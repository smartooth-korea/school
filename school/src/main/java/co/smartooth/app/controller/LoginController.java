package co.smartooth.app.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import co.smartooth.app.service.AuthService;
import co.smartooth.app.service.LogService;
import co.smartooth.app.service.OrganService;
import co.smartooth.app.service.TeethService;
import co.smartooth.app.service.UserService;
import co.smartooth.app.utils.AES256Util;
import co.smartooth.app.vo.AuthVO;
import co.smartooth.app.vo.TeethInfoVO;
import co.smartooth.app.vo.UserVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 04. 28
 * 수정일 : 2023. 06. 28
 */
@Controller
public class LoginController {

	@Autowired(required = false)
	private LogService logService;

	@Autowired(required = false)
	private AuthService authService;

	@Autowired(required = false)
	private UserService userService;

	@Autowired(required = false)
	private OrganService organService;
	
	

	
	/**
	 * 기능   : 프리미엄(유치원) 로그인 API
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 07. 19
	 */
	@PostMapping(value = {"/premium/login.do"})
	@ResponseBody
	public HashMap<String,Object> appLogin(@RequestBody HashMap<String, Object> paramMap) {
       
	    Logger logger = LoggerFactory.getLogger(getClass());

	    logger.debug("========== Premium LoginController ========== /premium/login.do ==========");
	    logger.debug("========== Premium LoginController ========== /premium/login.do ==========");
	    logger.debug("========== Premium LoginController ========== /premium/login.do ==========");
	    logger.debug("========== Premium LoginController ========== /premium/login.do ==========");
	    
	    //String lang = (String)paramMap.get("lang");
	    // 하드코딩
	    String lang = "ko";
	    String userId = null;
		String userPwd = null;
		String userType = null;
		String loginIp = null;
		String userAuthToken = null;
		
		int loginChkByIdPwd = 0;
		int isIdExist = 0;
		
		
		HashMap<String,Object> hm = new HashMap<String,Object>();
		List<HashMap<String, Object>> measureOranList = new ArrayList<HashMap<String, Object>>();
		
		// 로그인 인증 VO
		AuthVO authVO = new AuthVO();
		// 로그인 회원 VO
		UserVO userVO = new UserVO(); 
		
		
		// 오늘 일자 계산
		Date tmpDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
		String sysDate = sdf.format(tmpDate);
		
		// 파라미터 >> 값 setting
		userId= (String)paramMap.get("userId");
		loginIp = (String)paramMap.get("loginIp"); 
		
		// 비밀번호 암호화 
		AES256Util aes256Util = new AES256Util();
		userPwd = aes256Util.aesEncode((String)paramMap.get("userPwd"));
		
		if(userPwd.equals("false")) { // 암호화에 실패할 경우
			hm.put("code", "500");
			if(lang.equals("ko")) {
				hm.put("msg", "서버 에러가 발생했습니다.\n관리자에게 문의해주시기 바랍니다.");
			}else if(lang.equals("en")) {
				hm.put("msg", "Server Error");
			}
			return hm;
		}
		
		// 회원 인증 VO
		authVO.setUserId(userId);
		authVO.setUserPwd(userPwd);
		authVO.setLoginDt(sysDate);
		authVO.setUserIp(loginIp);
		
		
		try {
			
			// 로그인 확인
			loginChkByIdPwd = authService.loginChkByIdPwd(authVO);
			if(loginChkByIdPwd == 0){ // 0일 경우는 Database에 ID와 비밀번호가 틀린 것
				isIdExist = authService.isIdExist(authVO.getUserId());
				if(isIdExist == 0) { // ID가 존재하지 않을 경우
					if(lang.equals("ko")) {
						hm.put("msg", "해당 아이디가 존재하지 않습니다");
					}else if(lang.equals("en")) {
						hm.put("msg", "This ID does not exist");
					}
				}else { // PWD가 틀렸을 경우
					hm.put("code", "406");
					if(lang.equals("ko")) {
						hm.put("msg", "비밀번호가 틀렸습니다");
					}else if(lang.equals("en")) {
						hm.put("msg", "The password is wrong");
					}
				}
			}else { 
				
				userVO = userService.selectUserInfo(userId);
				userType = userVO.getUserType();
				// ID와 PWD가 검증된 이후 JWT 토큰과 회원의 정보를 제공하고 LOG를 INSERT
				// JWT token 발행
				// JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
				// userAuthToken = jwtTokenUtil.createToken(authVO);
				
				// 로그인 일자 업데이트
				logService.updateLoginDt(authVO);
				
				// 로그인 시 등록 되어 있는 측정 예정 혹은 측정 완료 기관 목록 조회 (SYSDATE 기준)
				// 해당 메소드의 경우 측정자일 경우에만 사용을 해야될 것으로 보이며
				// 슈퍼관리자 및 매니저의 경우는 SCHOOL_INFO를 참조하여 조회해야할 것으로 보임
				if(userType.equals("SU") || userType.equals("AD") || userType.equals("MA")) {
					measureOranList = organService.selectOrganList(userId);
				}else {
					measureOranList = organService.selectMeasureOrganList(userId);
				}
				
				
				// 데이터 RETURN0
				hm.put("userAuthToken", userAuthToken);
				hm.put("measureOranList", measureOranList);
				
				// 메시지 RETURN
				hm.put("code", "000");
				if(lang.equals("ko")) {
					hm.put("msg", "로그인 성공");
				}else if(lang.equals("en")) {
					hm.put("msg", "Login Success");
				}
				
				// 로그인 Log 등록
				logService.insertUserLoginHistory(authVO);
			}
		} catch (Exception e) {
			hm.put("code", "500");
			if(lang.equals("ko")) {
				hm.put("msg", "서버 에러가 발생했습니다.\n관리자에게 문의해주시기 바랍니다.");
			}else if(lang.equals("en")) {
				hm.put("msg", "Server Error");
			}
			e.printStackTrace();
		}
		return hm;
	}
	
}
