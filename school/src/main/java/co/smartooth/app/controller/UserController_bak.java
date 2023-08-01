//package co.smartooth.app.controller;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.ResponseBody;
//import co.smartooth.app.service.MailAuthService;
//import co.smartooth.app.service.TeethService;
//import co.smartooth.app.service.UserService;
//import co.smartooth.app.utils.AES256Util;
//import co.smartooth.app.utils.JwtTokenUtil;
//import co.smartooth.app.vo.SchoolClassInfoVO;
//import co.smartooth.app.vo.TeethInfoVO;
//import co.smartooth.app.vo.UserVO;
//
///**
// * 작성자 : 정주현 
// * 작성일 : 2022. 04. 28
// * 수정일 : 2022. 08. 03
// * @RestController를 쓰지 않는 이유는 몇 안되는 mapping에 jsp를 반환해줘야하는게 있으므로 @Controller를 사용한다.
// * @RestAPI로 반환해야할 경우 @ResponseBody를 사용하여 HashMap으로 return 해준다.
// */
//@Controller
//public class DentistUserController {
//	
//	Logger logger = LoggerFactory.getLogger(getClass());
//	
//	// 인증 여부 확인 flag
//	private static boolean tokenValidation = false; 
//
//	
//	@Autowired(required = false)
//	private DentalUserService userService;
//	
//	
//	/**
//	 * 기능   : 전체 선생님 ID 목록 조회
//	 * 작성자 : 정주현 
//	 * 작성일 : 2022. 07. 13
//	 * 수정일 : 2022. 08. 02
//	 */
////	@PostMapping(value = {"/app/user/selectSchoolList.do"})
////	@PostMapping(value = {"/premium/user/selectSchoolList.do"})
////	@ResponseBody
////		public HashMap<String,Object> selectSchoolList(HttpServletRequest request) {
////
////
////		// 현재 개발이 진행되어있는 상태는 강제로 TC목록을 조회하지만
////		// 나중의 경우 원장, 교감, 교장이 선생님 목록을 가져올 수 있도록 코드를 이용해야 될 것으로 보임
////		String userAuthToken = null;
////		userAuthToken = request.getHeader("Authorization");
////		
////		JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
////		
////		HashMap<String,Object> hm = new HashMap<String,Object>(); 
////		
////		
////		List<SchoolClassInfoVO> schoolList = new ArrayList<SchoolClassInfoVO>();
////		
////		tokenValidation = jwtTokenUtil.validateToken(userAuthToken);
////		
////		if(tokenValidation1) {
////
////			try {
////				// 학교 목록 조회
////				schoolList = userService.selectSchoolList();
////			} catch (Exception e) {
////				hm.put("code", "500");
////				hm.put("msg", "Server Error.");
////				e.printStackTrace();
////			}
////			hm.put("schoolList", schoolList);
////			hm.put("code", "000");
////			hm.put("msg", "Success.");
////		}else {
////			hm.put("code", "400");
////			hm.put("msg", "The token is not valid.");
////		}
////		return hm;
////	}
//	
//	
//	
//	
//	/**
//	 * 기능   : 기관 선택 → 해당 기관의 부서(반) 목록 조회
//	 * 작성자 : 정주현 
//	 * 작성일 : 2022. 07. 13
//	 * 수정일 : 2023. 05. 16
//	 */
////	@PostMapping(value = {"/app/user/selectTcUserList.do"})
//	@PostMapping(value = {"/premium/user/selectDepartmentList.do"})
//	@ResponseBody
//		public HashMap<String,Object> selectDepartmentList(@RequestBody HashMap<String, String> paramMap,  HttpServletRequest request) {
//
//
//		String lang = (String)paramMap.get("lang");
//		String userAuthToken =  request.getHeader("Authorization");
//		
//		// 기관 코드 >> OrganCode 로 추후 변경 예정
//		String schoolCode = (String)paramMap.get("schoolCode");
//		
//		
//		HashMap<String,Object> hm = new HashMap<String,Object>();
//		JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
//		
//		List<UserVO> departmentList = new ArrayList<UserVO>(); 
//		
//		tokenValidation = jwtTokenUtil.validateToken(userAuthToken);
//		
//		if(tokenValidation) {
//
//			try {
//				
//				// 부서(반) 목록 조회
//				departmentList = userService.selectDepartmentList(schoolCode);
//				
//			} catch (Exception e) {
//				
//				if(lang.equals("ko")) {
//					hm.put("msg", "서버 에러가 발생했습니다.\n관리자에게 문의해주시기 바랍니다.");
//				}else if(lang.equals("en")){
//					hm.put("msg", "Server Error");
//				}
//				hm.put("code", "500");
//				e.printStackTrace();
//			}
//			
//			// dataList 반환
//			hm.put("departmentList", departmentList);
//			hm.put("code", "000");
//
//			if(lang.equals("ko")) {
//				hm.put("msg", "성공"); 
//			}else if(lang.equals("en")){
//				hm.put("msg", "Success"); 
//			}
//		}else {
//			if(lang.equals("ko")) {
//				hm.put("msg", "로그인 정보가 유효하지 않습니다");
//			}else if(lang.equals("en")){
//				hm.put("msg", "The token is not valid");
//			}
//			hm.put("code", "400");
//		}
//		return hm;
//	}
//	
//	
//	
//	
//	/**
//	 * 기능   : 선생님 ID로 반 학생들 목록 조회
//	 * 작성자 : 정주현 
//	 * 작성일 : 2022. 07. 13
//	 * 수정일 : 2022. 08. 02
//	 */
////	@PostMapping(value = {"/premium/user/selectStUserListByTc.do"})
//	@PostMapping(value = {"/premium/user/selectMeasuredUserList.do"})
//	@ResponseBody
//		public HashMap<String,Object> selectStUserListByTc(@RequestBody HashMap<String, String> paramMap, HttpServletRequest request) {
//
//		String lang = (String)paramMap.get("lang");
//		String userAuthToken = request.getHeader("Authorization");
//		String userId = null;
//		String orderBy = null;
//		HashMap<String,Object> hm = new HashMap<String,Object>();
//		JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
//		
//		// Parameter :: userId 값 검증
//		userId = (String)paramMap.get("userId");
//		// (Null 체크 및 공백 체크)
//		if(userId == null || userId.equals("")) {
//			if(lang.equals("ko")) {
//				hm.put("msg", "부서(반) 아이디가 존재하지 않습니다.");
//			}else if(lang.equals("en")){
//				hm.put("msg", "There is no userId parameter.");
//			hm.put("code", "401");
//			}
//			return hm;
//		}
//		orderBy = (String)paramMap.get("order");
//		if(orderBy == null) {
//			orderBy = "ASC";
//		}
//		
//		tokenValidation = jwtTokenUtil.validateToken(userAuthToken);
//		List<UserVO> measuredUserList = new ArrayList<UserVO>(); 
//		
//		if(tokenValidation) {
//			try {
//
//				// 부서(반) 아이디로 피측정자 목록 조회
//				measuredUserList = userService.selectMeasuredUserList(userId, orderBy);
//				
//			}catch (Exception e) {
//				
//				if(lang.equals("ko")) {
//					hm.put("msg", "서버 에러가 발생했습니다.\n관리자에게 문의해주시기 바랍니다.");
//				}else if(lang.equals("en")){
//					hm.put("msg", "Server Error");
//				}
//				hm.put("code", "500");
//				e.printStackTrace();
//			}
//			// dataList 반환
//			hm.put("measuredUserList", measuredUserList);
//			hm.put("code", "000");
//			
//			if(lang.equals("ko")) {
//				hm.put("msg", "성공"); 
//			}else if(lang.equals("en")){
//				hm.put("msg", "Success"); 
//			}
//		}else {
//			if(lang.equals("ko")) {
//				hm.put("msg", "로그인 정보가 유효하지 않습니다");
//			}else if(lang.equals("en")){
//				hm.put("msg", "The token is not valid");
//			}
//			hm.put("code", "400");
//		}
//		return hm;
//	}
//	
//	
//	
//	
//}
