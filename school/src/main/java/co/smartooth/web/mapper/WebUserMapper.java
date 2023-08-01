package co.smartooth.web.mapper;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import co.smartooth.web.vo.WebUserVO;


/**
 * 작성자 : 정주현
 * 작성일 : 2022. 10. 14
 * 수정일 : 2023. 07. 28
 * 서버 분리 : 2023. 07. 28
 */
@Mapper
public interface WebUserMapper {
	
	
	// 피측정자 계정 등록
	public void insertUserInfo(WebUserVO webUserVO) throws Exception;
	
	
		
	// 피측정자 계정 삭제
	public void deleteUserInfo(@Param("userId") String userId) throws Exception;
	
	
	
	// 피측정자 정보 및 상세 정보 조회
	public WebUserVO selectUserInfo(@Param("userId") String userId) throws Exception;

	
	
	
	
	
	/** COMMON **/
	
	// 피측정자 아이디 조회 - ST_PARENT_USER_DEATAIL
	public String selectStUserId(@Param("userId") String userId) throws Exception;

	
	
	// 피측정자 이름 조회 - ST_USER
	public String selectStUserName(@Param("userId") String userId) throws Exception;

	
	
	
	
	
	/** WEB **/
	
	// 피측정자 상세 정보 등록 
	public void insertStUserDetail(@Param("userId") String userId, @Param("schoolType") String schoolType, @Param("classCode") String classCode) throws Exception;
	
	
	
	// 피측정자 정보 및 측정 데이터 조회 (회원 한명)
	public List<HashMap<String, Object>> selectStUserInfo(String userId) throws Exception;
	
	
	
	// 피측정자 아이디 목록 조회 - ST_PARENT_USER_DEATAIL
	public List<HashMap<String, Object>> selectChUserList(@Param("schoolCode") String schoolCode, @Param("measureDt") String measureDt) throws Exception;
	
	
	
	// 피측정자 법정대리인 상세 정보 등록 
	public void insertPaUserDetail(@Param("userId") String userId, @Param("childId") String childId) throws Exception;
	
	
	
	// 첫 로그인 >> 이메일 인증 후 비밀번호 변경
	public void updateUserPwd(@Param("userId") String userId, @Param("userPwd") String userPwd) throws Exception;

	
	
	// 기관 내 피측정자 목록 조회
	public List<HashMap<String, Object>> selectDepartUserList(@Param("classCode") String classCode, @Param("measureDt") String measureDt) throws Exception;

	
	
}