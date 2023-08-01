package co.smartooth.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.smartooth.web.mapper.WebLogMapper;
import co.smartooth.web.service.WebLogService;
import co.smartooth.web.vo.WebAuthVO;


/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 10. 14
 */
@Service
public class WebLogServiceImpl implements WebLogService{

	
	@Autowired(required = false)
	WebLogMapper webLogMapper;
	
	
	// 회원 로그인 기록 INSERT
	@Override
	public void insertUserLoginHistory(WebAuthVO webAuthVO) throws Exception {
		webLogMapper.insertUserLoginHistory(webAuthVO);
	}
	
	// 회원 접속일 UPDATE (관리자테이블)
	@Override
	public void updateLoginDt(WebAuthVO webAuthVO) throws Exception {
		webLogMapper.updateLoginDt(webAuthVO);
	}
}
