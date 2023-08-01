package co.smartooth.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 04. 28
 * 수정일 : 2022. 11. 16
 * @RestController를 쓰지 않는 이유는 몇 안되는 mapping에 jsp를 반환해줘야하는게 있으므로 @Controller를 사용한다.
 * @RestAPI로 반환해야할 경우 @ResponseBody를 사용하여 HashMap으로 return 해준다.
 */
@Slf4j
@Controller
public class InterceptorController {
    
	
	/** 유틸리티 **/
	// 비밀번호 암호화/복호화
	@GetMapping(value = {"/premium/utils/passwordCrypto"})
	public String passwordCrypto() {
		return "/test/premium/utils/passwordCrypto";
	}
	
	
	@GetMapping(value = {"/premium/utils/updateCavityCnt"})
	public String updateCavityCnt() {
		return "/test/premium/utils/updateCavityCnt";
	}
	
	
	@GetMapping(value = {"/premium/utils/selectCavityLevel"})
	public String selectCavityLevel() {
		return "/test/premium/utils/selectCavityLevel";
	}
	
	
	@GetMapping(value = {"/admin/utils/xlsxRegistUsers"})
	public String uploadCvs() {
		return "/test/admin/utils/xlsxRegistUsers";
	}

	
	@GetMapping(value = {"/premium/utils/updateDiagCd"})
	public String updateDiagCd() {
		return "/test/premium/utils/updateDiagCd";
	}
	
	
	@GetMapping(value = {"/premium/utils/selectTeethStatusInfo"})
	public String selectTeethStatusInfo() {
		return "/test/premium/utils/selectTeethStatusInfo";
	}

	
	/** 유치원 테스트 **/
	// 프리미엄(치과) 로그인 기능
	@GetMapping(value = {"/premium/login"})
	public String premiumLogin() {
		return "/test/premium/login/login";
	}
	
	
	
	/** 치과 테스트 **/
	// 프리미엄(치과) 로그인 기능
	@GetMapping(value = {"/dentist/login"})
	public String dentistLogin() {
		return "/test/dentist/login/login";
	}
	

	/**테스트**/
	// 프리미엄(치과) 로그인 기능
	@GetMapping(value = {"/dentist/user/regist"})
	public String dentistRegist() {
		return "/test/dentist/user/regist";
	}
	
	
	
}
