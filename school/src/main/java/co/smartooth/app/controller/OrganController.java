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

/**
 * 작성자 : 정주현 
 * 작성일 : 2023. 08. 02
 * 서버분리 : 2023. 08. 01
 * @RestController를 쓰지 않는 이유는 몇 안되는 mapping에 jsp를 반환해줘야하는게 있으므로 @Controller를 사용한다.
 * @RestAPI로 반환해야할 경우 @ResponseBody를 사용하여 HashMap으로 return 해준다.
 */
@Controller
public class OrganController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	// 인증 여부 확인 flag
	// private static boolean tokenValidation = false;
	private static boolean tmpTokenValidation = true; 

	
	@Autowired(required = false)
	private OrganService organService;

	
	/**
	 * 기능   : 기관 선택 (유치원)
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 07. 13
	 * 수정일 : 2023. 08. 02
	 * 서버분리 : 2023. 08. 01
	 * 비고 : 추후 organ으로 이름을 변경할 예정
	 */
//	@PostMapping(value = {"/premium/organ/selectOrganList.do"})
	@PostMapping(value = {"/premium/user/selectTcUserList.do"})
	@ResponseBody
		public HashMap<String,Object> selectOrganList(@RequestBody HashMap<String, String> paramMap,  HttpServletRequest request) {

		// String userAuthToken = null;
		// userAuthToken = request.getHeader("Authorization");
		// 기관
		String organCd = (String)paramMap.get("schoolCode");
		
		HashMap<String,Object> hm = new HashMap<String,Object>();
		
		List<HashMap<String, Object>> tcList = new ArrayList<HashMap<String, Object>>();
		
		// JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
		// tokenValidation = jwtTokenUtil.validateToken(userAuthToken);
		
		if(tmpTokenValidation) {

			try {
				// Teacher List 조회
				tcList = organService.selectDepartmentList(organCd);
			} catch (Exception e) {
				hm.put("code", "500");
				hm.put("msg", "반(부서) 목록 조회를 하지 못했습니다.\n관리자에게 문의 해주시기 바랍니다.");
				e.printStackTrace();
			}
			hm.put("tcList", tcList);
			hm.put("code", "000");
			hm.put("msg", "성공.");
		}else {
			hm.put("code", "400");
			hm.put("msg", "토큰이 유효하지 않습니다.");
		}
		return hm;
	}

	
	
	
	
	
	
	
	
	
	/**
	 * 기능   : 유치원 목록 조회
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 07. 13
	 * 수정일 : 2022. 08. 02
	 * 서버분리 : 2023. 08. 01
	 * 비고 : 현재 로그인으로 구성이 되어있어 유치원의 목록을 조회하지 않음
	 */
//	@PostMapping(value = {"/premium/user/selectSchoolList.do"})
//	@ResponseBody
//		public HashMap<String,Object> selectSchoolList(HttpServletRequest request) {
//
//		// 현재 개발이 진행되어있는 상태는 강제로 TC목록을 조회하지만
//		// 나중의 경우 원장, 교감, 교장이 선생님 목록을 가져올 수 있도록 코드를 이용해야 될 것으로 보임
//		// String userAuthToken = null;
//		// userAuthToken = request.getHeader("Authorization");
//		
//		HashMap<String,Object> hm = new HashMap<String,Object>(); 
//		
//		List<SchoolClassInfoVO> schoolList = new ArrayList<SchoolClassInfoVO>();
//		
//		// JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
//		// tokenValidation = jwtTokenUtil.validateToken(userAuthToken);
//		
//		if(tmpTokenValidation) {
//			try {
//				// 학교 목록 조회
//				schoolList = userService.selectSchoolList();
//			} catch (Exception e) {
//				hm.put("code", "500");
//				hm.put("msg", "Server Error.");
//				e.printStackTrace();
//			}
//			hm.put("schoolList", schoolList);
//			hm.put("code", "000");
//			hm.put("msg", "Success.");
//		}else {
//			hm.put("code", "400");
//			hm.put("msg", "The token is not valid.");
//		}
//		return hm;
//	}
}
