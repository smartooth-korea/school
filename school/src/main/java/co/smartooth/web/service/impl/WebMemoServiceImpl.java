package co.smartooth.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.smartooth.web.mapper.WebMemoMapper;
import co.smartooth.web.service.WebMemoService;
import co.smartooth.web.vo.WebMemoVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 4. 28 ~
 */
@Service
public class WebMemoServiceImpl implements WebMemoService{
	
	
	@Autowired(required = false)
	WebMemoMapper webMemoMapper;

	
	// 측정 당시 메모 사항 조회
	@Override
	public String selectUserTeethMeasureMemo(WebMemoVO webMemoVO) throws Exception {
		return webMemoMapper.selectUserTeethMeasureMemo(webMemoVO);
	}

}