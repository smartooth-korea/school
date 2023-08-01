package co.smartooth.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import co.smartooth.web.vo.WebMailAuthVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 4. 28 ~
 */
@Mapper
public interface WebMailAuthMapper {

	// UserId로 메일 인증키 발행 후 Database에 입력
	//public void insertAuthKeyById(WebMailAuthVO webMailAuthVO) throws Exception;

	
	// 메일 인증을 요청한 사용자 DB row에 인증키(AuthKey) 업데이트
	//public void updateAuthKeyById(WebMailAuthVO webMailAuthVO) throws Exception;

	
	// 메일 인증 테이블에 USER_ID가 있는지 조회
	//public int selectMailAuthInfo(WebMailAuthVO webMailAuthVO) throws Exception;
	
	
	// 회원가입 시 입력된 아이디(이메일)을 통해 DB에 있는지 조회
	//public int selectIdWhetherOrNot(String userId) throws Exception;
	
	

	
	// 인증 메일 재 요청 시 AuthStatus 'N'로 변경
	// public void updateAuthStatusN(String userId) throws Exception;
	
	
	// 메일 인증 여부 확인 (Y, N) 
	public String isEmailAuthEnabled(@Param("userId") String userId) throws Exception;
	
	
	// 메일 인증 번호 검증
	public int isAuthKeyMatch(@Param("userId") String userId, @Param("authKey") String authKey) throws Exception;
	

	// 인증 메일 클릭 시 인증 상태를 'Y' 로 업데이트
	public void updateAuthStatusY(@Param("userId") String userId) throws Exception;

	
	
	// 메일 인증 키 업데이트
	public void insertAuthkey(@Param("userId") String userId, @Param("authEmail") String authEmail, @Param("authKey") String authKey) throws Exception;
	
	
}
