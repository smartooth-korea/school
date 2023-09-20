package co.smartooth.app.service;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;



/**
 * 작성자 : 정주현 
 * 작성일 : 2023. 05. 18
 * 수정일 : 2023. 08. 29
 * 서버분리 : 2023. 08. 01
 */
public interface OrganService {
	
	
	// 기관 목록 조회
	public List<HashMap<String, Object>> selectOrganList(@Param("userId") String userId) throws Exception;
	
	
	// 측정 예정 혹은 측정 완료 기관 목록 조회 (SYSDATE 기준)
	public List<HashMap<String, Object>> selectMeasureOrganList(@Param("userId") String userId) throws Exception;
	
	
	// 부서(반) 목록 조회
	public List<HashMap<String, Object>> selectDepartmentList(@Param("organCd") String organCd) throws Exception;
	
	
	// 부서(반)에 해당하는 피측정자 회원 수 조회
	public int selectDepartmentUserCount(@Param("userId") String userId) throws Exception;
	
	
	
}
