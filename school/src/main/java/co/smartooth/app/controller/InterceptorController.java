package co.smartooth.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 04. 28
 * 수정일 : 2022. 11. 16
 * 서버분리 : 2023. 08. 01
 * @RestController를 쓰지 않는 이유는 몇 안되는 mapping에 jsp를 반환해줘야하는게 있으므로 @Controller를 사용한다.
 * @RestAPI로 반환해야할 경우 @ResponseBody를 사용하여 HashMap으로 return 해준다.
 */
@Slf4j
@Controller
public class InterceptorController {
    
	
	// 프리미엄 유치원 측정 서비스
	@GetMapping(value = {"/"})
	public String connectSchool() {
		return "index";
	}

	
	
//	/** 유치원 테스트 **/
//	// 프리미엄(치과) 로그인 기능
//	@GetMapping(value = {"/premium/login"})
//	public String premiumLogin() {
//		return "/test/premium/login/login";
//	}
	
	
	
	
}
