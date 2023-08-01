package co.smartooth.web.mapper;

import org.apache.ibatis.annotations.Mapper;

import co.smartooth.web.vo.WebAuthVO;


/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 10. 14
 */
@Mapper
public interface WebLogMapper {

	// 회원 로그인 기록 INSERT
	public void insertUserLoginHistory(WebAuthVO webAuthVO) throws Exception;
	
	// 회원 접속일 UPDATE (선생님테이블)
	public void updateLoginDtbyTC(WebAuthVO webAuthVO) throws Exception;
	
	// 회원 접속일 UPDATE (학생테이블)
	public void updateLoginDtbyST(WebAuthVO webAuthVO) throws Exception;
	
	// 회원 접속일 UPDATE (부모님테이블)
	public void updateLoginDtbyPA(WebAuthVO webAuthVO) throws Exception;
	
	// 회원 접속일 UPDATE (관리자테이블)
	public void updateLoginDt(WebAuthVO webAuthVO) throws Exception;
	
}
