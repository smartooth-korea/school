package co.smartooth.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import co.smartooth.app.service.UserService;
import co.smartooth.app.vo.UserVO;


/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 04. 28
 * 수정일 : 2023. 08. 02
 * 서버분리 : 2023. 08. 01
 * @RestController를 쓰지 않는 이유는 몇 안되는 mapping에 jsp를 반환해줘야하는게 있으므로 @Controller를 사용한다.
 * @RestAPI로 반환해야할 경우 @ResponseBody를 사용하여 HashMap으로 return 해준다.
 */
@Controller
public class UserController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	// 인증 여부 확인 flag
	// private static boolean tokenValidation = false;
	private static boolean tmpTokenValidation = true; 

	
	@Autowired(required = false)
	private UserService userService;
	


	
	/**
	 * 기능   : 부서(반) 소속 학생 목록 조회
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 07. 13
	 * 수정일 : 2023. 08. 02
	 * 서버분리 : 2023. 08. 01
	 */
//	@PostMapping(value = {"/premium/user/selectOrganUserList.do"})
	@PostMapping(value = {"/premium/user/selectStUserListByTc.do"})
	@ResponseBody
		public HashMap<String,Object> selectOrganUserList(@RequestBody HashMap<String, String> paramMap, HttpServletRequest request) {

		String userId = null;
		String orderBy = null;
		// String userAuthToken = request.getHeader("Authorization");
		
		HashMap<String,Object> hm = new HashMap<String,Object>();
		List<UserVO> stList = new ArrayList<UserVO>();
		
		// Parameter :: userId 값 검증
		userId = (String)paramMap.get("userId");
		// (Null 체크 및 공백 체크)
		if(userId == null || userId.equals("")) {
			hm.put("code", "401");
			hm.put("msg", "파라미터(ID)가 없습니다.");
			return hm;
		}
		orderBy = (String)paramMap.get("order");
		if(orderBy == null) {
			orderBy = "ASC";
		}
		
		// JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
		// tokenValidation = jwtTokenUtil.validateToken(userAuthToken);
		
		if(tmpTokenValidation) {

			try {
				// 부서(반) 소속 학생 목록 조회
				stList = userService.selectMeasuredUserList(userId, orderBy);
			} catch (Exception e) {
				hm.put("code", "500");
				hm.put("msg", "피측정자 목록 조회을 조회하지 못했습니다\n관리자에게 문의 해주시기 바랍니다.");
				e.printStackTrace();
			}
			hm.put("stList", stList);
			hm.put("code", "000");
			hm.put("msg", "피측 정자 목록 조회 성공");
			
		}else {
			hm.put("code", "400");
			hm.put("msg", "토큰이 유효하지 않습니다. 다시 로그인 해주시기 바랍니다.");
		}
		return hm;
	}
	
	
	
	/**
	 * 기능   : 회원 정보(개인정보) 수정
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 05. 30
	 */
//	@PostMapping(value = {"/app/user/updateUserInfo.do"})
	@PostMapping(value = {"/premium/user/updateUserInfo.do"})
	@ResponseBody
	public HashMap<String,Object> updateUserInfo(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception {
		
		logger.debug("========== School.UserController ========== updateUserInfo.do ==========");
		logger.debug("========== School.UserController ========== updateUserInfo.do ==========");
		logger.debug("========== School.UserController ========== updateUserInfo.do ==========");
		logger.debug("========== School.UserController ========== updateUserInfo.do ==========");
		
		String userId = null;
		String userNo = null;
		// String userAuthToken = null;
		
		String userName = null;
		String userBirthday = null;
		String userCountry = null;
		String userState = null;
		String userAddress = null;
		String userTelNo = null;
		String userSex = null;
		

		HashMap<String,Object> hm = new HashMap<String,Object>();
		UserVO userVO = new UserVO();
		
		userId = (String)paramMap.get("userId");
		
		// Parameter = userId 값 검증 (Null 체크 및 공백 체크)
		userId= (String)paramMap.get("userId");
		if(userId == null || userId.equals("") || userId.equals(" ")) {
			hm.put("code", "401");
			hm.put("msg", "There is no userId parameter");
			return hm;
		}
		
		// userAuthToken = request.getHeader("Authorization");
		userName = (String)paramMap.get("userName");
		userBirthday = (String)paramMap.get("userBirthday");
		userCountry = (String)paramMap.get("userCountry");
		userState = (String)paramMap.get("userState");
		userAddress = (String)paramMap.get("userAddress");
		userTelNo = (String)paramMap.get("userTelNo");
		userSex = (String)paramMap.get("userSex");
		
		// TOKEN 검증
		// JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
		// tokenValidation = jwtTokenUtil.validateToken(userAuthToken);
		
		if (tmpTokenValidation) {
			try {
				
				userVO.setUserId(userId);
				userVO.setUserNo(userNo);
				userVO.setUserName(userName);
				userVO.setUserBirthday(userBirthday);
				userVO.setUserCountry(userCountry);
				userVO.setUserState(userState);
				userVO.setUserAddress(userAddress);
				userVO.setUserTelNo(userTelNo);
				userVO.setUserSex(userSex);
				
				userService.updateUserInfo(userVO);
				
			} catch (Exception e) {
				hm.put("code", "500");
				hm.put("msg", "Server Error.");
				e.printStackTrace();
			}
		}else {
			hm.put("code", "400");
			hm.put("msg", "The token is not valid.");
		}
		
		hm.put("code", "000");
		hm.put("msg", "Success.");
		return hm;
	}
	
}
