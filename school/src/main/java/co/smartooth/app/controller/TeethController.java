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
 * 수정일 : 2023. 08. 02
 * 서버분리 : 2023. 08. 01
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

	

	
	// 인증 패스
	// private static boolean tokenValidation = false;
	private static boolean tmpTokenValidation = true;

	
	// 모든 치아의 값을 -99로 초기화하는 메소드
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
	 * 기능 : 회원 치아 개별 측정 값 조회 및 입력, 수정
	 * 작성자 : 정주현
	 * 작성일 : 2022. 05. 26
	 * 수정일 : 2023. 08. 01 
	 * 	기간 조회 : startDt - 시작일
	 * 			     endDt - 종료일
	 * 서버분리 : 2023. 08. 01
	 */
//	@PostMapping({ "/kindergarten/user/selectUserToothMeasureValue.do" })
	@PostMapping({ "/kindergarten/user/selectUserToothMeasureValue.do" })
	@ResponseBody
	public HashMap<String, Object> SelectUserMeasureToothValueForKindergarten(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception {

		
		this.logger.debug("========== School.TeethController ========== kindergarten.selectUserToothMeasureValue.do ==========");
		this.logger.debug("========== School.TeethController ========== kindergarten.selectUserToothMeasureValue.do ==========");
		this.logger.debug("========== School.TeethController ========== kindergarten.selectUserToothMeasureValue.do ==========");
		this.logger.debug("========== School.TeethController ========== kindergarten.selectUserToothMeasureValue.do ==========");

		
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
		// 측정자 아이디
		String measurerId = (String) paramMap.get("measurerId");

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

		HashMap<String, Object> hm = new HashMap<String, Object>();
		HashMap<String, Integer> cavityLevel = new HashMap<String, Integer>();

		ToothMeasureVO toothMeasureVO = new ToothMeasureVO();
		TeethMeasureVO teethMeasureVO = new TeethMeasureVO();
		
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

				// 치아 한개
				toothMeasureVO.setUserId(userId);
				toothMeasureVO.setStartDt(startDt);
				toothMeasureVO.setEndDt(endDt);
				toothMeasureVO.setToothNo(toothNo);
				toothMeasureVO.setMeasureDt(sysDate);
				toothMeasureVO.setMeasurerId(measurerId);
				// 치아 전체
				teethMeasureVO.setUserId(userId);
				teethMeasureVO.setStartDt(startDt);
				teethMeasureVO.setEndDt(endDt);
				teethMeasureVO.setMeasureDt(sysDate);
				teethMeasureVO.setMeasurerId(measurerId);

				// 오늘의 데이터 존재 여부 확인
				isExistSysdateRow = teethService.isExistSysDateRow(userId);
				// 기존에 데이터 존재 여부 확인
				isExistOldRow = teethService.isExistOldRow(userId);

				// 치아 선택된 경우
				if (toothValue != null && !toothValue.equals("") && !toothValue.equals(" ")) {
					toothMeasureVO.setToothValue(Integer.parseInt(toothValue));
					if (isExistSysdateRow == 0) {
						// 치아 값을 선택했을 경우 기존의 데이터에 추가적인 데이터를 합산하여 새로 데이터 생성
						List<TeethMeasureVO> userOldTeethMeasureValue = teethService.selectUserTeethMeasureValue(teethMeasureVO);
						teethService.insertUserTeethMeasureValue(userOldTeethMeasureValue.get(0));
						teethService.updateUserToothMeasureValue(toothMeasureVO);
						// 기존의 데이터를 반환
						userTeethValues = teethService.selectUserTeethMeasureValue(teethMeasureVO);
					} else {
						teethService.updateUserToothMeasureValue(toothMeasureVO);
						userTeethValues = teethService.selectUserTeethMeasureValue(teethMeasureVO);
					}
				// 치아가 선택되지 않았을 경우
				} else { 
					// 기존 데이터는 있으나 오늘 데이터는 없을 경우
					if (isExistOldRow > 0 && isExistSysdateRow == 0) {
						teethMeasureVO.setUserId(userId);
						teethMeasureVO.setStartDt(startDt);
						teethMeasureVO.setEndDt(endDt);
						// 기존의 데이터를 반환
						userTeethValues = teethService.selectUserTeethMeasureValue(teethMeasureVO);
					} else if (isExistSysdateRow > 0) {
						userTeethValues = teethService.selectUserTeethMeasureValue(teethMeasureVO);

					} else {
						List<DiagnosisVO> diagList = this.diagnosisService.selectDiagDept2List();
						String diagCd = "";
						for (int i = 0; i < diagList.size(); i++) {
							if (i == diagList.size() - 1) {
								diagCd = diagCd + diagList.get(i).getDiagCd() + ":" + diagList.get(i).getDiagNo()+ ":0";
							} else {
								diagCd = diagCd + diagList.get(i).getDiagCd() + ":" + diagList.get(i).getDiagNo()+ ":0|";
							}
						}
						
						// 초기 값 -99 설정
						setTeethInit(teethMeasureVO);
						teethMeasureVO.setDiagCd(diagCd);
						teethService.insertUserTeethMeasureValue(teethMeasureVO);
						userTeethValues = teethService.selectUserTeethMeasureValue(teethMeasureVO);
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
				for (int i = 0; i < babyTeethValueArray.length; i++) {
					// 측정자가 입력한 주의나 충치 값의 -1000
					if (babyTeethValueArray[i] > 1000) {
						babyTeethValueArray[i] = (int) babyTeethValueArray[i] - 1000;
					}
					// if (babyTeethValueArray[i] < cautionLevel && babyTeethValueArray[i] >= 0) { // 정상 치아는 -99이상 체크
					if (babyTeethValueArray[i] < cautionLevel) {
						// 정상 치아는 -99이상 체크
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
					
				// 유치 정상, 주의, 충치 개수 등록
				teethMeasureVO.setCavityNormal(babyCvNormalCnt);
				teethMeasureVO.setCavityCaution(babyCvCautionCnt);
				teethMeasureVO.setCavityDanger(babyCvDangerCnt);
				
				// 영구치 정상, 주의, 충치 개수 등록
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
	
	
	
	/**
	 * 기능 : 회원의 진단 정보 업데이트
	 * 작성자 : 정주현
	 * 작성일 : 2022. 11. 25
	 * 수정일 : 2023. 08. 01
	 * 서버분리 : 2023. 08. 01
	 */
	@PostMapping(value = { "/premium/user/updateDiagCd.do" })
	@ResponseBody
	public HashMap<String, Object> updateDiagCd(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception {

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
			
			// 진단 정보가 없을 경우 새로 생성
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
				setTeethInit(tmpTeethMeasureVO);
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
			
		} catch (Exception e) {
			hm.put("code", "500");
			hm.put("msg", "진단 코드 생성 및 등록 등의 작업을 진행하지 못했습니다.\n관리자에게 문의해주시기 바랍니다.");
			e.printStackTrace();
		}

		hm.put("code", "000");
		hm.put("msg", "성공.");
		hm.put("diagInfo", teethMeasureVO);
		return hm;

	}
	
	
	
	/**
	 * 기능 : 진단 정보 조회
	 * 작성자 : 정주현
	 * 작성일 : 2022. 11. 25
	 * 수정일 : 2023. 08. 01
	 */
	@PostMapping(value = { "/premium/user/selectDiagnosisInfo.do" })
	@ResponseBody
	public HashMap<String, Object> selectDiagnosisInfo(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception {

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
			hm.put("msg", "진단 정보 조회에 실패했습니다.\n관리자에게 문의해주시기 바랍니다.");
			e.printStackTrace();
		}

		hm.put("code", "000");
		hm.put("msg", "진단 정보 조회 성공.");

		hm.put("DiagDept1List", diagDept1List);
		hm.put("DiagDept2List", diagDept2List);

		return hm;

	}

	
	
	/**
	 * 기능 : 회원의 메모(비고) 내용 업데이트
	 * 작성자 : 정주현
	 * 작성일 : 2022. 11. 25
	 * 수정일 : 2023. 08. 01
	 * 서버분리 : 2023. 08. 01
	 */
	@PostMapping(value = { "/premium/user/updateMemo.do" })
	@ResponseBody
	public HashMap<String, Object> updateMemo(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception {

		String userId = (String) paramMap.get("userId");
		String memo = (String) paramMap.get("memo");

		// 오늘 날짜 구하기 (SYSDATE)
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate minusYears = now.minusYears(1);

		String startDt = minusYears.format(formatter);
		String endDt = now.format(formatter);
		String measureDt = endDt;

		HashMap<String, Object> hm = new HashMap<String, Object>();
		TeethMeasureVO teethMeasureVO = new TeethMeasureVO();
		
		try {
			if (!"ÿ".equals(memo)) {
				
				int isExistSysDateRow = teethService.isExistSysDateRow(userId);
				if(isExistSysDateRow == 0) {
					hm.put("code", "200");
					hm.put("msg", "측정 날짜가 아닌 날짜의 데이터는 메모가 수정이 불가능합니다.\n관리자에게 문의해주시기 바랍니다.");
				}else {
					teethService.updateMemo(userId, memo, measureDt);
					hm.put("code", "000");
					hm.put("msg", "메모 등록 성공");
				}
			}
			teethMeasureVO = teethService.selectMemo(userId, measureDt);
			hm.put("memoInfo", teethMeasureVO);
			
		} catch (Exception e) {
			hm.put("code", "500");
			hm.put("msg", "메모 등록에 실패했습니다.\n관리자에게 문의해주시기 바랍니다.");
			e.printStackTrace();
		}
		
		return hm;

	}
	

	
	/**
	 * 기능 : 치아 상태 업데이트
	 * 작성자 : 정주현
	 * 작성일 : 2022. 06. 30
	 * 수정일 : 2023. 08. 01
	 * 서버분리 : 2023. 08. 01
	 */
	@PostMapping({ "/premium/user/updateTeethStatus.do" })
	@ResponseBody
	public HashMap<String, Object> updateTeethStatus(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception {

		String userId = (String) paramMap.get("userId");
		String teethStatus = (String) paramMap.get("teethStatus");

		int isExistTeethInfo = 0;
		
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String measureDt = now.format(formatter);

		HashMap<String, Object> hm = new HashMap<String, Object>();
		TeethMeasureVO teethMeasureVO = new TeethMeasureVO();

		try {
			// 피측정자 치아 상태 정보 존재 여부 확인 (오늘 날짜 기준)
			isExistTeethInfo = teethService.isExistTeethInfo(userId, measureDt);
			if (isExistTeethInfo == 1) {
				// 치아 상태 정보 존재 시 업데이트
				teethService.updateTeethStatus(userId, teethStatus, measureDt);
			} else {
				// 치아 상태 정보 존재하지 않을 시 치아 상태 정보 등록
				teethService.insertTeethStatus(userId, teethStatus);
			}
		} catch (Exception e) {
			hm.put("code", "500");
			hm.put("msg", "치아 상태 등록 및 수정에 실패했습니다.\n관리자에게 문의해주시기 바랍니다.");
			e.printStackTrace();
		}

		hm.put("code", "000");
		hm.put("msg", "등록 성공.");
		// 추후 변경
		hm.put("memoInfo", teethMeasureVO);
		
		return hm;
	}

	
	
	/**
	 * 기능 : 회원의 측정 상태(IS_MEASURING) 목록 조회
	 * 작성자 : 정주현
	 * 작성일 : 2022. 06. 30
	 * 수정일 : 2023. 08. 01
	 * 서버분리 : 2023. 08. 01
	 */
	@PostMapping(value = { "/premium/user/selectUserIsMeasuring.do" })
	@ResponseBody
	public HashMap<String, Object> selectUserIsMeasuringList(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception {

		String userId = (String) paramMap.get("userId");
		String isMeasuring = (String) paramMap.get("isMeasuring");

		HashMap<String, Object> hm = new HashMap<String, Object>();
		List<UserVO> userList = null;
		UserVO userVO = new UserVO();

		if (userId != null && !userId.equals("") && isMeasuring != null && !isMeasuring.equals("")) {
			userVO.setUserId(userId);
			userList = teethService.selectUserIsMeasuring(userVO);

			// 동일한 값이 들어오면 db업데이트 X
			if (!isMeasuring.equals(userVO.getIsMeasuring())) {
				userVO.setIsMeasuring(isMeasuring);
				teethService.updateStUserIsMeasuring(userVO);
			}
		} else {
			try {
				userList = teethService.selectUserIsMeasuring(userVO);
			} catch (Exception e) {
				hm.put("code", "500");
				hm.put("msg", "치아 측정 상황 목록 조회에 실패했습니다.\n관리자에게 문의해주시기 바랍니다.");
				e.printStackTrace();

			}
		}
		hm.put("code", "000");
		hm.put("msg", "Success");
		hm.put("isMeasuring", isMeasuring);
		hm.put("userList", userList);

		return hm;

	}
	
	
	
}


