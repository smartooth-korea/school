package co.smartooth.app.service.impl;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.smartooth.app.mapper.OrganMapper;
import co.smartooth.app.service.OrganService;


/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 07. 15
 * 수정일 : 2023. 08. 02
 */
@Service
public class OrganServiceImpl implements OrganService{
	
	
	@Autowired(required = false)
	OrganMapper organMapper; 

	
	
	// 등록 되어 있는 기관 목록 조회
	public List<HashMap<String, Object>> selectOrganList(@Param("userId") String userId) throws Exception{
		return organMapper.selectOrganList(userId);
	}


	
	// 등록 되어 있는 측정 예정 혹은 측정 완료 기관 목록 조회 (SYSDATE 기준)
	@Override
	public List<HashMap<String, Object>> selectMeasureOrganList(@Param("userId")  String userId) throws Exception {
		return organMapper.selectMeasureOrganList(userId);
	}
	
	
	
	// 부서 목록 조회
	@Override
	public List<HashMap<String, Object>> selectDepartmentList(@Param("organCd") String organCd) throws Exception {
		return organMapper.selectDepartmentList(organCd);
	}

	
}