package co.smartooth.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.smartooth.app.mapper.LogMapper;
import co.smartooth.app.service.LogService;
import co.smartooth.app.vo.AuthVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 04. 28
 * 수정일 : 2023. 08. 02
 * 서버분리 : 2023. 08. 01
 */
@Service
public class LogServiceImpl implements LogService{

	
	@Autowired(required = false)
	LogMapper logMapper;
	
	
	// 회원 로그인 기록 INSERT
	@Override
	public void insertUserLoginHistory(AuthVO authVO) throws Exception {
		logMapper.insertUserLoginHistory(authVO);
	}
	
	// 회원 접속일 UPDATE
	@Override
	public void updateLoginDt(AuthVO authVO) throws Exception {
		logMapper.updateLoginDt(authVO);
	}
	
}
