package co.smartooth.app.mapper;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 10. 14
 * 수정일 : 2023. 08. 02
 * 서버분리 : 2023. 08. 01
 */
@Mapper
public interface OrganMapper {
	
	
	// 등록 되어 있는 기관 목록 조회
	public List<HashMap<String, Object>> selectOrganList(@Param("userId") String userId) throws Exception;

	
	// 측정 예정 혹은 측정 완료 기관 목록 조회 (SYSDATE 기준)
	public List<HashMap<String, Object>> selectMeasureOrganList(@Param("userId") String userId) throws Exception;
	
	
	// 부서 목록 조회
	public List<HashMap<String, Object>> selectDepartmentList(@Param("organCd") String organCd) throws Exception;
	
	
	
}