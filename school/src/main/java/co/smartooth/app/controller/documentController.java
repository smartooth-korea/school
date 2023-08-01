package co.smartooth.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 04. 28
 * @RestController를 쓰지 않는 이유는 몇 안되는 mapping에 jsp를 반환해줘야하는게 있으므로 @Controller를 사용한다.
 * @RestAPI로 반환해야할 경우 @ResponseBody를 사용하여 HashMap으로 return 해준다.
 */
@Controller
public class documentController {
	
	// 이용약관 (한글)
	@GetMapping(value = {"app/document/termsOfServiceKo.do"})
	public String termsOfServiceKo() {
		Logger logger = LoggerFactory.getLogger(getClass());
		logger.info("Call document 'Terms Of Service (KOR)'", "terms of service");
		logger.toString();
		return "/document/termsOfService_ko";
	}

	// 이용약관 (영문)
	@GetMapping(value = {"/app/document/termsOfServiceEn.do"})
	public String termsOfServiceEn() {
		Logger logger = LoggerFactory.getLogger(getClass());
		logger.info("Call document 'Terms Of Service (ENG)'", "terms of service");
		logger.toString();
		return "/document/termsOfService_en";
	}
	
}
