package co.smartooth.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import co.smartooth.web.service.WebOrganService;


/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 11. 09
 * 수정일 : 2023. 07. 28 (서버 분리)
 * 
 * @RestController를 쓰지 않는 이유는 몇 안되는 mapping에 jsp를 반환해줘야하는게 있으므로 @Controller를 사용한다.
 * @RestAPI로 반환해야할 경우 @ResponseBody를 사용하여 HashMap으로 return 해준다.
 */
@Controller
public class WebOrganController {
	
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired(required = false)
	private WebOrganService webOrganService;
	
	
	
	/**
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 11. 10
	 * 기능   : 프리미엄 앱에 등록 되어있는 기관 조회
	 * @throws Exception 
	 */
	@PostMapping(value = {"/web/organ/ajaxSelectOrganList.do"})
	public @ResponseBody Map<String, Object> ajaxSelectOrganList(@RequestParam Map<String, Object> paramMap) throws Exception {
		
		// 실시간 기관 검색
		String schoolName = (String)paramMap.get("value");
		
		List<Map<String, Object>> organList = new ArrayList<Map<String, Object>>();

		organList = webOrganService.ajaxSelectOrganList("SCHOOL_NAME", schoolName);
		
		paramMap.put("organList", organList);
		
		return paramMap;
	}

	
	
	/**
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 11. 10
	 * 기능   : 프리미엄 앱에 등록 되어있는 치과 병원 조회
	 * @throws Exception 
	 */
	@PostMapping(value = {"/web/organ/ajaxSelectDentistList.do"})
	public @ResponseBody Map<String, Object> ajaxSelectDentistList(@RequestParam Map<String, Object> paramMap) throws Exception {
		
		// 실시간 기관 검색
		String schoolName = (String)paramMap.get("value");
		
		List<Map<String, Object>> organList = new ArrayList<Map<String, Object>>();
		
		organList = webOrganService.ajaxSelectDentistList("SCHOOL_NAME", schoolName);
		
		paramMap.put("organList", organList);
		
		return paramMap;
	}
	
	
	
	/**
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 11. 10
	 * 기능   : 기관(학교)의 해당 부서(반) 조회
	 * @throws Exception 
	 */
	@PostMapping(value = {"/web/organ/ajaxSelectDepartmentList.do"})
	public @ResponseBody Map<String, Object> ajaxSelectDepartmentList(@RequestParam Map<String, Object> paramMap) throws Exception {
		
		// 실시간 부서 검색 - 선택된 기관(학교)의 해당 부서(반) 조회
		String schoolCode = (String)paramMap.get("schoolCode");
		
		// 반 목록 조회
		List<HashMap<String,Object>> departList = new ArrayList<HashMap<String,Object>>();
		
		departList = webOrganService.selectDepartmentList("SCHOOL_CODE", schoolCode);
		
		paramMap.put("departList", departList);
		
		return paramMap;
	}
	
	
	
	
	/**
	 * 작성자 : 정주현 
	 * 작성일 : 2023. 06. 29
	 * 기능   : 등록 되어있는 치과 실시간 조회
	 * @throws Exception 
	 */
	@PostMapping(value = {"/web/organ/ajaxSelectDentalHospitalList.do"})
	public @ResponseBody Map<String, Object> ajaxSelectDentalHospitalList(@RequestParam Map<String, Object> paramMap) throws Exception {
		
		// 실시간 기관 검색
		String dentalHospitalNm = (String)paramMap.get("value");
		List<Map<String, Object>> organList = new ArrayList<Map<String, Object>>();

		organList = webOrganService.ajaxSelectDentalHospitalList("DENTAL_HOSPITAL_NM", dentalHospitalNm);
		paramMap.put("organList", organList);
		return paramMap;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
