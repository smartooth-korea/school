package co.smartooth.web.service.impl;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.smartooth.web.mapper.WebUserMapper;
import co.smartooth.web.service.WebUserService;
import co.smartooth.web.vo.WebUserVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 07. 15 ~
 * 수정일 : 2023. 03. 17
 */
@Service
public class WebUserServiceImpl implements WebUserService{
	
	
	@Autowired(required = false)
	WebUserMapper webUserMapper;
	
	
	// 계정 등록
	@Override
	public void insertUserInfo(WebUserVO webUserVO) throws Exception {
		webUserMapper.insertUserInfo(webUserVO);
	}

	

	// 계정 삭제
	@Override
	public void deleteUserInfo(@Param("userId") String userId) throws Exception {
		webUserMapper.deleteUserInfo(userId);
	}

	
	
	// 피측정자 정보 및 상세 정보 조회
	@Override
	public WebUserVO selectUserInfo(@Param("userId") String userId) throws Exception {
		return webUserMapper.selectUserInfo(userId);
	}
	
	
	
	
	
	
	/** COMMON **/
	
	// 피측정자 회원 아이디 조회 - ST_PARENT_USER_DEATAIL
	@Override
	public String selectStUserId(@Param("userId") String userId) throws Exception {
		return webUserMapper.selectStUserId(userId);
	}
	
	
	
	// 피측정자 회원 이름 조회 - ST_USER
	@Override
	public String selectChUserName(@Param("userId") String userId) throws Exception {
		return webUserMapper.selectStUserName(userId);
	}
	
	
	
	
	
	
	/** WEB **/
	
	// 회원 상세 정보 등록 
	@Override
	public void insertStUserDetail(String userId, String schoolType, String classCode) throws Exception {
		webUserMapper.insertStUserDetail(userId, schoolType, classCode);
	}

	

	// 피측정자 회원 정보 및 측정 데이터 조회 (회원 한명)
	@Override
	public List<HashMap<String, Object>> selectStUserInfo(String userId) throws Exception {
		return webUserMapper.selectStUserInfo(userId);
	}
	
	

	// 피측정자 회원 아이디 목록 조회 - ST_PARENT_USER_DEATAIL
	@Override
	public List<HashMap<String, Object>> selectChUserList(@Param("schoolCode") String schoolCode, @Param("measureDt") String measureDt) throws Exception {
		return webUserMapper.selectChUserList(schoolCode, measureDt);
	}
	
	
	
	// 피측정자 법정대리인 회원 상세 정보 등록 
	@Override
	public void insertPaUserDetail(@Param("userId") String userId, @Param("childId") String childId) throws Exception {
		webUserMapper.insertPaUserDetail(userId, childId);
	}
	
	
	
	// 피측정자 법정대리인 회원 비밀번호 변경
	@Override
	public void updateUserPwd(@Param("userId") String userId, @Param("userPwd") String userPwd) throws Exception {
		webUserMapper.updateUserPwd(userId, userPwd);
	}


	
	// 기관 내 피측정자 목록 조회
	@Override
	public List<HashMap<String, Object>> selectDepartUserList(@Param("classCode") String classCode, @Param("measureDt") String measureDt) throws Exception {
		return webUserMapper.selectDepartUserList(classCode, measureDt);
	}



	


	
}