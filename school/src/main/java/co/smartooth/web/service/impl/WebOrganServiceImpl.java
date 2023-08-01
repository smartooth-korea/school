package co.smartooth.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.smartooth.web.mapper.WebOrganMapper;
import co.smartooth.web.service.WebOrganService;
import co.smartooth.web.vo.WebAdviceVO;
import co.smartooth.web.vo.WebOrganVO;


/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 7. 15 ~
 */
@Service
public class WebOrganServiceImpl implements WebOrganService{
	
	
	@Autowired(required = false)
	WebOrganMapper webOrganMapper; 

	
	// 기관 실시간 검색
	@Override
	public List<Map<String, Object>> ajaxSelectOrganList(@Param("searchType") String searchType, @Param("searchData") String searchData) throws Exception {
		return webOrganMapper.ajaxSelectOrganList(searchType, searchData);
	}

	
	// 치과 병원 실시간 검색
	@Override
	public List<Map<String, Object>> ajaxSelectDentistList(@Param("searchType") String searchType, @Param("searchData") String searchData) throws Exception {
		return webOrganMapper.ajaxSelectDentistList(searchType, searchData);
	}
	
	
	// 치과 실시간 검색 (ajax)
	@Override
	public List<Map<String, Object>> ajaxSelectDentalHospitalList(@Param("searchType") String searchType, @Param("searchData")  String searchData) throws Exception {
		return webOrganMapper.ajaxSelectDentalHospitalList(searchType, searchData);
	}

	
//	// 기관 정보 등록
//	@Override
//	public void insertOrganInfo(AdminOrganVO adminOrganVO) throws Exception {
//		adminOrganMapper.insertOrganInfo(adminOrganVO);
//	}

	
//	// 기관 정보 삭제
//	@Override
//	public void deleteOrganInfo(String schoolCode) throws Exception {
//		adminOrganMapper.deleteOrganInfo(schoolCode);
//	}

	
//	// 자문치과 등록
//	@Override
//	public void insertAdviceInfo(@Param("schoolCode") String schoolCode, @Param("dentistName") String dentistName) throws Exception{
//		adminOrganMapper.insertAdviceInfo(schoolCode, dentistName);
//	}
	
	
	// 기관 주소 조회
	@Override
	public WebOrganVO selectOrganInfo(@Param("schoolCode") String schoolCode) throws Exception {
		return webOrganMapper.selectOrganInfo(schoolCode);
	}

	
	// 부서 목록 조회
	@Override
	public List<HashMap<String, Object>> selectDepartmentList(@Param("searchType") String searchType, @Param("searchData") String searchData) throws Exception {
		return webOrganMapper.selectDepartmentList(searchType, searchData);
	}
	
	
	// 자문치과 조회
	@Override
	public WebAdviceVO selectAdviceDentistInfo(@Param("schoolCode") String schoolCode) throws Exception {
		return webOrganMapper.selectAdviceDentistInfo(schoolCode);
	}
	
	
//	// 진단지 계산 일괄 작업 여부 확인
//	@Override
//	public String selectIsDiagnosisBatched(@Param("schoolCode") String schoolCode) throws Exception {
//		return webOrganMapper.selectIsDiagnosisBatched(schoolCode);
//	}


//	// 진단지 계산 일괄 작업 여부 업데이트 (Y)
//	@Override
//	public void updateDignosisBatchedStatus(@Param("schoolCode") String schoolCode) throws Exception {
//		webOrganMapper.updateDignosisBatchedStatus(schoolCode);
//	}
	
	
//	// 그래프 계산 일괄 작업 여부 확인
//	@Override
//	public String selectIsGraphBatched(@Param("schoolCode") String schoolCode) throws Exception {
//		return webOrganMapper.selectIsGraphBatched(schoolCode);
//	}

	
//	// 그래프 계산 일괄 작업 여부 업데이트 (Y)
//	@Override
//	public void updateGraphBatchedStatus(@Param("schoolCode") String schoolCode) throws Exception {
//		webOrganMapper.updateGraphBatchedStatus(schoolCode);
//	}


	// 기관에 속한 피측정자 시퀀스 증가
	@Override
	public void updateOrganUserSeqNo(WebOrganVO webOrganVO) throws Exception {
		webOrganMapper.updateOrganUserSeqNo(webOrganVO);
	}


	
	// 기관 치아 측정일 목록 조회 (기관코드)
	@Override
//	public HashMap<String, Object> selectOrganMeasureDtList(@Param("schoolCode") String schoolCode) throws Exception {
	public List<HashMap<String, Object>> selectOrganMeasureDtList(@Param("schoolCode") String schoolCode) throws Exception {
		return webOrganMapper.selectOrganMeasureDtList(schoolCode);
	}
	
}