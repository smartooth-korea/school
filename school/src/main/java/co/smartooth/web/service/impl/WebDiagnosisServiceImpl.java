package co.smartooth.web.service.impl;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.smartooth.web.mapper.WebDiagnosisMapper;
import co.smartooth.web.service.WebDiagnosisService;
import co.smartooth.web.vo.WebDiagnosisVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2023. 02. 02
 */
@Service
public class WebDiagnosisServiceImpl implements WebDiagnosisService {
	
	
	
	@Autowired(required = false)
	WebDiagnosisMapper webDiagnosisMapper;

	
	
	// 최상위 진단 정보 조회
	@Override
	public List<WebDiagnosisVO> selectDiagDept1List() throws Exception {
		return webDiagnosisMapper.selectDiagDept1List();
	}

	
	
	// 중위 진단 정보 조회
	@Override
	public List<WebDiagnosisVO> selectDiagDept2List() throws Exception {
		return webDiagnosisMapper.selectDiagDept2List();
	}

	
	
	// 진단 키워드 DESCRIPT 조회 - teethType : M, B, P
	@Override
	public String selectDiagDescript(@Param("descCd") String descCd, @Param("teethType") String teethType) throws Exception {
		return webDiagnosisMapper.selectDiagDescript(descCd, teethType);
	}
	
	
	
	
	// 진단 키워드 제목 조회
	@Override
	public String selectDiagTitle(@Param("diagCd") String diagCd, @Param("teethType") String teethType) throws Exception {
		return webDiagnosisMapper.selectDiagTitle(diagCd, teethType);
	}

	
	
	
	// 진단 코드 내용의 Count 조회
	@Override
	public int selectDiagDescriptCount(@Param("descCd") String descCd) throws Exception {
		return webDiagnosisMapper.selectDiagDescriptCount(descCd);
	}





	
	
}
