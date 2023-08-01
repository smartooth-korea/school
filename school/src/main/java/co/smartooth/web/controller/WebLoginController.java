package co.smartooth.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import co.smartooth.web.service.WebAuthService;
import co.smartooth.web.service.WebLogService;
import co.smartooth.web.service.WebMailAuthService;
import co.smartooth.web.service.WebUserService;
import co.smartooth.web.utils.AES256Util;
import co.smartooth.web.vo.WebAuthVO;
import co.smartooth.web.vo.WebUserVO;
import lombok.extern.slf4j.Slf4j;


/**
 * 작성자 : 정주현
 * 작성일 : 2022. 07. 07
 * 수정일 : 2023. 07. 28
 * 서버 분리 : 2023. 07. 28
 * @RestController를 쓰지 않는 이유는 몇 안되는 mapping에 jsp를 반환해줘야하는게 있으므로 @Controller를 사용한다.
 * @RestAPI로 반환해야할 경우 @ResponseBody를 사용하여 HashMap으로 return 해준다.
 */
@Slf4j
@Controller
@PropertySource({ "classpath:application.yml" })
public class WebLoginController {

	@Value("${loginUrl}")
    private String loginUrl;
	
	private static final Logger logger = LoggerFactory.getLogger(WebLoginController.class);
	
	@Autowired(required = false)
	private WebAuthService webAuthService;

	@Autowired(required = false)
	private WebLogService webLogService;

	@Autowired(required = false)
	private WebUserService webUserService;
	
	@Autowired(required = false)
	private WebMailAuthService webMailAuthService;
	

	
	/**
	 * 기능 : 학부모 및 자녀 결과지 웹 로그인 화면
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 07. 15
	 * 수정일 : 2023. 07. 28
	 */
	@GetMapping(value = {"/login"})
	public String loginForm(HttpServletRequest request, Model model, HttpSession session, HttpServletResponse response) {
		
		WebUserVO sessionVO = (WebUserVO)session.getAttribute("userInfo");
		if(sessionVO == null) {
			return "/web/login/kindergartenLoginForm";
		}else {
			return "redirect:/web/statistics/diagnosis.do";
		}
	}
	
	
	
	
	/**
	 * 기능 : 학부모 및 자녀 결과지 웹 로그인 화면
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 07. 15
	 * 수정일 : 2023. 07. 28
	 */
	@GetMapping(value = {"/login/kindergarten"})
	public String kindergartenLoginForm(HttpServletRequest request, Model model, HttpSession session, HttpServletResponse response) {
		
		WebUserVO sessionVO = (WebUserVO)session.getAttribute("userInfo");
		if(sessionVO == null) {
			return "/web/login/kindergartenLoginForm";
		}else {
			return "redirect:/web/statistics/diagnosis.do";
		}
		
	}

	
	
	
	/**
	 * 기능 : 기관장 결과지 웹 로그인 화면
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 07. 15
	 * 수정일 : 2023. 07. 28
	 */
	@GetMapping(value = {"/director/login"})
	public String directorLoginForm(HttpServletRequest request, Model model, HttpSession session, HttpServletResponse response) {
		
		WebUserVO sessionVO = (WebUserVO)session.getAttribute("userInfo");
		if(sessionVO == null) {
			// 기관장용 로그인 페이지
			return "/web/login/directorLoginForm";
		}else {
			return "redirect:/web/director/diagnosis.do";
		}
		
	}
	
	
	
	
//	/**
//	 * 요청 : 결과지 웹 로그인 화면
//	 * 작성자 : 정주현 
//	 * 작성일 : 2022. 07. 15
//	 * 수정일 : 2022. 03. 27
//	 */
//	@GetMapping(value = {"/en"})
//	public String LoginFormEng(HttpServletRequest request, Model model, HttpSession session, HttpServletResponse response) {
//		
//		Cookie cookieLang = new Cookie("lang", "en");
//	    cookieLang.setMaxAge(60*30*1); // 쿠키 유효 기간: 30분 설정(60초 * 30분 * 1시간)
//	    cookieLang.setPath("/en"); // dentist에서만 사용 가능하도록 변경
//	    response.addCookie(cookieLang); // response에 Cookie 추가
//	    
//	    Cookie cookieUrlType = new Cookie("urlType", "statistic"); 
//	    cookieUrlType.setMaxAge(60*30*1); // 쿠키 유효 기간: 30분 설정(60초 * 30분 * 1시간)
//	    cookieUrlType.setPath("/en"); // dentist에서만 사용 가능하도록 변경
//	    response.addCookie(cookieUrlType); // response에 Cookie 추가
//	    
//		model.addAttribute("lang", "en");
//		model.addAttribute("urlType", "statistic");
//		
//		WebUserVO sessionVO = (WebUserVO)session.getAttribute("userInfo");
//		if(sessionVO == null) {
//			return "/web/login/statisticsLoginForm_en";
//		}else {
//			return "redirect:/web/statistics/diagnosis.do";
//		}
//		
//	}
	
	
	
	
	/**
	 * 기능   : 진단 결과지 로그인 - 분기처리
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 07. 07
	 * 수정일 : 2023. 07. 20
	 * 			 APP에서 JSON형식으로 전달 받은 회원의 ID와 비밀번호를 확인 후 JSON으로 반환
	 * @throws Exception 
	 */
	@PostMapping(value = {"/web/statistics/login.do"})
	public String webStatisticsLoginForm(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
       
		
		// 세션이 없을 경우 로그인 페이지로 보내주는 부분 필요
		logger.debug("===== WEB ===== WebLoginController ===== /web/statistics/login.do =====");
		logger.debug("===== WEB ===== WebLoginController ===== /web/statistics/login.do =====");
		logger.debug("===== WEB ===== WebLoginController ===== /web/statistics/login.do =====");
		logger.debug("===== WEB ===== WebLoginController ===== /web/statistics/login.do =====");
		
		
		// 로그인 후 조회시 필요한 것들
		int loginChkByIdPwd = 0;
		// 회원아이디와 기관코드 대조
		int loginchkByIdOrganCd = 0;
		int isIdExist = 0;
		String isEmailAuthEnabled = "N";
		
		HttpSession session = request.getSession(true);
		// 세션 유지 시간 30분
		session.setMaxInactiveInterval(60*30*1);
		
		String userId = request.getParameter("userId");
		String stUserId = null;
		String userPwd = request.getParameter("userPwd");
		String userType = null;
		// 로그인 시 링크에 포함되어있는 schoolCode를 받아서 저장
		String organCd = request.getParameter("schoolCode");
		
		WebAuthVO webAuthVO = new WebAuthVO();
		WebUserVO webUserVO = new WebUserVO();
		WebUserVO userInfo = new WebUserVO(); 
		
		// 비밀번호 암호화 
		AES256Util aes256Util = new AES256Util();
		userPwd = aes256Util.aesEncode(userPwd);
		
		// 오늘 일자 계산
		Date tmpDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
		String sysDate = sdf.format(tmpDate);
		
		// 회원 정보 및 상세정보 조회
		WebUserVO tmpWebUserVO = webUserService.selectUserInfo(userId);
		userType = tmpWebUserVO.getUserType();
		
		// 로그인 VO
		webAuthVO.setUserId(userId);
		webAuthVO.setUserPwd(userPwd);
		webAuthVO.setLoginDt(sysDate);
		webAuthVO.setUserType(userType);
		// 회원 VO
		webUserVO.setUserId(userId);
		webUserVO.setUserType(userType);
		
		
//		// 치과 로그인 일 경우 아이디 앞에 자동으로 치과 코드 추가
//		if(urlType.equals("dentist")) {
//			userId = organCd+"-"+userId;
//		}
		
		try {
			// 아이디 검증 ::: schoolCode를 검증해야함
			loginChkByIdPwd = webAuthService.loginChkByIdPwd(webAuthVO);
			if(loginChkByIdPwd == 0){
				// 0일 경우는 Database에 ID와 비밀번호가 틀린 것
				isIdExist = webAuthService.isIdExist(webAuthVO);
				if(isIdExist == 0) {
					 // ID가 존재하지 않을 경우
					model.addAttribute("msg", "등록 되어있지 않은 아이디입니다.");
				}else { 
					// Password가 틀렸을 경우
					model.addAttribute("msg", "비밀번호가 틀렸습니다. 다시 입력해주시기 바랍니다.");
				}
				model.addAttribute("loginUrl", loginUrl+"/login");
				return "/common/alertMessage";
				
			}else {
				
				if(userType.equals("PR")) {
					// 법정대리인 아이디로 피측정자 아이디 조회
					stUserId = webUserService.selectStUserId(userId);
					// 피측정자 정보 및 상세정보 조회
					 //webUserVO = webUserService.selectUserInfo(userId);
				}else {
					stUserId = userId;
				}
				
				
				// 비밀번호가 맞을 경우 회원 아이디와 기관 코드 대조
				loginchkByIdOrganCd = webAuthService.loginChkByIdOrganCd(stUserId, organCd);
				
				if(loginchkByIdOrganCd == 0) { // 회원 아이디와 기관 코드가 맞지 않을 경우 
					model.addAttribute("msg", "해당 기관과 아이디가 일치 하지 않습니다.*기관 혹은 아이디가 정확하게 입력 되었는지 확인해주시기 바랍니다.");
					model.addAttribute("loginUrl", loginUrl+"/login");
					return "/common/alertMessage";
				}else {
					// 본인 인증 여부 확인
					isEmailAuthEnabled = webMailAuthService.isEmailAuthEnabled(userId);
					if("N".contains(isEmailAuthEnabled)) { 
						session.setAttribute("userId", userId);
						session.setAttribute("organCd", organCd);
						// 이메일 인증 화면
						return "/web/auth/emailAuth";
					 }
					// 로그인 관련 정보 확인 및 로그 기록
					// 로그인 일자 업데이트
					webLogService.updateLoginDt(webAuthVO);
					// 회원의 정보를 가져옴
					userInfo = webUserService.selectUserInfo(userId);
					userType = userInfo.getUserType();
					
					// 로그인 기록 VO
					webUserVO.setUserId(userInfo.getUserId());
					webUserVO.setUserName(userInfo.getUserName());
					webUserVO.setUserType(userInfo.getUserType());
					webUserVO.setLoginDt(userInfo.getLoginDt());
					webUserVO.setSchoolCode(organCd);
					webAuthVO.setUserNo(userInfo.getUserNo());
					webAuthVO.setLoginDt(userInfo.getLoginDt());
					webAuthVO.setLoginIp(request.getRemoteAddr());
					
					// 회원 로그인 기록 등록
					webLogService.insertUserLoginHistory(webAuthVO);
				}
			}

		} catch (Exception e) {
			// 로그인 실패
			webAuthVO.setLoginResult("FAILURE");
			webLogService.insertUserLoginHistory(webAuthVO);
			e.printStackTrace();
			return "error/500";
		}

		// 기관 코드 입력
		userInfo.setSchoolCode(organCd);
		// 세션 생성 및 HTTP응답을 받고 세션을 쿠키에 담고, response에 쿠기를 담음
		session.setAttribute("userInfo", userInfo);
		
		if("DI".equals(userType)) {
			return "redirect:/web/statistics/integrateStatistics.do";
		}else {
			return "redirect:/web/statistics/diagnosis.do";
		}
	}
	
	
	
	
	/**
	 * 기능   : 진단 결과지 로그인 - 기관장용
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 07. 25
	 * 수정일 : 2023. 07. 25
	 * 			 APP에서 JSON형식으로 전달 받은 회원의 ID와 비밀번호를 확인 후 JSON으로 반환
	 * @throws Exception 
	 */
	@PostMapping(value = {"/web/statistics/director/login.do"})
	public String webStatisticsDirectorLoginForm(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
       
		
		// 세션이 없을 경우 로그인 페이지로 보내주는 부분 필요
		logger.debug("===== WEB ===== WebLoginController ===== /web/statistics/login.do =====");
		
		// 로그인 후 조회시 필요한 것들
		int loginChkByIdPwd = 0;
		// 회원아이디와 기관코드 대조
		int loginchkByIdOrganCd = 0;
		int isIdExist = 0;
		String isEmailAuthEnabled = "N";
		
		HttpSession session = request.getSession(true);
		// 세션 유지 시간 30분
		session.setMaxInactiveInterval(60*30*1);
		
		String stUserId = null;
		String userPwd = request.getParameter("userPwd");
		String userType = null;
		// 로그인 시 링크에 포함되어있는 schoolCode를 받아서 저장
		String organCd = request.getParameter("schoolCode");
		String userId = organCd;
		
		WebAuthVO webAuthVO = new WebAuthVO();
		WebUserVO webUserVO = new WebUserVO();
		WebUserVO userInfo = new WebUserVO(); 
		
		// 비밀번호 암호화 
		AES256Util aes256Util = new AES256Util();
		userPwd = aes256Util.aesEncode(userPwd);
		
		// 오늘 일자 계산
		Date tmpDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
		String sysDate = sdf.format(tmpDate);
		
		// 회원 정보 및 상세정보 조회
		WebUserVO tmpWebUserVO = webUserService.selectUserInfo(userId);
		userType = tmpWebUserVO.getUserType();
		
		// 로그인 VO
		webAuthVO.setUserId(userId);
		webAuthVO.setUserPwd(userPwd);
		webAuthVO.setLoginDt(sysDate);
		webAuthVO.setUserType(userType);
		// 회원 VO
		webUserVO.setUserId(userId);
		webUserVO.setUserType(userType);
		
		
//		// 치과 로그인 일 경우 아이디 앞에 자동으로 치과 코드 추가
//		if(urlType.equals("dentist")) {
//			userId = organCd+"-"+userId;
//		}
		
		try {
			// 아이디 검증 ::: schoolCode를 검증해야함
			loginChkByIdPwd = webAuthService.loginChkByIdPwd(webAuthVO);
			if(loginChkByIdPwd == 0){
				// 0일 경우는 Database에 ID와 비밀번호가 틀린 것
				isIdExist = webAuthService.isIdExist(webAuthVO);
				if(isIdExist == 0) {
					 // ID가 존재하지 않을 경우
					model.addAttribute("msg", "등록 되어있지 않은 아이디입니다.");
				}else { 
					// Password가 틀렸을 경우
					model.addAttribute("msg", "비밀번호가 틀렸습니다. 다시 입력해주시기 바랍니다.");
				}
				model.addAttribute("loginUrl", loginUrl+"/login");
				return "/common/alertMessage";
				
			}else {
				
//				if(userType.equals("PR")) {
//					// 법정대리인 아이디로 피측정자 아이디 조회
//					stUserId = webUserService.selectStUserId(userId);
//					// 피측정자 정보 및 상세정보 조회
//					 //webUserVO = webUserService.selectUserInfo(userId);
//				}else {
//					stUserId = userId;
//				}
				
				// 본인(기관장) 인증 여부 확인
				isEmailAuthEnabled = webMailAuthService.isEmailAuthEnabled(userId);
				
				if("N".contains(isEmailAuthEnabled)) { 
					session.setAttribute("userId", userId);
					session.setAttribute("organCd", organCd);
					// 이메일 인증 화면
					return "/web/auth/emailAuth";
				 }
				// 로그인 관련 정보 확인 및 로그 기록
				// 로그인 일자 업데이트
				webLogService.updateLoginDt(webAuthVO);
				// 회원의 정보를 가져옴
				userInfo = webUserService.selectUserInfo(userId);
				userType = userInfo.getUserType();
				
				// 로그인 기록 VO
				webUserVO.setUserId(userInfo.getUserId());
				webUserVO.setUserName(userInfo.getUserName());
				webUserVO.setUserType(userInfo.getUserType());
				webUserVO.setLoginDt(userInfo.getLoginDt());
				webUserVO.setSchoolCode(organCd);
				webAuthVO.setUserNo(userInfo.getUserNo());
				webAuthVO.setLoginDt(userInfo.getLoginDt());
				webAuthVO.setLoginIp(request.getRemoteAddr());
				
				// 회원 로그인 기록 등록
				webLogService.insertUserLoginHistory(webAuthVO);
			}

		} catch (Exception e) {
			// 로그인 실패
			webAuthVO.setLoginResult("FAILURE");
			webLogService.insertUserLoginHistory(webAuthVO);
			e.printStackTrace();
			return "error/500";
		}

		// 기관 코드 입력
		userInfo.setSchoolCode(organCd);
		// 세션 생성 및 HTTP응답을 받고 세션을 쿠키에 담고, response에 쿠기를 담음
		session.setAttribute("userInfo", userInfo);
		
		if("DI".equals(userType)) {
			return "redirect:/web/statistics/integrateStatistics.do";
		}else {
			return "redirect:/web/statistics/diagnosis.do";
		}
	}
	
	
	
	
	
	/**
	 * 작성자 : 정주현
	 * 작성일 : 2022. 07. 18
	 * 기능   : 로그아웃
	 */
	@GetMapping(value = {"/web/statistics/logout.do"})
	public String logout(HttpServletRequest request, HttpSession session) {
		// 세션 종료
		session.invalidate();
		
//		String lang = request.getParameter("lang");
//		String urlType = request.getParameter("urlType");
//		
//		if(lang == null) lang = "ko";
//		if(urlType == null) urlType="";
//		
//		if(lang.equals("ko") && urlType.equals("statistics")) {
//			return "redirect:/";
//		}else if(lang.equals("en") && urlType.equals("statistics")){
//			return "redirect:/en";
//		}else {
//			return "redirect:/dentist";
//		}
		
		return "redirect:/login";
	}
	
	
	
	
	
	/**
	 * 작성자 : 정주현
	 * 작성일 : 2022. 07. 18
	 * 기능   : 로그아웃
	 */
	@GetMapping(value = {"/web/integrate/logout.do"})
	public String integrateLogout(HttpServletRequest request, HttpSession session) {
		// 세션 종료
		session.invalidate();
		
//		String lang = request.getParameter("lang");
//		String urlType = request.getParameter("urlType");
//		
//		if(lang == null) lang = "ko";
//		if(urlType == null) urlType="";
//		
//		if(lang.equals("ko") && urlType.equals("statistics")) {
//			return "redirect:/";
//		}else if(lang.equals("en") && urlType.equals("statistics")){
//			return "redirect:/en";
//		}else {
//			return "redirect:/dentist";
//		}
		
		return "redirect:/director/login";
	}
	
	
	
	
}
