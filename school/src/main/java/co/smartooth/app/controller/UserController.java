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

import co.smartooth.app.service.OrganService;
import co.smartooth.app.service.TeethService;
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

	
	@Autowired(required = false)
	private TeethService teethService;
	


	
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
		public HashMap<String,Object> selectOrganUserList(@RequestBody HashMap<String, String> paramMap) {

		String userId = null;
		String orderBy = null;
		// String userAuthToken = request.getHeader("Authorization");
		
		HashMap<String,Object> hm = new HashMap<String,Object>();
		//List<UserVO> stList = new ArrayList<UserVO>();
		List<HashMap<String, Object>> stList = new ArrayList<HashMap<String, Object>>();
		
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
				for(int i=0; i<stList.size();i++) {
					// 피측정자 아이디
					String stUserId = (String)stList.get(i).get("userId");
					HashMap<String, Object> prUserInfo = userService.selectPrUserInfo(stUserId);
					String measureDt = teethService.selectMeasureDt(stUserId);
					if(measureDt == null) {
						measureDt = "";
					}
					stList.get(i).put("measureDt", measureDt);
					if(prUserInfo==null) {
						continue;
					}
					stList.get(i).put("prUserName", prUserInfo.get("userName"));
					stList.get(i).put("prUserTelNo", prUserInfo.get("userTelNo"));
					stList.get(i).put("prUserEmail", prUserInfo.get("userEmail"));
				}
				
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
	
	
	
	/**
	 * 기능   : 측정자 정보 등록
	 * 작성자 : 정주현 
	 * 작성일 : 2023. 08. 29
	 * 수정일 : 2023. 08. 29
	 */
	@PostMapping(value = {"/premium/user/insertMeasurerInfo.do"})
	@ResponseBody
	public HashMap<String,Object> insertMeasurerInfo(@RequestBody HashMap<String, Object> paramMap) throws Exception{
		
		
		logger.debug("========== premium.UserController ========== /premium/user/insertMeasurerInfo.do ==========");
		logger.debug("========== premium.UserController ========== /premium/user/insertMeasurerInfo.do ==========");
		logger.debug("========== premium.UserController ========== /premium/user/insertMeasurerInfo.do ==========");
		logger.debug("========== premium.UserController ========== /premium/user/insertMeasurerInfo.do ==========");

		
		// String lang = (String)paramMap.get("lang");
	    // 하드코딩
	    // if(lang == null || lang.equals("")) {
	    //		lang = "ko";
	    // }
		
		// 측정자 아이디
		String measurerId = (String)paramMap.get("measurerId");
		// 측정자 이름
		String measurerNm = (String)paramMap.get("measurerNm");
		// 측정자 이메일
		String measurerEmail = (String)paramMap.get("measurerEmail");
		// 측정자 전화번호
		String measurerTelNo = (String)paramMap.get("measurerTelNo");

		HashMap<String,Object> hm = new HashMap<String,Object>();
		List<HashMap<String, Object>> measurerList = new ArrayList<HashMap<String, Object>>();
			
		 try {
			
			// 측정자 정보 등록
			userService.insertMeasurerInfo(measurerId, measurerNm, measurerEmail, measurerTelNo);
			// 측정자 목록 조회
			measurerList = userService.selectMeasurerList();
			
		} catch (Exception e) {
			
			hm.put("code", "500");
			//if(lang.equals("ko")) {
				hm.put("msg", "측정자 등록에 실패하였습니다.\n관리자에게 문의해주시기 바랍니다.");
			//}else if(lang.equals("en")) {
			//	hm.put("msg", "Failed to register the doctor registration.\nPlease contact the administrator.");
			//}
			e.printStackTrace();
			
		}
		
		// 피측정자 목록
		hm.put("measurerList", measurerList);
		hm.put("code", "000");
		//if(lang.equals("ko")) {
			hm.put("msg", "측정자가 등록되었습니다.");
		//}else if(lang.equals("en")) {
		//	hm.put("msg", "Success");
		//}
		return hm;
		
	}
	
	
	
	/**
	 * 기능   : 측정자 정보 업데이트(수정)
	 * 작성자 : 정주현 
	 * 작성일 : 2023. 08. 29
	 * 수정일 : 2023. 08. 29
	 */
	@PostMapping(value = {"/premium/user/updateMeasurerInfo.do"})
	@ResponseBody
	public HashMap<String,Object> updateMeasurerInfo(@RequestBody HashMap<String, Object> paramMap) throws Exception{
		
		
		logger.debug("========== premium.UserController ========== /premium/user/updateMeasurerInfo.do ==========");
		logger.debug("========== premium.UserController ========== /premium/user/updateMeasurerInfo.do ==========");
		logger.debug("========== premium.UserController ========== /premium/user/updateMeasurerInfo.do ==========");
		logger.debug("========== premium.UserController ========== /premium/user/updateMeasurerInfo.do ==========");

		
		// String lang = (String)paramMap.get("lang");
	    // 하드코딩
	    // if(lang == null || lang.equals("")) {
	    //		lang = "ko";
	    // }
		// 측정자 아이디
		String measurerId = (String)paramMap.get("measurerId");
		// 측정자 이름
		String measurerNm = (String)paramMap.get("measurerNm");
		// 측정자 이메일
		String measurerEmail = (String)paramMap.get("measurerEmail");
		// 측정자 전화번호
		String measurerTelNo = (String)paramMap.get("measurerTelNo");

		HashMap<String,Object> hm = new HashMap<String,Object>();
		List<HashMap<String, Object>> measurerList = new ArrayList<HashMap<String, Object>>();
			
		 try {
			
			// 측정자 정보 등록
			userService.updateMeasurerInfo(measurerId, measurerNm, measurerEmail, measurerTelNo);
			// 측정자 목록 조회
			measurerList = userService.selectMeasurerList();
			
		} catch (Exception e) {
			
			hm.put("code", "500");
			//if(lang.equals("ko")) {
				hm.put("msg", "측정자 정보 수정에 실패하였습니다.\n관리자에게 문의해주시기 바랍니다.");
			//}else if(lang.equals("en")) {
			//	hm.put("msg", "Failed to register the doctor registration.\nPlease contact the administrator.");
			//}
			e.printStackTrace();
			
		}
		
		// 피측정자 목록
		hm.put("measurerList", measurerList);
		hm.put("code", "000");
		//if(lang.equals("ko")) {
			hm.put("msg", "측정자 정보가 수정되었습니다.");
		//}else if(lang.equals("en")) {
		//	hm.put("msg", "Success");
		//}
		return hm;
		
	}
	
}
