package co.smartooth.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import co.smartooth.app.service.OrganService;
//import co.smartooth.app.service.RegistService;
import co.smartooth.app.service.TeethService;
import co.smartooth.app.utils.AES256Util;
import co.smartooth.app.vo.TeethMeasureVO;


/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 04. 28
 * 수정일 : 2023. 08. 01
 * 서버 분리
 * @RestController를 쓰지 않는 이유는 몇 안되는 mapping에 jsp를 반환해줘야하는게 있으므로 @Controller를 사용한다.
 * @RestAPI로 반환해야할 경우 @ResponseBody를 사용하여 HashMap으로 return 해준다.
 */
@Controller
public class UtilsController {

	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired(required = false)
	private TeethService teethService;

	
	@Autowired(required = false)
	private OrganService organService;
	
	
	/**
	 * 기능   : 비밀번호 암호화 및 복호화
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 08. 24
	 * 			 APP에서 JSON형식으로 전달 받은 회원의 ID와 비밀번호를 확인 후 JSON으로 반환
	 */
	@PostMapping(value = {"/premium/utils/passwordCrypto.do"})
	@ResponseBody
	public HashMap<String,Object> passwordCrypto(@RequestBody HashMap<String, Object> paramMap) {
       
		logger.debug("========== UtilController ========== passwordCrypto.do ==========");
	    
	    HashMap<String, Object> hm = new HashMap<String, Object>();
	    
		String originalPwd = null;
		String decodePwd = null;
		String encodePwd = null;
		
		// 비밀번호 암호화 
		AES256Util aes256Util = new AES256Util();
		originalPwd = (String)paramMap.get("userPwd");
		decodePwd = aes256Util.aesDecode(originalPwd);
		encodePwd = aes256Util.aesEncode(originalPwd);
		
		System.out.println("USER_PWD Original >>>>>>>>>>>>>>>> "+ originalPwd);
		System.out.println("USER_PWD Encoding >>>>>>>>>>>>>>>> "+ encodePwd);
		System.out.println("USER_PWD Decoding >>>>>>>>>>>>>>>> "+ decodePwd);
		
		hm.put("originalPwd", originalPwd);
		hm.put("encodePwd", encodePwd);
		hm.put("decodePwd", decodePwd);
		
		return hm;
	}
	
	
	 /**
     * 기능   : 학생 회원 충치 개수 업데이트 (수동)
     * 작성자 : 정주현 
     * 작성일 : 2022. 08. 26
     * 수정일 : 
     */
	@PostMapping(value = {"/premium/utils/updateCavityCntKids.do"})
	@ResponseBody
	public void updateCavityCntKids(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception {
		
		logger.debug("========== UtilController ========== updateCavityCnt.do ==========");
		
		String userId = null;
		String startDt = null;
		String endDt = null;

		// 20개 치아 측정 값 
		int[] teethValue = new int[20];
		
		Integer cavityCaution = 0;
		Integer cavityDanger = 0;
			    
		// 정상 수치 0~12 이하 갯수
		int cavityNormalCnt = 0;
		// 주의 수치 13~24 이하 갯수
		int cavityCautionCnt = 0;
	    // 위험 수치 25이상 갯수
	    int cavityDangerCnt = 0;
		
		
		List<TeethMeasureVO> userTeethValues = new ArrayList<TeethMeasureVO>();
		
		TeethMeasureVO teethMeasureVO = new TeethMeasureVO();

		HashMap<String,Integer> cavityLevel = new HashMap<String,Integer>();
		
		// Parameter = userId 값 검증 (Null 체크 및 공백 체크)
		userId= (String)paramMap.get("userId");
		startDt= (String)paramMap.get("startDt");
		endDt= (String)paramMap.get("endDt");
		
		teethMeasureVO.setUserId(userId);
		teethMeasureVO.setStartDt(startDt);
		teethMeasureVO.setEndDt(endDt);
		
			
		try {
			
			// 모든 치아에 대한 조회 값 (오늘의 값이 없을 경우 최근 값으로 불러와야함)
			userTeethValues = teethService.selectUserTeethMeasureValue(teethMeasureVO);
			
			// CAVITY_LEVEL 분류 부분 - 충치 단계별 수치 조회
			cavityLevel = teethService.selectCavityLevel();
			
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
			
			
			cavityCaution = Integer.parseInt(String.valueOf(cavityLevel.get("CAVITY_CAUTION")));
			cavityDanger = Integer.parseInt(String.valueOf(cavityLevel.get("CAVITY_DANGER")));

			
			for (int i = 0; i < teethValue.length; i++) {
				
				System.out.println("teethValue[i] >> " + teethValue[i]);
				if (teethValue[i] < cavityCaution) { 														// 0~12 정상 치아
					cavityNormalCnt++;
				} else if (teethValue[i] >= cavityCaution && teethValue[i] < cavityDanger) {	// 13~24 주의
					cavityCautionCnt++;
				} else if (teethValue[i] >= cavityDanger) {	// 25~ 충치
					cavityDangerCnt++;
				}
			}
			
			teethMeasureVO.setCavityNormal(cavityNormalCnt);
			teethMeasureVO.setCavityCaution(cavityCautionCnt);
			teethMeasureVO.setCavityDanger(cavityDangerCnt);
			
			userTeethValues.get(0).setCavityDanger(cavityDangerCnt);
			
			// ST_STUDENT_USER_DETAIL 테이블에 CavityCnt 업데이트
			teethMeasureVO.setMeasureDt(startDt);
			teethService.updateUserCavityCntByMeasureDt(teethMeasureVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
     * 기능   : 학생 회원 충치 개수 업데이트 (수동)
     * 작성자 : 정주현 
     * 작성일 : 2022. 08. 26
     * 수정일 : 
     */
	@PostMapping(value = {"/premium/utils/updateCavityCntNormal.do"})
	@ResponseBody
	public void updateCavityCntNormal(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception {
		
		logger.debug("========== UtilController ========== updateCavityCnt.do ==========");
		
		String userId = null;
		String startDt = null;
		String endDt = null;

		// 32개 치아 측정 값 
		int[] teethValue = new int[32];
		
		Integer cavityCaution = 0;
		Integer cavityDanger = 0;
			    
		// 정상 수치 0~12 이하 갯수
		int cavityNormalCnt = 0;
		// 주의 수치 13~24 이하 갯수
		int cavityCautionCnt = 0;
	    // 위험 수치 25이상 갯수
	    int cavityDangerCnt = 0;
		
		
		List<TeethMeasureVO> userTeethValues = new ArrayList<TeethMeasureVO>();
		
		TeethMeasureVO teethMeasureVO = new TeethMeasureVO();

		HashMap<String,Integer> cavityLevel = new HashMap<String,Integer>();
		
		// Parameter = userId 값 검증 (Null 체크 및 공백 체크)
		userId= (String)paramMap.get("userId");
		startDt= (String)paramMap.get("startDt");
		endDt= (String)paramMap.get("endDt");
		
		teethMeasureVO.setUserId(userId);
		teethMeasureVO.setStartDt(startDt);
		teethMeasureVO.setEndDt(endDt);
		teethMeasureVO.setMeasureDt(endDt);
		
			
		try {
			
			// 모든 치아에 대한 조회 값 (오늘의 값이 없을 경우 최근 값으로 불러와야함)
			userTeethValues = teethService.selectUserTeethMeasureValue(teethMeasureVO);
			
			// CAVITY_LEVEL 분류 부분 - 충치 단계별 수치 조회
			cavityLevel = teethService.selectCavityLevel();
			
			teethValue[0] = userTeethValues.get(0).getT01();
			teethValue[1] = userTeethValues.get(0).getT02();
			teethValue[2] = userTeethValues.get(0).getT03();
			teethValue[3] = userTeethValues.get(0).getT04();
			teethValue[4] = userTeethValues.get(0).getT05();
			teethValue[5] = userTeethValues.get(0).getT06();
			teethValue[6] = userTeethValues.get(0).getT07();
			teethValue[7] = userTeethValues.get(0).getT08();
			teethValue[8] = userTeethValues.get(0).getT09();
			teethValue[9] = userTeethValues.get(0).getT10();
			teethValue[10] = userTeethValues.get(0).getT11();
			teethValue[11] = userTeethValues.get(0).getT12();
			teethValue[12] = userTeethValues.get(0).getT13();
			teethValue[13] = userTeethValues.get(0).getT14();
			teethValue[14] = userTeethValues.get(0).getT15();
			teethValue[15] = userTeethValues.get(0).getT16();
			teethValue[16] = userTeethValues.get(0).getT17();
			teethValue[17] = userTeethValues.get(0).getT18();
			teethValue[18] = userTeethValues.get(0).getT19();
			teethValue[19] = userTeethValues.get(0).getT20();
			teethValue[20] = userTeethValues.get(0).getT21();
			teethValue[21] = userTeethValues.get(0).getT22();
			teethValue[22] = userTeethValues.get(0).getT23();
			teethValue[23] = userTeethValues.get(0).getT24();
			teethValue[24] = userTeethValues.get(0).getT25();
			teethValue[25] = userTeethValues.get(0).getT26();
			teethValue[26] = userTeethValues.get(0).getT27();
			teethValue[27] = userTeethValues.get(0).getT28();
			teethValue[28] = userTeethValues.get(0).getT29();
			teethValue[29] = userTeethValues.get(0).getT30();
			teethValue[30] = userTeethValues.get(0).getT31();
			teethValue[31] = userTeethValues.get(0).getT32();
			
			
			cavityCaution = Integer.parseInt(String.valueOf(cavityLevel.get("CAVITY_CAUTION")));
			cavityDanger = Integer.parseInt(String.valueOf(cavityLevel.get("CAVITY_DANGER")));

			
			for (int i = 0; i < teethValue.length; i++) {
				
				System.out.println("teethValue[i] >> " + teethValue[i]);
				if (teethValue[i] < cavityCaution) { 														// 0~12 정상 치아
					cavityNormalCnt++;
				} else if (teethValue[i] >= cavityCaution && teethValue[i] < cavityDanger) {	// 13~24 주의
					cavityCautionCnt++;
				} else if (teethValue[i] > cavityDanger) {	// 25~ 충치
					cavityDangerCnt++;
				}
			}

			teethMeasureVO.setCavityNormal(cavityNormalCnt);
			teethMeasureVO.setCavityCaution(cavityCautionCnt);
			teethMeasureVO.setCavityDanger(cavityDangerCnt);
			
			userTeethValues.get(0).setCavityDanger(cavityDangerCnt);
			
			// ST_STUDENT_USER_DETAIL 테이블에 CavityCnt 업데이트
			teethService.updateUserCavityCntByMeasureDt(teethMeasureVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 기능   : 그래프 통계 일괄 계산
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 08. 29
	 * 수정일 : 2023. 08. 29
	 */
	@PostMapping(value = { "/premium/utils/graphBatch.do" })
	@ResponseBody
	public HashMap<String,Object> graphBatch(@RequestBody HashMap<String, Object> paramMap) {

		String schoolCode = (String) paramMap.get("schoolCode");
		String measureDt = (String) paramMap.get("measureDt");

		// 진단 내용에 따른 기준 점수 조회 (하드코딩 되어 있으므로 조회하도록 변경해야함)
		double cavityCautionScore = 1;
		double cavityDangerScore = 4;
		double permCavityCautionScore = 1.5;
		double permCavityDangerScore = 6;

		HashMap<String, Object> hm = new HashMap<String, Object>();
		TeethMeasureVO teethMeasureVO = new TeethMeasureVO();

		// 회원들의 측정 값 통계 목록
		List<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
		
		try {
			dataList = teethService.selectUserMeasureStatisticsList(schoolCode, measureDt);
		
		
			for (int i = 0; i < dataList.size(); i++) {
				// 측정 회원의 아이디
				String stUserId = (String) dataList.get(i).get("USER_ID");
				// 측정 회원 진단 태그 항목
				String diagCd = (String) dataList.get(i).get("DIAG_CD");
		
				// 유치 및 영구치 >> 주의(caution) 치아 및 충치(danger) 치아 개수
				int cavityCautionCnt = Integer.parseInt(dataList.get(i).get("CAVITY_CAUTION").toString());
				int cavityDangerCnt = Integer.parseInt(dataList.get(i).get("CAVITY_DANGER").toString());
				int permCavityCautionCnt = Integer.parseInt(dataList.get(i).get("PERM_CAVITY_CAUTION").toString());
				int permCavityDangerCnt = Integer.parseInt(dataList.get(i).get("PERM_CAVITY_DANGER").toString());
				// 악화지수 계산ㄴ
				double deteriorateScore = (cavityCautionCnt * cavityCautionScore) + (cavityDangerCnt * cavityDangerScore) + (permCavityCautionCnt * permCavityCautionScore) + (permCavityDangerCnt * permCavityDangerScore);
		
				// 충치 및 주의가 있을 경우 소견 점수는 포함되지 않는다.
				if (deteriorateScore == 0) {
					// 치태 및 치석이 존재 할 경우 (하드코딩)
					if (diagCd.contains("A:006:1")) {
						deteriorateScore = 0.3;
					}
				}

				teethMeasureVO.setUserId(stUserId);
				teethMeasureVO.setMeasureDt(measureDt);
				teethMeasureVO.setDeteriorateScore(deteriorateScore);
		
				// 악화 지수 업데이트
				teethService.updateUserDeteriorateScore(teethMeasureVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			hm.put("code", "500");
			hm.put("msg", "그래프 통계 관련 계산 중 문제가 발생하였습니다.\n관리자에게 문의해주시기 바랍니다.");
		}
		// 그래프 계산 일괄 작업 여부 업데이트 (Y)
		// organService.updateGraphBatchedStatus(schoolCode);
		hm.put("msg", "000");
		hm.put("msg", "그래프 통계 계산이 완료되었습니다.");
		
		return hm;
	}
	
	
	
}
