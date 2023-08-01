package co.smartooth.app.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import co.smartooth.app.service.DiagnosisService;
import co.smartooth.app.service.TeethService;
import co.smartooth.app.service.UserService;
import co.smartooth.app.vo.DiagnosisVO;
import co.smartooth.app.vo.TeethInfoVO;
import co.smartooth.app.vo.TeethMeasureVO;
import co.smartooth.app.vo.ToothMeasureVO;
import co.smartooth.app.vo.UserVO;
import lombok.extern.slf4j.Slf4j;


/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 07. 14
 * 수정일 : 2023. 06. 28
 * @RestController를 쓰지 않는 이유는 몇 안되는 mapping에 jsp를 반환해줘야하는게 있으므로 @Controller를 사용한다.
 * @RestAPI로 반환해야할 경우 @ResponseBody를 사용하여 HashMap으로 return 해준다.
 */
@Slf4j
@RestController
public class TeethController {

	Logger logger = LoggerFactory.getLogger(getClass());

	
	@Autowired(required = false)
	private TeethService teethService;

	
	@Autowired(required = false)
	private DiagnosisService diagnosisService;

	
	@Autowired(required = false)
	private UserService userService;

	
	// 인증 패스
	// private static boolean tokenValidation = false;
	private static boolean tmpTokenValidation = true;

	
	// 모든 치아의 값을 -99로 초기화
	public TeethMeasureVO setTeethInit(TeethMeasureVO teethMeasureVO) {
		teethMeasureVO.setT01(-99);
		teethMeasureVO.setT02(-99);
		teethMeasureVO.setT03(-99);
		teethMeasureVO.setT04(-99);
		teethMeasureVO.setT05(-99);
		teethMeasureVO.setT06(-99);
		teethMeasureVO.setT07(-99);
		teethMeasureVO.setT08(-99);
		teethMeasureVO.setT09(-99);
		teethMeasureVO.setT10(-99);
		teethMeasureVO.setT11(-99);
		teethMeasureVO.setT12(-99);
		teethMeasureVO.setT13(-99);
		teethMeasureVO.setT14(-99);
		teethMeasureVO.setT15(-99);
		teethMeasureVO.setT16(-99);
		teethMeasureVO.setT17(-99);
		teethMeasureVO.setT18(-99);
		teethMeasureVO.setT19(-99);
		teethMeasureVO.setT20(-99);
		teethMeasureVO.setT21(-99);
		teethMeasureVO.setT22(-99);
		teethMeasureVO.setT23(-99);
		teethMeasureVO.setT24(-99);
		teethMeasureVO.setT25(-99);
		teethMeasureVO.setT26(-99);
		teethMeasureVO.setT27(-99);
		teethMeasureVO.setT28(-99);
		teethMeasureVO.setT29(-99);
		teethMeasureVO.setT30(-99);
		teethMeasureVO.setT31(-99);
		teethMeasureVO.setT32(-99);
		teethMeasureVO.setT33(-99);
		teethMeasureVO.setT34(-99);
		teethMeasureVO.setT35(-99);
		teethMeasureVO.setT36(-99);
		teethMeasureVO.setT37(-99);
		teethMeasureVO.setT38(-99);
		teethMeasureVO.setT39(-99);
		teethMeasureVO.setT40(-99);
		teethMeasureVO.setT41(-99);
		teethMeasureVO.setT42(-99);
		teethMeasureVO.setT43(-99);
		teethMeasureVO.setT44(-99);
		teethMeasureVO.setT45(-99);
		teethMeasureVO.setT46(-99);
		teethMeasureVO.setT47(-99);
		teethMeasureVO.setT48(-99);
		teethMeasureVO.setT49(-99);
		teethMeasureVO.setT50(-99);
		teethMeasureVO.setT51(-99);
		teethMeasureVO.setT52(-99);
		teethMeasureVO.setT53(-99);
		teethMeasureVO.setT54(-99);
		teethMeasureVO.setT55(-99);
		teethMeasureVO.setT56(-99);
		return teethMeasureVO;
	}
	
	
	
	/**
	 * 기능 : 회원의 치아 상태 값 등록
	 * 작성자 : 정주현
	 * 작성일 : 2022. 05. 20
	 * 수정일 : 2023. 06. 28
	 */
	@PostMapping(value = { "/premium/user/insertUserTeethInfo.do" })
	@ResponseBody
	public HashMap<String, Object> insertUserTeethInfo(@RequestBody HashMap<String, Object> paramMap) {

		logger.debug("========== TeethController ========== /premium/user/iinsertUserTeethInfo.do ==========");
		logger.debug("========== TeethController ========== /premium/user/iinsertUserTeethInfo.do ==========");
		logger.debug("========== TeethController ========== /premium/user/iinsertUserTeethInfo.do ==========");
		logger.debug("========== TeethController ========== /premium/user/iinsertUserTeethInfo.do ==========");

		String userId = null;
		String userNo = null;
		String teethStatus = null;

		HashMap<String, Object> hm = new HashMap<String, Object>();
		TeethInfoVO teethInfoVO = new TeethInfoVO();

		userId = (String) paramMap.get("userId");
		userNo = (String) paramMap.get("userNo");
		teethStatus = (String) paramMap.get("teethStatus");

		// Parameter = userId 값 검증 (Null 체크 및 공백 체크)
		if (userId == null || userId.equals("") || userId.equals(" ")) {
			hm.put("code", "401");
			hm.put("msg", "There is no userId parameter.");
			return hm;
		}

		try {
			teethInfoVO.setUserId(userId);
			teethInfoVO.setUserNo(userNo);
			teethInfoVO.setTeethStatus(teethStatus);
			// 회원 치아 상태 INSERT
			teethService.insertUserTeethInfo(teethInfoVO);
			hm.put("code", "000");
			hm.put("msg", "Success");
		} catch (Exception e) {
			hm.put("code", "500");
			hm.put("msg", "Server Error");
			e.printStackTrace();
		}
		return hm;
	}

	
	
	/**
	 * 기능 : 회원의 치아 측정 값을 등록 또는 업데이트
	 * 작성자 : 정주현
	 * 작성일 : 2022. 05. 25
	 * 수정일 : 2023. 06. 28
	 */
	@PostMapping(value = { "/premium/user/insertUserMeasureValue.do" })
	@ResponseBody
	public HashMap<String, Object> insertUserMeasureValue(@RequestBody HashMap<String, Object> paramMap,
			HttpServletRequest request) throws Exception {
		logger.debug("========== DentalTeethController ========== insertUserMeasureValue.do ==========");

		String measurerId = null;
		String userId = null;
		String userNo = null;
		// String userAuthToken = null;

		// 32개 치아 측정 값
		int[] teethValue = new int[32];

		int cavityCaution = 0;
		int cavityDanger = 0;

		// 정상 수치 개수
		int cavityNormalCnt = 0;
		// 주의 수치 개수
		int cavityCautionCnt = 0;
		// 충치 수치 개수
		int cavityDangerCnt = 0;

		// 데이터 존재 유무
		int isExistRow = 0;

		HashMap<String, Object> hm = new HashMap<String, Object>();

		HashMap<String, Integer> cavityLevel = new HashMap<String, Integer>();

		TeethMeasureVO teethMeasureVO = new TeethMeasureVO();

		// 오늘 날짜 구하기 (SYSDATE)
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String sysDate = now.format(formatter);

		// 측정인 아이디
		measurerId = (String) paramMap.get("measurerId");
		userId = (String) paramMap.get("userId");

		// Parameter = userId 값 검증 (Null 체크 및 공백 체크)
		if (userId == null || userId.equals("") || userId.equals(" ")) {
			hm.put("code", "401");
			hm.put("msg", "There is no userId parameter.");
			return hm;
		}

		for (int i = 0; i < teethValue.length + 1; i++) {
			if (i == 0) {
				teethValue[i] = Integer.parseInt((String) paramMap.get("t0" + (i + 1)));
				i++;
			} else if (i < 10) {
				teethValue[i - 1] = Integer.parseInt((String) paramMap.get("t0" + i));
			} else {
				teethValue[i - 1] = Integer.parseInt((String) paramMap.get("t" + i));
			}
		}

		// CAVITY_LEVEL 분류 부분 - 충치 단계별 수치 조회
		cavityLevel = teethService.selectCavityLevel();

		cavityCaution = cavityLevel.get("cavityCaution");
		cavityDanger = cavityLevel.get("cavityDanger");

		for (int i = 0; i < teethValue.length; i++) {
			if (teethValue[i] < cavityCaution) { // 12 이하일 경우 정상 치아
				cavityNormalCnt++;
			} else if (teethValue[i] >= cavityCaution || teethValue[i] < cavityDanger) { // 13이상 19이하일 경우 충치 상태 주의
				cavityCautionCnt++;
			} else {
				cavityDangerCnt++;
			}
		}

		userNo = (String) paramMap.get("userNo");

		// userAuthToken = request.getHeader("Authorization");
		// TOKEN 검증
		// JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
		// tokenValidation = jwtTokenUtil.validateToken(userAuthToken);

		if (tmpTokenValidation) {
			try {

				teethMeasureVO.setT01(teethValue[0]);
				teethMeasureVO.setT02(teethValue[1]);
				teethMeasureVO.setT03(teethValue[2]);
				teethMeasureVO.setT04(teethValue[3]);
				teethMeasureVO.setT05(teethValue[4]);
				teethMeasureVO.setT06(teethValue[5]);
				teethMeasureVO.setT07(teethValue[6]);
				teethMeasureVO.setT08(teethValue[7]);
				teethMeasureVO.setT09(teethValue[8]);
				teethMeasureVO.setT10(teethValue[9]);
				teethMeasureVO.setT11(teethValue[10]);
				teethMeasureVO.setT12(teethValue[11]);
				teethMeasureVO.setT13(teethValue[12]);
				teethMeasureVO.setT14(teethValue[13]);
				teethMeasureVO.setT15(teethValue[14]);
				teethMeasureVO.setT16(teethValue[15]);
				teethMeasureVO.setT17(teethValue[16]);
				teethMeasureVO.setT18(teethValue[17]);
				teethMeasureVO.setT19(teethValue[18]);
				teethMeasureVO.setT20(teethValue[29]);
				teethMeasureVO.setT21(teethValue[20]);
				teethMeasureVO.setT22(teethValue[21]);
				teethMeasureVO.setT23(teethValue[22]);
				teethMeasureVO.setT24(teethValue[23]);
				teethMeasureVO.setT25(teethValue[24]);
				teethMeasureVO.setT26(teethValue[25]);
				teethMeasureVO.setT27(teethValue[26]);
				teethMeasureVO.setT28(teethValue[27]);
				teethMeasureVO.setT29(teethValue[28]);
				teethMeasureVO.setT30(teethValue[29]);
				teethMeasureVO.setT31(teethValue[30]);
				teethMeasureVO.setT32(teethValue[31]);

				teethMeasureVO.setUserId(userId);
				teethMeasureVO.setUserNo(userNo);
				teethMeasureVO.setMeasureDt(sysDate);
				teethMeasureVO.setMeasurerId(measurerId);
				teethMeasureVO.setCavityNormal(cavityNormalCnt);
				teethMeasureVO.setCavityCaution(cavityCautionCnt);
				teethMeasureVO.setCavityDanger(cavityDangerCnt);

				// 회원 치아 측정 값을 저장하기 위해 현재 회원이 측정한 측정 값이 오늘 데이터인지 확인 후 값 반환(0 : 오늘X / 1: 오늘)
				isExistRow = teethService.isExistSysDateRow(userId);
				if (isExistRow == 0) { // 0일 경우는 Database에 값이 없는 경우 ::: INSERT
					teethService.insertUserTeethMeasureValue(teethMeasureVO);
				} else { // 1이상일 경우 Database에 값이 있는 경우 ::: UPDATE
					teethService.updateUserTeethMeasureValue(teethMeasureVO);
				}

				hm.put("code", "000");
				hm.put("msg", "Success");

			} catch (Exception e) {
				hm.put("code", "500");
				hm.put("msg", "Server Error");
				e.printStackTrace();
			}
		} else {
			hm.put("code", "400");
			hm.put("msg", "The token is not valid.");
		}
		return hm;
	}

	
	
	/**
	 * 기능 : 회원의 치아 상태 값 조회
	 * 작성자 : 정주현
	 * 작성일 : 2022. 05. 20
	 * 수정일 : 2023. 06. 28
	 */
	@PostMapping(value = { "/premium/user/selectUserTeethInfo.do" })
	@ResponseBody
	public HashMap<String, Object> selectUserTeethInfo(@RequestBody HashMap<String, Object> paramMap,
			HttpServletRequest request) {

		logger.debug("========== DentalTeethController ========== selectUserTeethInfo.do ==========");

		String userId = null;
		// String userNo = null;
		// String userAuthToken = null;

		HashMap<String, Object> hm = new HashMap<String, Object>();
		List<TeethInfoVO> userTeethInfo = new ArrayList<TeethInfoVO>();
		UserVO userVO = new UserVO();

		// Parameter = userId 값 검증 (Null 체크 및 공백 체크)
		userId = (String) paramMap.get("userId");
		if (userId == null || userId.equals("") || userId.equals(" ")) {
			hm.put("code", "401");
			hm.put("msg", "There is no userId parameter.");
			return hm;
		}

		// userNo = (String) paramMap.get("userNo");
		// userAuthToken = request.getHeader("Authorization");
		// JWT TOKEN 검증
		// JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
		// tokenValidation = jwtTokenUtil.validateToken(userAuthToken);

		if (tmpTokenValidation) {

			try {
				userVO.setUserId((String) paramMap.get("userId"));
				userVO.setUserNo((String) paramMap.get("userNo"));
				// 회원의 치아 상태 값 조회
				userTeethInfo = teethService.selectUserTeethInfo(userVO);

				hm.put("userTeethInfo", userTeethInfo);
				hm.put("code", "000");
				hm.put("msg", "Success");

			} catch (Exception e) {
				hm.put("code", "500");
				hm.put("msg", "Server Error");
				e.printStackTrace();
			}

		} else {
			hm.put("code", "400");
			hm.put("msg", "The token is not valid.");
		}
		return hm;
	}

	
	
	/**
	 * 기능 : 회원의 치아 측정 값 조회
	 * 작성자 : 정주현
	 * 작성일 : 2022. 05. 16
	 * 수정일 : 2023. 06. 28
	 * 기간 조회 (startDt, endDt)
	 */
//	@PostMapping(value = {"/app/user/selectUserTeethMeasureValue.do"})
	@PostMapping(value = { "/premium/user/selectUserTeethMeasureValue.do" })
	@ResponseBody
	public HashMap<String, Object> selectUserMeasureValue(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception {


		logger.debug("========== TeethController ========== selectUserTeethMeasureValue.do ==========");
		logger.debug("========== TeethController ========== selectUserTeethMeasureValue.do ==========");
		logger.debug("========== TeethController ========== selectUserTeethMeasureValue.do ==========");
		logger.debug("========== TeethController ========== selectUserTeethMeasureValue.do ==========");

		String userNo = null;
		String userId = null;
		// String userAuthToken = null;
		String startDt = null;
		String endDt = null;

		HashMap<String, Object> hm = new HashMap<String, Object>();
		// TeethMeasureVO userTeethValues = new TeethMeasureVO();
		List<TeethMeasureVO> userTeethValues = new ArrayList<TeethMeasureVO>();
		TeethMeasureVO teethInfoVO = new TeethMeasureVO();

		userNo = (String) paramMap.get("userNo");
		userId = (String) paramMap.get("userId");
		startDt = (String) paramMap.get("startDt");
		endDt = (String) paramMap.get("endDt");

		// Parameter = userId 값 검증 (Null 체크 및 공백 체크)
		userId = (String) paramMap.get("userId");
		if (userId == null || userId.equals("") || userId.equals(" ")) {
			hm.put("code", "401");
			hm.put("msg", "There is no userId parameter.");
			return hm;
		}

		// userAuthToken = request.getHeader("Authorization");
		// TOKEN 검증
		// JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
		// tokenValidation = jwtTokenUtil.validateToken(userAuthToken);

		if (tmpTokenValidation) {
			try {

				teethInfoVO.setUserNo(userNo);
				teethInfoVO.setUserId(userId);
				teethInfoVO.setStartDt(startDt);
				teethInfoVO.setEndDt(endDt);

				// 회원의 치아 측정 값 조회 (기간)
				userTeethValues = teethService.selectUserTeethMeasureValue(teethInfoVO);

				hm.put("userTeethValues", userTeethValues);
				hm.put("code", "000");
				hm.put("msg", "Success");

			} catch (Exception e) {

				hm.put("code", "500");
				hm.put("msg", "Server Error");
				e.printStackTrace();

			}
		} else {

			hm.put("code", "400");
			hm.put("msg", "The token is not valid.");

		}

		return hm;
	}

	/**
	 * 기능 : 회원 치아 개별 측정 값 조회 (기간)
	 * 작성자 : 정주현
	 * 작성일 : 2022. 5. 26
	 * 파라미터 : 기간 조회 (startDt, endDt)
	 */
	@SuppressWarnings("unused")
	@PostMapping({ "/premium/user/selectUserToothMeasureValue.do" })
	@ResponseBody
	public HashMap<String, Object> selectUserMeasureToothValue(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception {

		
		this.logger.debug("========== DentalTeethController ========== premium.selectUserToothMeasureValue.do ==========");

		int isExistRow = 0;
		// 회원 아이디
		String userId = null;
		// 회원 번호
		String userNo = null;
		// 측정 치아 번호
		String toothNo = null;
		// 치아 측정 값
		String toothValue = null;
		String startDt = null;
		String endDt = null;
		// 회원 치아 정보
		String teethStatus = null;

		// 회원 유저 토큰 (인증)
		// String userAuthToken = null;

		// 유치 치아 배열
		int[] teethValue = new int[20];
		// 영구치 치아 배열
		int[] permTeethValue = new int[4];

		// 유치 정상 치아 개수
		int cavityNormalCnt = 0;
		// 유치 주의 치아 개수
		int cavityCautionCnt = 0;
		// 유치 충치 치아 개수
		int cavityDangerCnt = 0;

		// 영구치 일반 치아 개수
		// int permCavityNomalCnt = 0;
		// 영구치 주의 치아 개수
		// int permCavityCautionCnt = 0;
		// 영구치 충치 치아 개수
		// int permCavityDangerCnt = 0;

		// 충치 단계 - 주의
		Integer cavityCaution = 0;
		// 충치 단계 - 위험
		Integer cavityDanger = 0;

		HashMap<String, Object> hm = new HashMap<String, Object>();
		HashMap<String, Integer> cavityLevel = new HashMap<String, Integer>();

		List<ToothMeasureVO> userToothValues = new ArrayList<ToothMeasureVO>();
		List<TeethMeasureVO> userTeethValues = new ArrayList<TeethMeasureVO>();
//			ToothMeasureVO userToothValues = new ToothMeasureVO();
//			TeethMeasureVO userTeethValues = new TeethMeasureVO();

		ToothMeasureVO toothMeasureVO = new ToothMeasureVO();
		TeethMeasureVO teethMeasureVO = new TeethMeasureVO();

		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String sysDate = now.format(formatter);

		userId = (String) paramMap.get("userId");

		if (userId == null || userId.equals("") || userId.equals(" ")) {
			hm.put("code", "401");
			hm.put("msg", "There is no userId parameter.");
			return hm;
		}

		// userAuthToken = request.getHeader("Authorization");
		// JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
		// tokenValidation = jwtTokenUtil.validateToken(userAuthToken);

		if (tmpTokenValidation) {

			try {

				userNo = (String) paramMap.get("userNo");
				toothNo = (String) paramMap.get("toothNo");
				toothValue = (String) paramMap.get("toothValue");
				startDt = (String) paramMap.get("startDt");
				endDt = (String) paramMap.get("endDt");

				toothMeasureVO.setUserId(userId);
				toothMeasureVO.setUserNo(userNo);
				// toothMeasureVO.setStartDt(startDt);
				// toothMeasureVO.setEndDt(endDt);
				toothMeasureVO.setToothNo(toothNo);
				toothMeasureVO.setMeasureDt(sysDate);

				teethMeasureVO.setUserNo(userNo);
				teethMeasureVO.setUserId(userId);
				teethMeasureVO.setStartDt(sysDate);
				teethMeasureVO.setEndDt(sysDate);
				teethMeasureVO.setMeasureDt(sysDate);

				isExistRow = this.teethService.isExistSysDateRow(userId).intValue();

				if (toothValue != null && !toothValue.equals("") && !toothValue.equals(" ")) {
					toothMeasureVO.setToothValue(Integer.parseInt(toothValue));
					if (isExistRow == 0) {
						teethService.insertUserToothMeasureValue(toothMeasureVO);
					} else {
						teethService.updateUserToothMeasureValue(toothMeasureVO);
					}
				} else if (isExistRow == 0) {
					List<DiagnosisVO> diagList = diagnosisService.selectDiagDept2List();
					String diagCd = "";
					for (int i = 0; i < diagList.size(); i++) {
						if (i == diagList.size() - 1) {
							diagCd = diagCd + diagList.get(i).getDiagCd() + ":" + diagList.get(i).getDiagNo() + ":0";
						} else {
							diagCd = diagCd + diagList.get(i).getDiagCd() + ":" + diagList.get(i).getDiagNo() + ":0|";
						}
					}
					teethMeasureVO.setDiagCd(diagCd);
					teethService.insertUserTeethMeasureValue(teethMeasureVO);
				}

				// 현재 측정한 치아의 값과 측정된 치아의 지난 측정 결과 값 RETURN
				userToothValues = teethService.selectUserToothMeasureValue(toothMeasureVO);
				// 현재 측정한 전체 치아의 값과 측정된 전체 치아의 값 RETURN
				userTeethValues = teethService.selectUserTeethMeasureValue(teethMeasureVO);

				cavityLevel = teethService.selectCavityLevel();
				// 수치 단계 : 데이터베이스 값 조회
				cavityCaution = Integer.parseInt(String.valueOf(cavityLevel.get("CAVITY_CAUTION")));
				cavityDanger = Integer.parseInt(String.valueOf(cavityLevel.get("CAVITY_DANGER")));

				// 유치
				teethValue[0] = userTeethValues.get(0).getT04();
				teethValue[1] = userTeethValues.get(0).getT05();
				teethValue[2] = userTeethValues.get(0).getT06();
				teethValue[3] = userTeethValues.get(0).getT07();
				teethValue[4] = userTeethValues.get(0).getT08();
				teethValue[5] = userTeethValues.get(0).getT09();
				teethValue[6] = userTeethValues.get(0).getT10();
				teethValue[7] = userTeethValues.get(0).getT11();
				teethValue[8] = userTeethValues.get(0).getT12();
				teethValue[9] = userTeethValues.get(0).getT13();
				teethValue[10] = userTeethValues.get(0).getT20();
				teethValue[11] = userTeethValues.get(0).getT21();
				teethValue[12] = userTeethValues.get(0).getT22();
				teethValue[13] = userTeethValues.get(0).getT23();
				teethValue[14] = userTeethValues.get(0).getT24();
				teethValue[15] = userTeethValues.get(0).getT25();
				teethValue[16] = userTeethValues.get(0).getT26();
				teethValue[17] = userTeethValues.get(0).getT27();
				teethValue[18] = userTeethValues.get(0).getT28();
				teethValue[19] = userTeethValues.get(0).getT29();

				// 영구치 어금니
				permTeethValue[0] = userTeethValues.get(0).getT03();
				permTeethValue[1] = userTeethValues.get(0).getT14();
				permTeethValue[2] = userTeethValues.get(0).getT19();
				permTeethValue[3] = userTeethValues.get(0).getT30();

				// 일반, 주의, 충치 개수 Counting
				for (int i = 0; i < teethValue.length; i++) {

					if (teethValue[i] < cavityCaution.intValue()) {
						cavityNormalCnt++;
					} else if (teethValue[i] >= cavityCaution && teethValue[i] < cavityDanger) {
						cavityCautionCnt++;
					} else if (teethValue[i] >= cavityDanger) {
						cavityDangerCnt++;
					}
				}

				// teethMeasureVO에 일반, 주의, 충치 개수 입력
				teethMeasureVO.setCavityNormal(cavityNormalCnt);
				teethMeasureVO.setCavityCaution(cavityCautionCnt);
				teethMeasureVO.setCavityDanger(cavityDangerCnt);

				// 일단 필요없을 것으로 사료되어 전송하지 않음
				// userTeethValues.get(0).setCavityDanger(cavityDangerCnt);

				// 일반, 주의, 충치 개수 업데이트 (측정일 기준)
				teethService.updateUserCavityCntByMeasureDt(teethMeasureVO);

				// 치아 상태 정보 조회
				teethStatus = teethService.selectTeethStatus(userId);

				hm.put("userToothValues", userToothValues);
				hm.put("userTeethValues", userTeethValues);
				hm.put("teethStatus", teethStatus);

				hm.put("code", "000");
				hm.put("msg", "Success");
			} catch (Exception e) {

				hm.put("code", "500");
				hm.put("msg", "Server Error");
				e.printStackTrace();
			}

		} else {

			hm.put("code", "400");
			hm.put("msg", "The token is not valid.");
		}

		return hm;
	}

	/**
	 * 기능 : 회원의 진단 정보 업데이트 작성자 : 정주현 작성일 : 2022. 11. 25 수정일 : 2022. 11. 29
	 */
	@PostMapping(value = { "/premium/user/updateDiagCd.do" })
	@ResponseBody
	public HashMap<String, Object> updateDiagCd(@RequestBody HashMap<String, Object> paramMap,
			HttpServletRequest request) throws Exception {

		// 회원 아이디
		String userId = (String) paramMap.get("userId");
		// 측정일
		String measureDt = (String) paramMap.get("measureDt");
		// 진단 코드
		String diagCd = (String) paramMap.get("diagCd");
		
		String diagCdList = "";

		String userDiagCd = null;
		String[] diagArray = null;
		String measureDiagCd = null;

		// 유치 측정 값 (20개) 저장하는 배열
		int[] teethValue = new int[20];
		// 영구치 측정 값 (4개) 저장하는 배열
		int[] permTeethValue = new int[4];

		// 유치 일반 개수
		int cavityNormalCnt = 0;
		// 유치 주의 개수
		int cavityCautionCnt = 0;
		// 유치 충치 개수
		int cavityDangerCnt = 0;

		// 영구치 일반 개수
		int permCavityNomalCnt = 0;
		// 영구치 주의 개수
		int permCavityCautionCnt = 0;
		// 영구치 충치 개수
		int permCavityDangerCnt = 0;

		// 충치 단계 - 주의
		Integer cavityCaution = 0;
		// 충치 단계 - 위험
		Integer cavityDanger = 0;

		HashMap<String, Integer> cavityLevel = new HashMap<String, Integer>();
		HashMap<String, Object> hm = new HashMap<String, Object>();
		TeethMeasureVO teethMeasureVO = new TeethMeasureVO();
		TeethMeasureVO tmpTeethMeasureVO = new TeethMeasureVO();

		if ("".equals(measureDt) || measureDt == null) {
			// 측정일 파라미터의 값이 존재 하지 않을 경우 SYSDATE로 설정
			LocalDate now = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			measureDt = now.format(formatter);
		}

		try {

			// 회원 진단 정보 조회
			teethMeasureVO = teethService.selectDiagCd(userId, measureDt);
			
			if(teethMeasureVO == null) {
				List<DiagnosisVO> diagList = diagnosisService.selectDiagDept2List();
				for (int i = 0; i < diagList.size(); i++) {
					if (i == diagList.size() - 1) {
						diagCdList += diagList.get(i).getDiagCd() + ":" + diagList.get(i).getDiagNo() + ":0";
					} else {
						diagCdList += diagList.get(i).getDiagCd() + ":" + diagList.get(i).getDiagNo() + ":0|";
					}
				}
				tmpTeethMeasureVO.setUserId(userId);
				tmpTeethMeasureVO.setMeasureDt(measureDt);
				tmpTeethMeasureVO.setDiagCd(diagCdList);
				teethService.insertUserTeethMeasureValue(tmpTeethMeasureVO);
				
				teethMeasureVO = teethService.selectDiagCd(userId, measureDt);
			}
			
			diagArray = diagCd.split(":");
			// 진단 목록 선택 시 전달되는 PARAMETER
			measureDiagCd = diagArray[0] + ":" + diagArray[1] + ":";
			// 회원 진단 코드
			userDiagCd = teethMeasureVO.getDiagCd();

			StringBuffer sb = new StringBuffer();
			sb.append(userDiagCd);
			// 회원의 전체 진단 코드에 선택된 진단 코드의 값을 1로 변경
			userDiagCd = sb.replace(userDiagCd.indexOf(measureDiagCd), userDiagCd.indexOf(measureDiagCd) + 7, diagCd).toString();
			// 회원의 전체 진단 코드 업데이트
			teethService.updateDiagCd(userId, userDiagCd, measureDt);

			// 충치 단계 - 데이터베이스 조회
			cavityLevel = teethService.selectCavityLevel();
			// 충치 단계 - 주의
			cavityCaution = Integer.parseInt(String.valueOf(cavityLevel.get("CAVITY_CAUTION")));
			// 충치 단계 - 충치
			cavityDanger = Integer.parseInt(String.valueOf(cavityLevel.get("CAVITY_DANGER")));

			// 유치 개수 Counting (일반, 주의, 충치)
			for (int i = 0; i < teethValue.length; i++) {
				if (teethValue[i] < cavityCaution.intValue()) {
					cavityNormalCnt++;
				} else if (teethValue[i] >= cavityCaution.intValue() && teethValue[i] < cavityDanger.intValue()) {
					cavityCautionCnt++;
				} else if (teethValue[i] >= cavityDanger.intValue()) {
					cavityDangerCnt++;
				}
			}

			teethMeasureVO.setCavityNormal(cavityNormalCnt);
			teethMeasureVO.setCavityCaution(cavityCautionCnt);
			teethMeasureVO.setCavityDanger(cavityDangerCnt);

			// 영구치 맹출 및 혼합치열기가 선택되었을 경우 영구치의 개수 Counting
			if (userDiagCd.contains("A:06:1") || userDiagCd.contains("A:10:1")) {

				for (int i = 0; i < permTeethValue.length; i++) {
					if (permTeethValue[i] < cavityCaution.intValue()) {
						permCavityNomalCnt++;
					} else if (permTeethValue[i] >= cavityCaution.intValue()
							&& permTeethValue[i] < cavityDanger.intValue()) {
						permCavityCautionCnt++;
					} else if (permTeethValue[i] >= cavityDanger.intValue()) {
						permCavityDangerCnt++;
					}
				}

				teethMeasureVO.setPermCavityNormal(permCavityNomalCnt);
				teethMeasureVO.setPermCavityCaution(permCavityCautionCnt);
				teethMeasureVO.setPermCavityDanger(permCavityDangerCnt);
			}

			// 유치 일반, 주의, 충치 및 영구치 일반, 주의, 충치 개수 업데이트 (측정일 기준)
			teethService.updateUserCavityCntByMeasureDt(teethMeasureVO);

		} catch (Exception e) {
			hm.put("code", "500");
			hm.put("msg", "Server error");
			e.printStackTrace();
		}

		hm.put("code", "000");
		hm.put("msg", "Success");
		hm.put("diagInfo", teethMeasureVO);
		return hm;

	}

	/**
	 * 기능 : 회원의 메모(비고) 내용 업데이트 작성자 : 정주현 작성일 : 2022. 11. 25 수정일 : 2022. 11. 25
	 */
	@PostMapping(value = { "/premium/user/updateMemo.do" })
	@ResponseBody
	public HashMap<String, Object> updateMemo(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request)
			throws Exception {

		String userId = (String) paramMap.get("userId");
		String memo = (String) paramMap.get("memo");

		// 오늘 날짜 구하기 (SYSDATE)
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String measureDt = now.format(formatter);

		HashMap<String, Object> hm = new HashMap<String, Object>();
		TeethMeasureVO teethMeasureVO = new TeethMeasureVO();

		try {

			if (!"ÿ".equals(memo)) {
				// 파라미터로 받은 MEMO 정보를 업데이트
				teethService.updateMemo(userId, memo, measureDt);
			}

			teethMeasureVO = teethService.selectMemo(userId, measureDt);

		} catch (Exception e) {
			hm.put("code", "500");
			hm.put("msg", "Server error");
			e.printStackTrace();

		}
		hm.put("code", "000");
		hm.put("msg", "Success");
		hm.put("memoInfo", teethMeasureVO);
		return hm;

	}

	/**
	 * 기능 : 진단 정보 조회 작성자 : 정주현 작성일 : 2022. 11. 25 수정일 : 2022. 11. 25
	 */
	@PostMapping(value = { "/premium/user/selectDiagnosisInfo.do" })
	@ResponseBody
	public HashMap<String, Object> selectDiagnosisInfo(@RequestBody HashMap<String, Object> paramMap,
			HttpServletRequest request) throws Exception {

		List<DiagnosisVO> diagDept1List = new ArrayList<DiagnosisVO>();
		List<DiagnosisVO> diagDept2List = new ArrayList<DiagnosisVO>();

		HashMap<String, Object> hm = new HashMap<String, Object>();

		try {
			// ST_DIAG_DEPT1 리스트 조회 (최상위 진단 정보 : ex 양치불량)
			diagDept1List = diagnosisService.selectDiagDept1List();
			// ST_DIAG_DEPT2 리스트 조회 (중위 진단 정보 : ex 치태)
			diagDept2List = diagnosisService.selectDiagDept2List();
		} catch (Exception e) {
			hm.put("code", "500");
			hm.put("msg", "Server error");
			e.printStackTrace();
		}

		hm.put("code", "000");
		hm.put("msg", "Success");

		hm.put("DiagDept1List", diagDept1List);
		hm.put("DiagDept2List", diagDept2List);

		return hm;

	}

	/**
	 * 기능 : 회원의 측정 상태(IS_MEASURING) 목록 조회 작성자 : 정주현 작성일 : 2022. 06. 30 수정일 : 2022.
	 * 08. 04
	 */
	@PostMapping(value = { "/premium/user/selectUserIsMeasuringValue.do" })
	@ResponseBody
	public HashMap<String, Object> selectUserIsMeasuringList(@RequestBody HashMap<String, Object> paramMap,
			HttpServletRequest request) throws Exception {

		String userId = (String) paramMap.get("userId");
		String isMeasuring = (String) paramMap.get("isMeasuring");

		HashMap<String, Object> hm = new HashMap<String, Object>();
		List<UserVO> userList = null;
		UserVO userVO = new UserVO();

		if (userId != null && !userId.equals("") && isMeasuring != null && !isMeasuring.equals("")) {
			userVO.setUserId(userId);
			userList = teethService.selectStUserIsMeasuring(userVO);

			// 동일한 값이 들어오면 db업데이트 X
			if (!isMeasuring.equals(userVO.getIsMeasuring())) {
				userVO.setIsMeasuring(isMeasuring);
				teethService.updateStUserIsMeasuring(userVO);
			}
		} else {
			try {
				userList = teethService.selectStUserIsMeasuring(userVO);
			} catch (Exception e) {
				hm.put("code", "500");
				hm.put("msg", "Server error");
				e.printStackTrace();

			}
		}
		hm.put("code", "000");
		hm.put("msg", "Success");
		hm.put("isMeasuring", isMeasuring);
		hm.put("userList", userList);

		return hm;

	}

	@PostMapping({ "/premium/user/updateTeethStatus.do" })
	@ResponseBody
	public HashMap<String, Object> updateTeethStatus(@RequestBody HashMap<String, Object> paramMap,
			HttpServletRequest request) throws Exception {

		String userId = (String) paramMap.get("userId");
		String teethStatus = (String) paramMap.get("teethStatus");
		int isExist = 0;
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String measureDt = now.format(formatter);

		HashMap<String, Object> hm = new HashMap<String, Object>();
		TeethMeasureVO teethMeasureVO = new TeethMeasureVO();

		try {
			isExist = teethService.selectCountTeethInfo(userId, measureDt);
			if (isExist == 1) {
				teethService.updateTeethStatus(userId, teethStatus, measureDt);
			} else {
				teethService.insertTeethStatus(userId, teethStatus);
			}

		} catch (Exception e) {
			hm.put("code", "500");
			hm.put("msg", "Server error");
			e.printStackTrace();
		}

		hm.put("code", "000");
		hm.put("msg", "Success");
		hm.put("memoInfo", teethMeasureVO);
		return hm;
	}

	
	
	
	
	/**
	 * 프리미엄(치과) 기능 : 회원 치아 개별 측정 값 조회 (기간) 작성자 : 정주현 작성일 : 2022. 5. 26 기간 조회
	 * (startDt, endDt)
	 */
	@PostMapping({ "/kindergarten/user/selectUserToothMeasureValue.do" })
	@ResponseBody
	public HashMap<String, Object> SelectUserMeasureToothValueForKindergarten(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception {

		this.logger.debug("========== TeethController ========== kindergarten.selectUserToothMeasureValue.do ==========");
		this.logger.debug("========== TeethController ========== kindergarten.selectUserToothMeasureValue.do ==========");
		this.logger.debug("========== TeethController ========== kindergarten.selectUserToothMeasureValue.do ==========");
		this.logger.debug("========== TeethController ========== kindergarten.selectUserToothMeasureValue.do ==========");
		this.logger.debug("========== TeethController ========== kindergarten.selectUserToothMeasureValue.do ==========");

		// SYSDATE 기준 측정 여부 확인
		int isExistSysdateRow = 0;
		// 기존 측정 값 여부 확인
		int isExistOldRow = 0;
		/** 피측정자 관련 정보 **/
		// 인증 토큰
		//String userAuthToken = request.getHeader("Authorization");
		// 회원 아이디
		String userId = (String) paramMap.get("userId");
		// 측정 치아 번호0
		String toothNo = (String) paramMap.get("toothNo");
		// 치아 측정 값
		String toothValue = (String) paramMap.get("toothValue");
		// 측정일 : SYSDATE(yyyy-mm-dd)
		String startDt = (String) paramMap.get("startDt");
		String endDt = (String) paramMap.get("endDt");
		// 기관 코드
		// String schoolCode = (String) paramMap.get("schoolCode");
		// 법정대리인 동의 여부
		// String agreYn = null;

		/** 치아 관련 정보 **/
		// 회원 치아 정보
		String teethStatus = null;
		// 유치 개수 20개
		int[] babyTeethValueArray = null;
		// 영구치 개수 8개
		int[] permTeethValueArray = null;
		// 영구치 어금니 개수 4개
		int[] permanentMolarsValueArray = null;
		// 임시 유치 배열 20개
		int[] tmpBabyTeethValueArray = null;
		

		// 유치 정상 치아 개수
		int babyCvNormalCnt = 0;
		// 유치 주의 치아 개수
		int babyCvCautionCnt = 0;
		// 유치 충치 치아 개수
		int babyCvDangerCnt = 0;

		// 정상 영구치 개수
		int pmCvNomalCnt = 0;
		// 주의 영구치 개수
		int pmCvCautionCnt = 0;
		// 충치 영구치 개수
		int pmCvDangerCnt = 0;

		// 충치 단계 - 주의
		Integer cautionLevel = 0;
		// 충치 단계 - 위험
		Integer dangerLevel = 0;

		UserVO userVO = new UserVO();
		ToothMeasureVO toothMeasureVO = new ToothMeasureVO();
		TeethMeasureVO teethMeasureVO = new TeethMeasureVO();

		HashMap<String, Object> hm = new HashMap<String, Object>();
		HashMap<String, Integer> cavityLevel = new HashMap<String, Integer>();

//				ToothMeasureVO userToothValues = new ToothMeasureVO();
//				TeethMeasureVO userTeethValues = new TeethMeasureVO();
		List<ToothMeasureVO> userToothValues = new ArrayList<ToothMeasureVO>();
		List<TeethMeasureVO> userTeethValues = new ArrayList<TeethMeasureVO>();

		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String sysDate = now.format(formatter);

		if (userId == null || userId.equals("") || userId.equals(" ")) {
			hm.put("code", "401");
			hm.put("msg", "There is no userId parameter.");
			return hm;
		}

		// JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
		// tokenValidation = jwtTokenUtil.validateToken(userAuthToken);

		if (tmpTokenValidation) {

			try {

				// 피측정자의 법정대리인 동의 여부 확인
				userVO = userService.selectUserDetail(userId);
				//agreYn = userVO.getAgreYn();

				toothMeasureVO.setUserId(userId);
				toothMeasureVO.setStartDt(startDt);
				toothMeasureVO.setEndDt(endDt);
				toothMeasureVO.setToothNo(toothNo);
				toothMeasureVO.setMeasureDt(sysDate);

				teethMeasureVO.setUserId(userId);
				teethMeasureVO.setStartDt(startDt);
				teethMeasureVO.setEndDt(endDt);
				teethMeasureVO.setMeasureDt(sysDate);

				// 오늘의 데이터가 있는지 확인
				isExistSysdateRow = teethService.isExistSysDateRow(userId);
				// 기존에 데이터가 있는지 확인
				isExistOldRow = teethService.isExistOldRow(userId);

				// 치아가 선택 되었을 경우
				if (toothValue != null && !toothValue.equals("") && !toothValue.equals(" ")) {
					toothMeasureVO.setToothValue(Integer.parseInt(toothValue));
					if (isExistSysdateRow == 0) {
						// 치아 값을 선택했을 경우 기존의 데이터에 추가적인 데이터를 합산하여 새로 데이터 생성
						List<TeethMeasureVO> userOldTeethMeasureValue = teethService.selectPaUserTeethMeasureValue(teethMeasureVO);
						teethService.insertPaUserTeethMeasureValue(userOldTeethMeasureValue.get(0));
						teethService.updateUserToothMeasureValue(toothMeasureVO);
						// 기존의 데이터를 반환
						userTeethValues = teethService.selectPaUserTeethMeasureValue(teethMeasureVO);

					} else {

						teethService.updateUserToothMeasureValue(toothMeasureVO);
						userTeethValues = teethService.selectPaUserTeethMeasureValue(teethMeasureVO);

					}
				} else { // 치아가 선택되지 않았을 경우
					// 기존 데이터는 있으나 오늘 데이터는 없을 경우
					if (isExistOldRow > 0 && isExistSysdateRow == 0) {

						teethMeasureVO.setUserId(userId);
						teethMeasureVO.setStartDt(startDt);
						teethMeasureVO.setEndDt(endDt);
						// 기존의 데이터를 반환
						userTeethValues = teethService.selectPaUserTeethMeasureValue(teethMeasureVO);

					} else if (isExistSysdateRow > 0) {

						userTeethValues = teethService.selectPaUserTeethMeasureValue(teethMeasureVO);

					} else {
						List<DiagnosisVO> diagList = this.diagnosisService.selectDiagDept2List();
						String diagCd = "";
						for (int i = 0; i < diagList.size(); i++) {
							if (i == diagList.size() - 1) {
								diagCd = diagCd + diagList.get(i).getDiagCd() + ":" + diagList.get(i).getDiagNo()
										+ ":0";
							} else {
								diagCd = diagCd + diagList.get(i).getDiagCd() + ":" + diagList.get(i).getDiagNo()
										+ ":0|";
							}
						}
						
						// 초기 값 -99 설정
						setTeethInit(teethMeasureVO);
						teethMeasureVO.setDiagCd(diagCd);
						teethService.insertPaUserTeethMeasureValue(teethMeasureVO);
						userTeethValues = teethService.selectPaUserTeethMeasureValue(teethMeasureVO);
					}
				}

				// 현재 측정한 치아의 값과 측정된 치아의 지난 측정 결과 값 RETURN
				userToothValues = teethService.selectUserToothMeasureValue(toothMeasureVO);
				// 현재 측정한 전체 치아의 값과 측정된 전체 치아의 값 RETURN
				// userTeethValues = teethService.selectUserTeethMeasureValue(teethMeasureVO);

				cavityLevel = teethService.selectCavityLevel();
				// 수치 단계 : 데이터베이스 값 조회
				cautionLevel = Integer.parseInt(String.valueOf(cavityLevel.get("CAVITY_CAUTION")));
				dangerLevel = Integer.parseInt(String.valueOf(cavityLevel.get("CAVITY_DANGER")));


				// 유치 개수 20개
				babyTeethValueArray = new int[20];
				// 영구치 개수 12개
				permTeethValueArray = new int[8];
				// 영구치 어금니 4개
				permanentMolarsValueArray = new int[4];
				// 영구치 위치에 해당하는 임시 유치 배열
				tmpBabyTeethValueArray = new int[8];

				// 변경전 유치 배열 34~53까지 20개
				babyTeethValueArray[0] = userTeethValues.get(0).getT34();
				babyTeethValueArray[1] = userTeethValues.get(0).getT35();
				babyTeethValueArray[2] = userTeethValues.get(0).getT36();
				// 영구치 T07
				babyTeethValueArray[3] = userTeethValues.get(0).getT37();
				// 영구치 T08
				babyTeethValueArray[4] = userTeethValues.get(0).getT38();
				// 영구치 T09
				babyTeethValueArray[5] = userTeethValues.get(0).getT39();
				// 영구치 T10
				babyTeethValueArray[6] = userTeethValues.get(0).getT40();

				babyTeethValueArray[7] = userTeethValues.get(0).getT41();
				babyTeethValueArray[8] = userTeethValues.get(0).getT42();
				babyTeethValueArray[9] = userTeethValues.get(0).getT43();
				
				babyTeethValueArray[10] = userTeethValues.get(0).getT46();
				babyTeethValueArray[11] = userTeethValues.get(0).getT47();
				babyTeethValueArray[12] = userTeethValues.get(0).getT48();
				// 영구치 T23
				babyTeethValueArray[13] = userTeethValues.get(0).getT49();
				// 영구치 T24
				babyTeethValueArray[14] = userTeethValues.get(0).getT50();
				// 영구치 T25
				babyTeethValueArray[15] = userTeethValues.get(0).getT51();
				// 영구치 T26
				babyTeethValueArray[16] = userTeethValues.get(0).getT52();
				
				babyTeethValueArray[17] = userTeethValues.get(0).getT53();
				babyTeethValueArray[18] = userTeethValues.get(0).getT54();
				babyTeethValueArray[19] = userTeethValues.get(0).getT55();
				
				// 영구치 어금니 - 16, 26, 36, 46
				permanentMolarsValueArray[0] = userTeethValues.get(0).getT33();
				permanentMolarsValueArray[1] = userTeethValues.get(0).getT44();
				permanentMolarsValueArray[2] = userTeethValues.get(0).getT45();
				permanentMolarsValueArray[3] = userTeethValues.get(0).getT56();

				// 영구치 상악
				permTeethValueArray[0] = userTeethValues.get(0).getT07();
				permTeethValueArray[1] = userTeethValues.get(0).getT08();
				permTeethValueArray[2] = userTeethValues.get(0).getT09();
				permTeethValueArray[3] = userTeethValues.get(0).getT10();

				// 영구치 하악
				permTeethValueArray[4] = userTeethValues.get(0).getT23();
				permTeethValueArray[5] = userTeethValues.get(0).getT24();
				permTeethValueArray[6] = userTeethValues.get(0).getT25();
				permTeethValueArray[7] = userTeethValues.get(0).getT26();
				
				
				// 갯수 카운팅을 위한 임시 배열
				tmpBabyTeethValueArray[0] = userTeethValues.get(0).getT37();
				tmpBabyTeethValueArray[1] = userTeethValues.get(0).getT38();
				tmpBabyTeethValueArray[2] = userTeethValues.get(0).getT39();
				tmpBabyTeethValueArray[3] = userTeethValues.get(0).getT40();
				tmpBabyTeethValueArray[4] = userTeethValues.get(0).getT49();
				tmpBabyTeethValueArray[5] = userTeethValues.get(0).getT50();
				tmpBabyTeethValueArray[6] = userTeethValues.get(0).getT51();
				tmpBabyTeethValueArray[7] = userTeethValues.get(0).getT52();
				
				
				// 충치 단계 조회 (주의, 충치)
				cautionLevel = Integer.parseInt(String.valueOf(cavityLevel.get("CAVITY_CAUTION")));
				dangerLevel = Integer.parseInt(String.valueOf(cavityLevel.get("CAVITY_DANGER")));

				/** 유치 정상, 주의, 충치 개수 저장 **/
				for (int i = 0; i < babyTeethValueArray.length; i++) { // 측정자가 입력한 주의나 충치 값의 -1000
					if (babyTeethValueArray[i] > 1000) {
						babyTeethValueArray[i] = (int) babyTeethValueArray[i] - 1000;
					}
//						if (babyTeethValueArray[i] < cautionLevel && babyTeethValueArray[i] >= 0) { // 정상 치아는 -99이상 체크
					if (babyTeethValueArray[i] < cautionLevel) { // 정상 치아는 -99이상 체크
						babyCvNormalCnt++;
					} else if (babyTeethValueArray[i] >= cautionLevel && babyTeethValueArray[i] < dangerLevel) {
						babyCvCautionCnt++;
					} else if (babyTeethValueArray[i] >= dangerLevel) {
						babyCvDangerCnt++;
					}
				}

				/** 영구치 상악 하악 정상, 주의, 충치 개수 저장 **/
				for (int i = 0; i < permTeethValueArray.length; i++) { // 측정자가 입력한 주의나 충치 값의 -1000
					if (permTeethValueArray[i] > 1000) {
						permTeethValueArray[i] = (int) permTeethValueArray[i] - 1000;
					}
					if (permTeethValueArray[i] >= cautionLevel && permTeethValueArray[i] < dangerLevel) {
						pmCvCautionCnt++;
					} else if (permTeethValueArray[i] >= dangerLevel) {
						pmCvDangerCnt++;
					}
				}

				for (int i = 0; i < tmpBabyTeethValueArray.length; i++) {
					if (tmpBabyTeethValueArray[i] > 1000) {
						tmpBabyTeethValueArray[i] = (int) tmpBabyTeethValueArray[i] - 1000;
					}
				}

				/** 영구치와 유치 두 개 다 값이 있을 경우 **/
				for (int i = 0; i < 8; i++) {
					if (permTeethValueArray[i] > 0 && tmpBabyTeethValueArray[i] > 0) {

						if (tmpBabyTeethValueArray[i] >= cautionLevel && tmpBabyTeethValueArray[i] < dangerLevel) {
							// 유치의 값이 주의 단계와 같거나 크고 주의 단계보다 작을 때 :: 범위는 주의 단계 이므로 유치의 주의 단계의 개수를 차감
							babyCvCautionCnt--;
						} else if (tmpBabyTeethValueArray[i] > dangerLevel) {
							// 유치의 값이 위험 단계보다 클때 :: 범위는 위험 단계 이므로 유치의 위험 단계의 개수를 차감
							babyCvDangerCnt--;
						}
					}
					// 영구치 측정 값이 있고 유치 측정값이 0이거나 -99일때 정상치아 갯수를 -1해준다
					if (permTeethValueArray[i] > 0 && tmpBabyTeethValueArray[i] <= 0) {
						babyCvNormalCnt--;
					}
				}

				/** 영구치 어금니 정상, 주의, 충치 개수 저장 **/
				for (int i = 0; i < permanentMolarsValueArray.length; i++) { // 측정자가 입력한 주의나 충치 값의 -1000
					if (permanentMolarsValueArray[i] > 1000) {
						permanentMolarsValueArray[i] = (int) permanentMolarsValueArray[i] - 1000;
					}
					if (permanentMolarsValueArray[i] >= cautionLevel && permanentMolarsValueArray[i] < dangerLevel) {
						pmCvCautionCnt++;
					} else if (permanentMolarsValueArray[i] >= dangerLevel) {
						pmCvDangerCnt++;
					}
				}
					

				// 유치 정상, 주의, 충치 개수 입력
				teethMeasureVO.setCavityNormal(babyCvNormalCnt);
				teethMeasureVO.setCavityCaution(babyCvCautionCnt);
				teethMeasureVO.setCavityDanger(babyCvDangerCnt);
				
				// 영구치 정상, 주의, 충치 개수 입력
				teethMeasureVO.setPermCavityNormal(pmCvNomalCnt);
				teethMeasureVO.setPermCavityCaution(pmCvCautionCnt);
				teethMeasureVO.setPermCavityDanger(pmCvDangerCnt);

				teethService.updateUserCavityCntByMeasureDt(teethMeasureVO);

				// 치아 상태 정보 조회
				teethStatus = teethService.selectTeethStatus(userId);

				hm.put("userToothValues", userToothValues);
				hm.put("userTeethValues", userTeethValues);
				hm.put("teethStatus", teethStatus);

				hm.put("code", "000");
				hm.put("msg", "Success");
			} catch (Exception e) {

				hm.put("code", "500");
				hm.put("msg", "Server Error");
				e.printStackTrace();
			}

		} else {

			hm.put("code", "400");
			hm.put("msg", "The token is not valid.");
		}

		return hm;
	}

	
}


