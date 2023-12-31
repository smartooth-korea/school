package co.smartooth.app.service;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import co.smartooth.app.vo.DiagnosisVO;


/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 11. 16
 * 수정일 : 2023. 08. 02
 * 서버분리 : 2023. 08. 01
 */
public interface DiagnosisService {
	
	
	// 최상위 진단 정보 조회
	public List<DiagnosisVO> selectDiagDept1List() throws Exception;

	
	// 중위 진단 정보 조회 
	public List<DiagnosisVO> selectDiagDept2List() throws Exception;

	
	// 진단 코드 및 설명 조회
	public String selectDiagDescript(@Param("descCd") String descCd) throws Exception;
	
	
	// 진단명 조회
	public String selectDiagNm(@Param("diagCd") String diagCd, @Param("diagNo") String diagNo) throws Exception;
	
	
}
