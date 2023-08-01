package co.smartooth.app.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.smartooth.app.mapper.DiagnosisMapper;
import co.smartooth.app.service.DiagnosisService;
import co.smartooth.app.vo.DiagnosisVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 04. 28
 * 수정일 : 2022. 08. 03
 */
@Service
public class DiagnosisServiceImpl implements DiagnosisService{

	
	@Autowired(required = false)
	DiagnosisMapper diagnosisMapper;
	
	
	
	@Override
	public List<DiagnosisVO> selectDiagDept1List() throws Exception {
		return diagnosisMapper.selectDiagDept1List();
	}

	
	
	@Override
	public List<DiagnosisVO> selectDiagDept2List() throws Exception {
		return diagnosisMapper.selectDiagDept2List();
	}

	
	
	@Override
	public String selectDiagDescript(@Param("descCd") String descCd) throws Exception {
		return diagnosisMapper.selectDiagDescript(descCd);
	}

	
	
	@Override
	public String selectDiagNm(@Param("diagCd") String diagCd, @Param("diagNo") String diagNo) throws Exception {
		return diagnosisMapper.selectDiagNm(diagCd, diagNo);
	}


	
}