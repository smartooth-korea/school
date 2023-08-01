package co.smartooth.app.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import co.smartooth.app.service.MailAuthService;
import co.smartooth.app.service.UserService;
import co.smartooth.app.utils.AES256Util;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 4. 28 ~
 * @RestController를 쓰지 않는 이유는 몇 안되는 mapping에 jsp를 반환해줘야하는게 있으므로 @Controller를 사용한다.
 * @RestAPI로 반환해야할 경우 @ResponseBody를 사용하여 HashMap으로 return 해준다.
 */
@Controller
public class MailAuthController {


	@Autowired(required = false)
	private MailAuthService mailAuthService;

	@Autowired(required = false)
	private UserService userService;
	
	
	
	/**
	 * 기능   : 인증 메일 발송
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 04. 27
	 * 수정일 : 2022. 08. 10
	 */
//	@PostMapping(value = {"/app/user/signUp/emailAuth.do"})
	@PostMapping(value = {"/premium/user/signUp/emailAuth.do"})
	@ResponseBody
		public HashMap<String,Object> mailAuth(@RequestBody HashMap<String, String> paramMap) {

		HashMap<String,Object> hm = new HashMap<String,Object>();
		int duplicateChkId = 0;

		// Parameter :: userId 값 검증
		String userId = (String)paramMap.get("userId");
		// (Null 체크 및 공백 체크)
		if(userId == null || userId.equals("")) {
			hm.put("code", "401");
			hm.put("msg", "There is no userId parameter.");
			return hm;
		}
		
		try {
			// 아이디 중복 체크 :: 0 일 경우, 아이디가 존재 하지 않는 경우, 0이 아닌 경우 아이디가 존재
			duplicateChkId = userService.duplicateChkId(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(duplicateChkId == 0) { // 아이디가 존재하지 않는 경우, 메일 발송
			try {
				mailAuthService.sendMail(userId);
				mailAuthService.updateAuthStatusN(userId);
			} catch (Exception e) {
				hm.put("code", "500");
				hm.put("msg", "Server Error");
				e.printStackTrace();
				return hm;
			}
			hm.put("code", "000");
			hm.put("msg", "Mail Sent Completed.");
		}else {
			hm.put("code", "402");
			hm.put("msg", "This ID is already registered.");			
		}
		return hm;
	}
	
	
	
	/**
	 * 기능   : 인증 메일 URL 클릭시 메일 인증 진행
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 04. 27
	 * 수정일 : 2022. 08. 10
	 * 비고   : API 문서엔 적혀있지 않음
	 */
//	@GetMapping(value = {"/app/user/signUp/emailConfirm.do"})
	@GetMapping(value = {"/premium/user/signUp/emailConfirm.do"})
	@ResponseBody
	public HashMap<String,Object> signUpConfirm(@RequestParam Map<String, String> paramMap) throws Exception {

		HashMap<String,Object> hm = new HashMap<String,Object>();
		// userId(email), authKey 가 일치할경우 authStatus 업데이트
		String userId = paramMap.get("userId");
		String authKey = paramMap.get("authKey");
		String decId = "";
		String decAuthKey = "";
		
		// 아이디와 인증번호 복호화
		AES256Util aes256Util = new AES256Util();
		decAuthKey = aes256Util.aesDecode(authKey);
		decId = aes256Util.aesDecode(userId);
		
		if(decAuthKey.contains("false")) {
			hm.put("code", "403");
			hm.put("msg", "Authentication failed.");
			return hm;
		}
		// 인증 메일 상태 'Y'로 변경
		mailAuthService.updateAuthStatusY(decId);

		// authSatus의 상태를 JSON으로 return
		hm.put("code", "Please Go back to application, and Click 'verification confirmation' button");
		hm.put("msg", "Certification Success");
		return hm;

	}
	
	
	
	/**
	 * 기능   : 앱에서 메일 인증 여부 확인
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 4. 27
	 */
//	@PostMapping(value = {"/app/user/signUp/emailAuthChk.do"})
	@PostMapping(value = {"/premium/user/signUp/emailAuthChk.do"})
	@ResponseBody
	public HashMap<String,Object> emailAuthChk(@RequestBody Map<String, String> paramMap) throws Exception {

		HashMap<String,Object> hm = new HashMap<String,Object>();
		String authStatusYn = "";
		String userId = paramMap.get("userId");
		
		if(userId == null || userId.equals("")) {
			hm.put("code", "401");
			hm.put("msg", "There is no userId parameter.");
			return hm;
		}
		
		// 사용자의 메일 인증 여부 확인
		authStatusYn = mailAuthService.isEmailAuthEnabled(userId);
		// Null 체크
		if(authStatusYn != null && !authStatusYn.equals("")) {
			if(authStatusYn.equals("Y")) { // Y일 경우 인증 완료
				hm.put("code", "000");
				hm.put("msg", "Authentication Success.");
			}else {  // N일 경우 인증 실패
				hm.put("code", "403");
				hm.put("msg", "Authentication failed");
			}
		}else {
			hm.put("code", "405");
			hm.put("msg", "This ID does not exist or ID that did not request authentication.");
		}
		return hm;
	}
}
