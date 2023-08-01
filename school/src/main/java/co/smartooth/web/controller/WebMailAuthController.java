package co.smartooth.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import co.smartooth.web.service.WebMailAuthService;
import co.smartooth.web.service.WebTeethService;
import co.smartooth.web.service.WebUserService;
import co.smartooth.web.vo.WebTeethMeasureVO;
import co.smartooth.web.vo.WebUserVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 4. 28 ~
 * @RestController를 쓰지 않는 이유는 몇 안되는 mapping에 jsp를 반환해줘야하는게 있으므로 @Controller를 사용한다.
 * @RestAPI로 반환해야할 경우 @ResponseBody를 사용하여 HashMap으로 return 해준다.
 */
@Controller
public class WebMailAuthController {

	@Autowired(required = false)
	WebStatisticsController webStatisticsController; 

	@Autowired(required = false)
	private WebMailAuthService webMailAuthService;

	

	/**
	 * 기능   : 인증 메일 발송
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 04. 27
	 * 수정일 : 2022. 08. 10
	 */
	@PostMapping(value = {"/web/auth/emailAuth.do"})
	@ResponseBody
	public HashMap<String,Object> mailAuth(HttpSession session, @RequestBody HashMap<String, String> paramMap) {

		// Parameter :: userId
		String userId = (String)session.getAttribute("userId");
		String authEmail = (String)paramMap.get("email");

		HashMap<String,Object> hm = new HashMap<String,Object>();
		
		try {
			webMailAuthService.sendMail(userId, authEmail);
		} catch (Exception e) {
			hm.put("code", "500");
			hm.put("msg", "Server Error");
			e.printStackTrace();
			return hm;
		}
		
		hm.put("code", "000");
		hm.put("msg", "Mail Sent Completed.");

		return hm;
	}

	
	
	
	/**
	 * 기능   : 인증 번호 (이메일) 일치 확인
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 04. 27
	 * 수정일 : 2022. 08. 10
	 */
	@PostMapping(value = {"/web/auth/isAuthKeyMatch.do"})
	@ResponseBody
	public HashMap<String,Object> isAuthKeyMatch(HttpSession session, @RequestBody HashMap<String, String> paramMap) {
		
		// 파라미터
 		String userId = (String)session.getAttribute("userId");
		String authKey = paramMap.get("authKey");
		int isAuthKeyMatch = 0;
		
		HashMap<String,Object> hm = new HashMap<String,Object>();
		
		
		try {
			
			// 메일 인증 번호 확인
			isAuthKeyMatch = webMailAuthService.isAuthKeyMatch(userId, authKey);
			
			// 메일 인증 번호 일치
			if(isAuthKeyMatch > 0 ) {
				
				// 인증 여부 N -> Y
				webMailAuthService.updateAuthStatusY(userId);
				hm.put("code", "000");
				hm.put("msg", "success");
				
			}else {
				hm.put("code", "400");
 				hm.put("msg", "failed");
			}
			
		} catch (Exception e) {
			hm.put("code", "500");
			hm.put("msg", "Server Error");
			e.printStackTrace();
			return hm;
		}
		
		
		
		return hm;
	}
}
