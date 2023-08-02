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
	 * 기능   : 치아 상태 및 정보 조회
	 * 작성자 : 정주현 
	 * 작성일 : 2022. 08. 24
	 * 수정일 : 2023. 08. 01
	 */
	@PostMapping(value = {"/premium/utils/selectTeethStatusInfo.do"})
	@ResponseBody
	public HashMap<String,Object> selectTeethStatusInfo(@RequestBody HashMap<String, Object> paramMap) {
		
		logger.debug("========== UtilController ========== passwordCrypto.do ==========");
		
		HashMap<String, Object> hm = new HashMap<String, Object>();
		
		String teethStatus = null;
		String teethStatusStr = null;
		String loosingToothNoStr = "";
		String treatedToothNoStr = "";
		
		ArrayList<Integer> losingToothArray = new ArrayList<Integer>();
		ArrayList<Integer> treatedToothArray = new ArrayList<Integer>();
		
		int treatedCnt = 0;
		int cameOutCnt = 0;
		
		String[] teethStatusArray = new String[32]; 
		
		// 치아 상태 정보 파라미터
		teethStatus = (String)paramMap.get("teethStatus");
		
		teethStatusArray = teethStatus.split("\\|");
		
		for(int i=0 ; i<teethStatusArray.length; i++) {
		
			// 빠진 치아
			if(teethStatusArray[i].equals("200")) {
				cameOutCnt++;
				losingToothArray.add(i+1);
				
			}
			// 치료받은 치아
			if(teethStatusArray[i].equals("300")) {
				treatedCnt++;
				treatedToothArray.add(i+1);
			}

		}
		
		for(Integer losingTooth : losingToothArray) {
			
			switch (losingTooth) {
	            case 1:  loosingToothNoStr += "영구치1,";
	                     break;
	            case 2:  loosingToothNoStr += "영구치2,";
	                     break;
	            case 3:  loosingToothNoStr += "16,";
	                     break;
	            case 4:  loosingToothNoStr += "55,";
	                     break;
	            case 5:  loosingToothNoStr += "54,";
	                     break;
	            case 6:  loosingToothNoStr += "53,";
	                     break;
	            case 7:  loosingToothNoStr += "52,";
	                     break;
	            case 8:  loosingToothNoStr += "51,";
	                     break;
	            case 9:  loosingToothNoStr += "61,";
	                     break;
	            case 10: loosingToothNoStr += "62,";
	                     break;
	            case 11: loosingToothNoStr += "63,";
	                     break;
	            case 12: loosingToothNoStr += "64,";
	                     break;
	            case 13: loosingToothNoStr += "65,";
	            		break;
	            case 14: loosingToothNoStr += "26,";
	            		break;
	            case 15: loosingToothNoStr += "영구치15,";
	            		break;
	            case 16: loosingToothNoStr += "영구치16,";
	            		break;
	            case 17: loosingToothNoStr += "영구치17,";
	            		break;
	            case 18: loosingToothNoStr += "영구치18,";
	            		break;
	            case 19: loosingToothNoStr += "36,";
	            		break;
	            case 20: loosingToothNoStr += "75,";
	            		break;
	            case 21: loosingToothNoStr += "74,";
	            		break;
	            case 22: loosingToothNoStr += "73,";
	            		break;
	            case 23: loosingToothNoStr += "72,";
	            		break;
	            case 24: loosingToothNoStr += "71,";
	            		break;
	            case 25: loosingToothNoStr += "81,";
	            		break;
	            case 26: loosingToothNoStr += "82,";
	            		break;
	            case 27: loosingToothNoStr += "83,";
	            		break;
	            case 28: loosingToothNoStr += "84,";
	            		break;
	            case 29: loosingToothNoStr += "85,";
	            		break;
	            case 30: loosingToothNoStr += "46,";
	            		break;
	            case 31: loosingToothNoStr += "영구치31,";
	            		break;
	            case 32: loosingToothNoStr += "영구치32,";
	            		break;
			}
		
		
		
		for(Integer treatedTooth : treatedToothArray) {
			
			switch (treatedTooth) {
	            case 1:  treatedToothNoStr += "영구치1,";
	                     break;
	            case 2:  treatedToothNoStr += "영구치2,";
	                     break;
	            case 3:  treatedToothNoStr += "16,";
	                     break;
	            case 4:  treatedToothNoStr += "55,";
	                     break;
	            case 5:  treatedToothNoStr += "54,";
	                     break;
	            case 6:  treatedToothNoStr += "53,";
	                     break;
	            case 7:  treatedToothNoStr += "52,";
	                     break;
	            case 8:  treatedToothNoStr += "51,";
	                     break;
	            case 9:  treatedToothNoStr += "61,";
	                     break;
	            case 10: treatedToothNoStr += "62,";
	                     break;
	            case 11: treatedToothNoStr += "63,";
	                     break;
	            case 12: treatedToothNoStr += "64,";
	                     break;
	            case 13: treatedToothNoStr += "65,";
	            		break;
	            case 14: treatedToothNoStr += "26,";
	            		break;
	            case 15: treatedToothNoStr += "영구치15,";
	            		break;
	            case 16: treatedToothNoStr += "영구치16,";
	            		break;
	            case 17: treatedToothNoStr += "영구치17,";
	            		break;
	            case 18: treatedToothNoStr += "영구치18,";
	            		break;
	            case 19: treatedToothNoStr += "36,";
	            		break;
	            case 20: treatedToothNoStr += "75,";
	            		break;
	            case 21: treatedToothNoStr += "74,";
	            		break;
	            case 22: treatedToothNoStr += "73,";
	            		break;
	            case 23: treatedToothNoStr += "72,";
	            		break;
	            case 24: treatedToothNoStr += "71,";
	            		break;
	            case 25: treatedToothNoStr += "81,";
	            		break;
	            case 26: treatedToothNoStr += "82,";
	            		break;
	            case 27: treatedToothNoStr += "83,";
	            		break;
	            case 28: treatedToothNoStr += "84,";
	            		break;
	            case 29: treatedToothNoStr += "85,";
	            		break;
	            case 30: treatedToothNoStr += "46,";
	            		break;
	            case 31: treatedToothNoStr += "영구치31,";
	            		break;
	            case 32: treatedToothNoStr += "영구치32,";
	            		break;
				}
			
			}
		}
		
		loosingToothNoStr = loosingToothNoStr.substring(0, loosingToothNoStr.length()-1);
		treatedToothNoStr = treatedToothNoStr.substring(0, treatedToothNoStr.length()-1);
		
		hm.put("loosingToothNoStr", loosingToothNoStr);
		hm.put("treatedToothNoStr", treatedToothNoStr);
		return hm;
	}
	
}
