package co.smartooth.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import co.smartooth.app.vo.AuthVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 04. 28
 * 수정일 : 2022. 08. 18
 */
@Mapper
public interface LogMapper {

	// 회원 로그인 기록 INSERT
	public void insertUserLoginHistory(AuthVO authVO) throws Exception;
	
	
	// 회원 접속일 UPDATE
	public void updateLoginDt(AuthVO authVO) throws Exception;
	
}
