package co.smartooth.app.service;

import org.springframework.stereotype.Service;

import co.smartooth.app.vo.AuthVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 04. 28
 * 수정일 : 2023. 08. 02
 * 서버분리 : 2023. 08. 01
 */
@Service
public interface LogService {
	
	// 회원 로그인 기록 INSERT
	public void insertUserLoginHistory(AuthVO authVO) throws Exception;
	
	// 회원 접속일 UPDATE
	public void updateLoginDt(AuthVO authVO) throws Exception;

	
}