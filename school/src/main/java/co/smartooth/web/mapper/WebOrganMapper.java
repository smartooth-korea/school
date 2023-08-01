package co.smartooth.web.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import co.smartooth.web.vo.WebAdviceVO;
import co.smartooth.web.vo.WebOrganVO;


/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 10. 14
 */
@Mapper
public interface WebOrganMapper {
	

	// 기관 실시간 검색
	public List<Map<String, Object>> ajaxSelectOrganList(@Param("searchType") String searchType, @Param("searchData") String searchData) throws Exception;

	
	// 치과 병원 실시간 검색
	public List<Map<String, Object>> ajaxSelectDentistList(@Param("searchType") String searchType, @Param("searchData") String searchData) throws Exception;
	
	
	// 치과 실시간 검색 (ajax)
	public List<Map<String, Object>> ajaxSelectDentalHospitalList(@Param("searchType") String searchType, @Param("searchData") String searchData) throws Exception;
	
	
	// 기관 주소 조회
	public WebOrganVO selectOrganInfo(@Param("schoolCode") String schoolCode) throws Exception;
	
	
	// 부서 목록 조회
	public List<HashMap<String, Object>> selectDepartmentList(@Param("searchType") String searchType, @Param("searchData") String searchData) throws Exception;
	
	
	// 자문치과 조회
	public WebAdviceVO selectAdviceDentistInfo(@Param("schoolCode") String schoolCode) throws Exception;
	
	
	// 진단지 계산 일괄 작업 여부 확인
	public String selectIsDiagnosisBatched(@Param("schoolCode") String schoolCode) throws Exception;
	
	
	// 진단지 계산 일괄 작업 여부 업데이트 (Y)
	public void updateDignosisBatchedStatus(@Param("schoolCode") String schoolCode) throws Exception;

	
	
	
	// 기관에 속한 피측정자 시퀀스 증가
	public void updateOrganUserSeqNo(WebOrganVO webOrganVO) throws Exception;
	
	
	// 기관 치아 측정일 목록 조회 (기관코드)
	public List<HashMap<String, Object>> selectOrganMeasureDtList(@Param("schoolCode") String schoolCode) throws Exception;
	
	
	
	
	
//	// 그래프 계산 일괄 작업 여부 업데이트 (Y)
//	public void updateGraphBatchedStatus(@Param("schoolCode") String schoolCode) throws Exception;
	
//	// 그래프 계산 일괄 작업 여부 확인
//	public String selectIsGraphBatched(@Param("schoolCode") String schoolCode) throws Exception;
	
	
//	// 기관 정보 등록
//	public void insertOrganInfo(AdminOrganVO adminOrganVO) throws Exception;
    
	
//	// 기관 정보 삭제
//	public void deleteOrganInfo(String schoolCode) throws Exception;
    
	
//	// 자문치과 등록
//	public void insertAdviceInfo(@Param("schoolCode") String schoolCode, @Param("dentistName") String dentistName) throws Exception;
	
	
}