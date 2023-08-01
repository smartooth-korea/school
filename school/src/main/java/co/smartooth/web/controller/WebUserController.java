package co.smartooth.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import co.smartooth.web.utils.AES256Util;
import co.smartooth.web.service.WebLocationService;
import co.smartooth.web.service.WebOrganService;
import co.smartooth.web.service.WebTeethService;
import co.smartooth.web.service.WebUserService;
import co.smartooth.web.vo.WebLocationVO;
import co.smartooth.web.vo.WebOrganVO;
import co.smartooth.web.vo.WebTeethInfoVO;
import co.smartooth.web.vo.WebUserVO;


/**
 * 작성자 : 정주현
 * 작성일 : 2022. 11. 28
 * 수정일 : 2023. 07. 28
 * 서버 분리 : 2023. 07. 28
 * @RestController를 쓰지 않는 이유는 몇 안되는 mapping에 jsp를 반환해줘야하는게 있으므로 @Controller를 사용한다.
 * @RestAPI로 반환해야할 경우 @ResponseBody를 사용하여 HashMap으로 return 해준다.
 */
@Controller
public class WebUserController {

	@Value("${loginUrl}")
	private String loginUrl;

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired(required = false)
	private WebUserService webUserService;

	@Autowired(required = false)
	private WebTeethService webTeethService;
//
//	@Autowired(required = false)
//	private WebDiagnosisService webDiagnosisService;
//
	@Autowired(required = false)
	private WebOrganService webOrganService;

	@Autowired(required = false)
	private WebLocationService webLocationService;

	
	
	/**
	 * 작성자 : 정주현
	 * 작성일 : 2022. 11. 28
	 * 기능 : 첫 로그인 시 비밀번호 변경
	 */
	@PostMapping(value = { "/web/user/passwordChage.do" })
	public String passwordChage(@RequestParam Map<String, String> paramMap, HttpServletRequest request, HttpSession session, Model model) throws Exception {

		
	
		// 회원 아이디
		String userId = paramMap.get("userId");
		// 회원 비밀번호
		String userPwd = paramMap.get("userPwd");
		
		AES256Util aes256Util = new AES256Util();
		userPwd = aes256Util.aesEncode(userPwd);
		
		webUserService.updateUserPwd(userId, userPwd);
		
		model.addAttribute("userId", userId);
		model.addAttribute("updatePwd", "Y");
		
		// PWD가 틀렸을 경우
		model.addAttribute("msg", "비밀번호가 변경되었습니다.");
		
		// 하드코딩
		// model.addAttribute("loginUrl", "premium.smartooth.co:8090/login");
		model.addAttribute("loginUrl", "localhost:8090/login");
		return "/common/alertMessage";

	}
	
	
	
	/**
	 * 작성자 : 정주현
	 * 작성일 : 2022. 03. 09
	 * 기능 : 개인정보 동의서 화면
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(value = { "/web/user/agreement" })
	public String agreement(@RequestParam Map<String, String> paramMap, HttpServletRequest request, HttpSession session, Model model) throws Exception {
		
		return "/web/user/agreement";

	}
	
	
	
	/**
	 * 작성자 : 정주현
	 * 작성일 : 2022. 03. 16
	 * 기능 : 개인정보 동의서로 회원가입
	 */
	@PostMapping(value = { "/web/user/insertUserInfo.do" })
	public String insertUserInfo(@RequestParam Map<String, String> paramMap, HttpServletRequest request, HttpSession session, Model model, RedirectAttributes redirectAttributes) throws Exception {
		
		
		// 법정대리인
		String prUserId = null;
		String prUserName = null;
		String paUserPwd = null;
		String paUserTelNo = null;
		String paUserTelNo1 = null;
		String paUserTelNo2 = null;
		String paUserTelNo3 = null;
		
		// 피측정자
		String userId = null;
		String userPwd = null;
		String userName = null;
		String userTelNo = null;
		String userTelNo1 = null;
		String userTelNo2 = null;
		String userTelNo3 = null;
		String userBritday = null;
		String userSex = null;
		String strBirthday = null;
		int userSeqNo = 0;
		
		// 기관 및 부서 정보
		String schoolCode = null;
		String classCode = null;
		
		// 주소 정보
		String countryNm = null;
		String countryCd = null;
		String organType = null;
		
		// 기관 주소
		String organSidoNm = null;
		String organSigunguNm = null;
		String organEupmyeondongNm = null;
		String addressDetail = null;
		
		// 치아 정보 (하드코딩) - 모든 치아 정상
		//String userTeethStatus = "100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100";
		
		String userTeethStatus = "100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100|100";
		
		// 피측정자
		WebUserVO webStUserVO = new WebUserVO();
		// 법정대리인
		WebUserVO webPrUserVO = new WebUserVO();
		// 기관 정보
		WebOrganVO webOrganVO = new WebOrganVO();
		// 지역 정보 (나라)
		WebLocationVO webLocationVO = new WebLocationVO();
		// 치아 상태 정보
		WebTeethInfoVO webTeethInfoVO = new WebTeethInfoVO();

		// 암호화 클래스
		AES256Util aes256Util = new AES256Util();
		
		
		String userType = paramMap.get("userType");
		String isOverourteetn = paramMap.get("userAgeCheck");
		
		// 만 14세 미만일 경우 : 법정 대리인, 자녀의 정보 입력
		if(userType.equals("ST")) {
			
			// 피측정자 파라미터
			userName = paramMap.get("stUserName");
			userSex = paramMap.get("stUserSex");
			userBritday = paramMap.get("stUserBirthday");
			// 법정대리인 파라미터
			prUserName = paramMap.get("paUserName");
			paUserTelNo1 = paramMap.get("paUserTelNo1");
			paUserTelNo2 = paramMap.get("paUserTelNo2");
			paUserTelNo3 = paramMap.get("paUserTelNo3");
			paUserTelNo = paUserTelNo1+"-"+paUserTelNo2+"-"+paUserTelNo3;
			// 학교 정보
			schoolCode = paramMap.get("schoolCode");
			classCode = paramMap.get("classCode1");
			
			// 기관 코드로 주소 조회 :: 피측정자 (학생) 주소 정보 입력 시 필요
			webOrganVO = webOrganService.selectOrganInfo(schoolCode);
			// 주소 정보 : 시도
			organSidoNm = webOrganVO.getOrganSidoNm();
			// 주소 정보 : 시군구
			organSigunguNm = webOrganVO.getOrganSigunguNm();
			// 주소 정보 : 읍면동
			organEupmyeondongNm = webOrganVO.getOrganEupmyeondongNm();
			// 상세 주소
			addressDetail = organSidoNm+" "+organSigunguNm+" "+organEupmyeondongNm;
			
			// 피측정자 (학생) 회원 주소 (유치원 주소 기준)
			countryCd = schoolCode.substring(0,2); 
			// 나라 코드로 국가명 조회
			webLocationVO = webLocationService.selectNationalInfo(countryCd);
			countryNm = webLocationVO.getNationalNameKor();
			// 기관 유형
			organType = schoolCode.substring(2,4);
			
			// 피측정자 (학생) 회원 시퀀스
			userSeqNo = webOrganVO.getUserSeqNo();
			// 피측정자 아이디 생성
			userId = countryCd+"ST"+schoolCode.substring(4, schoolCode.length())+String.format("%03d", userSeqNo);
			// 피측정자 (학생) 회원 시퀀스 증가
			userSeqNo++;

			// 비밀번호 생성 규칙 2019-01-01 >> 190101 
			strBirthday = userBritday.substring(2, userBritday.length()).replaceAll("-", "");

			// 피측정자 회원 비밀번호 :: 생년월일(190101) 
			userPwd = aes256Util.aesEncode(strBirthday);
			
			// 피측정자 회원 정보 VO
			webStUserVO.setUserId(userId);
			webStUserVO.setUserName(userName);
			webStUserVO.setUserPwd(userPwd);
			webStUserVO.setUserType("ST");
			webStUserVO.setUserBirthday(userBritday);
			webStUserVO.setUserCountry(countryCd);
			webStUserVO.setUserTelNo(paUserTelNo);
 			webStUserVO.setUserSex(userSex);
			webStUserVO.setCountryNm(countryNm);
			webStUserVO.setSidoNm(organSidoNm);
			webStUserVO.setSigunguNm(organSigunguNm);
			webStUserVO.setEupmyeondongNm(organEupmyeondongNm);
			webStUserVO.setAddrDetail(addressDetail);
			
			// 피측정자 회원 치아 상태 등록
			webTeethInfoVO.setUserId(userId);
			webTeethInfoVO.setTeethStatus(userTeethStatus);
			
			// 법정대리인 회원 아이디 :: 자녀이름+생년월일(190101 :: stTmpUserPwd)+전화번호 뒷자리4자리
			prUserId = userName+strBirthday+paUserTelNo3;
			
			// 법정대리인 회원 비밀번호 :: 휴대전화 번호 뒷자리 4자리 
			paUserPwd = aes256Util.aesEncode(paUserTelNo3);
			
			// 법정대리인 회원 정보 VO
			webPrUserVO.setUserId(prUserId);
			webPrUserVO.setUserName(prUserName);
			webPrUserVO.setUserPwd(paUserPwd);
			webPrUserVO.setUserType("PR");
			webPrUserVO.setUserTelNo(paUserTelNo);
			webPrUserVO.setUserCountry(countryCd);
			webPrUserVO.setCountryNm(countryNm);
			webPrUserVO.setSidoNm(organSidoNm);
			webPrUserVO.setSigunguNm(organSigunguNm);
			webPrUserVO.setEupmyeondongNm(organEupmyeondongNm);
			webPrUserVO.setAddrDetail(addressDetail);
			
			// 기관 정보 VO
			webOrganVO.setUserSeqNo(userSeqNo);
			webOrganVO.setSchoolCode(schoolCode);
			
			
			// 계정 등록 (피측정자 회원) - ST_USER
			webUserService.insertUserInfo(webStUserVO);
			// 계정 상세 정보 등록 (피측정자 - 학생) - ST_STUDENT_USER_DETAL
			webUserService.insertStUserDetail(userId, organType, classCode);
			// 계정 등록 (법정대리인 회원) - ST_USER
			webUserService.insertUserInfo(webPrUserVO);
			// 계정 상세 정보 등록 (법정대리인) - ST_PARENT_USER_DETAL
			webUserService.insertPaUserDetail(prUserId, userId);
			// 치아 상태
			webTeethService.insertUserTeethInfo(webTeethInfoVO);
			// 피측정자 회원 시퀀스 증가
			webOrganService.updateOrganUserSeqNo(webOrganVO);
			
		}else {
			
			// 만 14세 이상일 경우
			// 피측정자 파라미터
			userName = paramMap.get("userName");
			userSex = paramMap.get("userSex");
			userBritday = paramMap.get("userBirthday");
			userTelNo1 = paramMap.get("userTelNo1");
			userTelNo2 = paramMap.get("userTelNo2");
			userTelNo3 = paramMap.get("userTelNo3");
			userTelNo = userTelNo1+"-"+userTelNo2+"-"+userTelNo3;
			// 기관 정보
			schoolCode = paramMap.get("schoolCode");
			// 기관 부서 정보
			classCode = paramMap.get("classCode2");
			
			// 기관 코드로 주소 조회
			webOrganVO = webOrganService.selectOrganInfo(schoolCode);
			// 주소 정보 : 시도
			organSidoNm = webOrganVO.getOrganSidoNm();
			// 주소 정보 : 시군구
			organSigunguNm = webOrganVO.getOrganSigunguNm();
			// 주소 정보 : 읍면동
			organEupmyeondongNm = webOrganVO.getOrganEupmyeondongNm();
			// 상세 주소
			addressDetail = organSidoNm+" "+organSigunguNm+" "+organEupmyeondongNm;
			// 국가 코드
			countryCd = schoolCode.substring(0,2); 
			// 국가 코드로 국가명 조회
			webLocationVO = webLocationService.selectNationalInfo(countryCd);
			// 국가명
			countryNm = webLocationVO.getNationalNameKor();
			// 기관 유형
			organType = schoolCode.substring(2,4);
			
			// 피측정자 시퀀스
			userSeqNo = webOrganVO.getUserSeqNo();
			// 피측정자 아이디 생성
			userId = countryCd+"ST"+schoolCode.substring(4, schoolCode.length())+String.format("%03d", userSeqNo);
			// 피측정자 시퀀스 증가
			userSeqNo++;

			// 비밀번호 생성 규칙 2019-01-01 >> 190101 
			strBirthday = userBritday.substring(2, userBritday.length()).replaceAll("-", "");

			// 피측정자 비밀번호 :: 생년월일(190101) 
			userPwd = aes256Util.aesEncode(strBirthday);
			
			// 피측정자 정보 VO
			webStUserVO.setUserId(userId);
			webStUserVO.setUserName(userName);
			webStUserVO.setUserPwd(userPwd);
			webStUserVO.setUserType("PT");
			webStUserVO.setUserBirthday(userBritday);
			webStUserVO.setUserCountry(countryCd);
			webStUserVO.setUserTelNo(userTelNo);
			webStUserVO.setUserSex(userSex);
			webStUserVO.setCountryNm(countryNm);
			webStUserVO.setSidoNm(organSidoNm);
			webStUserVO.setSigunguNm(organSigunguNm);
			webStUserVO.setEupmyeondongNm(organEupmyeondongNm);
			webStUserVO.setAddrDetail(addressDetail);
			
			// 피측정자 치아 상태 등록
			webTeethInfoVO.setUserId(userId);
			webTeethInfoVO.setTeethStatus(userTeethStatus);
			
			// 기관 정보 VO
			webOrganVO.setUserSeqNo(userSeqNo);
			webOrganVO.setSchoolCode(schoolCode);
			
			// 계정 등록 (피측정자) - ST_USER
			webUserService.insertUserInfo(webStUserVO);
			// 계정 상세 정보 등록 (피측정자) - ST_STUDENT_USER_DETAL
			webUserService.insertStUserDetail(userId, organType, classCode);
			// 치아 상태
			webTeethService.insertUserTeethInfo(webTeethInfoVO);
			// 피측정자 회원 시퀀스 증가
			webOrganService.updateOrganUserSeqNo(webOrganVO);
		}
		
		
		
		// 리다이렉션 시 파라미터를 전달
		redirectAttributes.addFlashAttribute("msg", "제출이 완료 되었습니다. 감사합니다.");
		
		return "redirect:/web/user/agreement";

	}
	
	
	

}
