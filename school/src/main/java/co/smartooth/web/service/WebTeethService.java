package co.smartooth.web.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.smartooth.web.vo.WebTeethInfoVO;
import co.smartooth.web.vo.WebTeethMeasureVO;
import co.smartooth.web.vo.WebToothMeasureVO;
import co.smartooth.web.vo.WebUserVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 04. 28
 * 수정일 : 2023. 03. 28
 */
public interface WebTeethService {
	
	
	// 피측정자 치아 상태 값 INSERT
	public void insertUserTeethInfo(WebTeethInfoVO webTeethInfoVO) throws Exception;

	
	
	// 피측정자 충치 개수 UPDATE (측정일별)
	public void updateUserCavityCntByMeasureDt(WebTeethMeasureVO webTeethMeasureVO) throws Exception;
	
	
	
	// 피측정자 치아 측정 값 조회 (기간)
	public List<WebTeethMeasureVO> selectUserTeethMeasureValue(WebTeethMeasureVO webTeethMeasureVO) throws Exception;
	
	
	
    // 피측정자 치아 측정 값 측정일 기준으로 조회
    public WebTeethMeasureVO selectUserMeasureValue(@Param("userId") String userId, @Param("measureDt") String measureDt) throws Exception;
    
    
    
    // 충치 단계별 수치 조회
	public HashMap<String, Integer> selectCavityLevel() throws Exception;
	
	
	
	// 진단 내용 (diag-decript) 업데이트 
	public void updateDiagDescript(@Param("userId") String userId, @Param("measureDt") String measureDt, @Param("diagDescript") String diagDescript) throws Exception;

	
	
	// 진단 제목 조회
	public String selectDiagTitle(String diagCd) throws Exception;
	
	
	
	// 측정일 목록 조회
	public List<String> selectUserMeasureDtList(WebTeethMeasureVO webTeethMeasureVO) throws Exception;
	
	
	
	// 회원들의 측정 값 통계 목록
	public List<HashMap<String, Object>> selectUserMeasureStatisticsList(@Param("schoolCode") String schoolCode, @Param("measureDt") String measureDt) throws Exception;

	
	
	// 악화 지수 점수 업데이트 
	public void updateUserDeteriorateScore(WebTeethMeasureVO webTeethMeasureVO) throws Exception;
	
	
	
	
	
	
}
