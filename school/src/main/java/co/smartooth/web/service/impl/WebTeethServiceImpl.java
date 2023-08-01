package co.smartooth.web.service.impl;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.smartooth.web.mapper.WebTeethMapper;
import co.smartooth.web.service.WebTeethService;
import co.smartooth.web.vo.WebTeethInfoVO;
import co.smartooth.web.vo.WebTeethMeasureVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 04. 28
 * 수정일 : 2023. 03. 28
 */
@Service
public class WebTeethServiceImpl implements WebTeethService{
	
	
	
	@Autowired(required = false)
	WebTeethMapper webTeethMapper;

	
	
	// 피측정자 치아 정보 등록
	@Override
	public void insertUserTeethInfo(WebTeethInfoVO webTeethInfoVO) throws Exception {
		webTeethMapper.insertUserTeethInfo(webTeethInfoVO);
	}
	
	
	
	// 피측정자 충치 개수 UPDATE (측정일별)
	@Override
	public void updateUserCavityCntByMeasureDt(WebTeethMeasureVO webTeethMeasureVO) throws Exception {
		webTeethMapper.updateUserCavityCntByMeasureDt(webTeethMeasureVO);
	}
	
	
	
	// 피측정자의 치아 측정 값 조회 (기간)
	@Override
	public List<WebTeethMeasureVO> selectUserTeethMeasureValue(WebTeethMeasureVO webTeethMeasureVO) throws Exception {
		return webTeethMapper.selectUserTeethMeasureValue(webTeethMeasureVO);
	}
	
	
	
	// 피측정자 치아 측정 값 측정일 기준으로 조회
	@Override
    public WebTeethMeasureVO selectUserMeasureValue(@Param("userId") String userId, @Param("measureDt") String measureDt) throws Exception {
		return webTeethMapper.selectUserMeasureValue(userId, measureDt);
	}
	
	

    // 충치 단계별 수치 조회
	@Override
	public HashMap<String, Integer> selectCavityLevel() throws Exception {
		return webTeethMapper.selectCavityLevel();
	}
	
	
	
	// 진단 내용 (diag-decript) 업데이트 
	@Override
	public void updateDiagDescript(@Param("userId") String userId, @Param("measureDt") String measureDt, @Param("diagDescript") String diagDescript) throws Exception {
		webTeethMapper.updateDiagDescript(userId, measureDt ,diagDescript);
	}

	
	
	// 진단 제목 조회
	@Override
	public String selectDiagTitle(@Param("diagCd") String diagCd) throws Exception{
		return webTeethMapper.selectDiagTitle(diagCd);
	}


	
	// 피측정자들의 측정 값 통계 목록
	@Override
	public List<HashMap<String, Object>> selectUserMeasureStatisticsList(@Param("schoolCode") String schoolCode, @Param("measureDt") String measureDt) throws Exception{
		return webTeethMapper.selectUserMeasureStatisticsList(schoolCode, measureDt); 
	}

	
	
	// 측정일 목록 조회
	@Override
	public List<String> selectUserMeasureDtList(WebTeethMeasureVO webTeethMeasureVO) throws Exception {
		return webTeethMapper.selectUserMeasureDtList(webTeethMeasureVO);
	}
	
	
	
	// 악화 지수 점수 업데이트
	@Override
	public void updateUserDeteriorateScore(WebTeethMeasureVO webTeethMeasureVO) throws Exception {
		webTeethMapper.updateUserDeteriorateScore(webTeethMeasureVO);
	}
	
	
	
}