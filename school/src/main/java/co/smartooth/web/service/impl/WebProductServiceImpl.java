package co.smartooth.web.service.impl;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.smartooth.web.mapper.WebProductMapper;
import co.smartooth.web.service.WebProductService;
import co.smartooth.web.vo.WebProductVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 7. 15 ~
 */
@Service
public class WebProductServiceImpl implements WebProductService{
	
	
	@Autowired(required = false)
	WebProductMapper webProductMapper;


	
	// 시퀀스 넘버 조회
	@Override
	public int selectSeqNo() throws Exception {
		return webProductMapper.selectSeqNo();
	}

	
	// 시퀀스 넘버 업데이트
	@Override
	public void updateSeqNo() throws Exception{
		webProductMapper.updateSeqNo();
	}
	

	// 제품 목록 조회
	@Override
	public List<HashMap<String, Object>> selectProductInfo(@Param("searchField") String searchField, @Param("searchStr") String searchStr) throws Exception {
		return webProductMapper.selectProductInfo(searchField, searchStr);
	}


	// 제품 중복 확인
	@Override
	public int isExistProductInfo(@Param("prodNo") String prodNo) throws Exception {
		return webProductMapper.isExistProductInfo(prodNo);
	}


	// 제품 정보 등록
	@Override
	public void insertProductInfo(WebProductVO webProductVO) throws Exception {
		webProductMapper.insertProductInfo(webProductVO);
	}


	// 제품 상세 정보 등록
	@Override
	public void insertProductInfoDetail(WebProductVO webProductVO) throws Exception {
		webProductMapper.insertProductInfoDetail(webProductVO);
	}


	

	
}