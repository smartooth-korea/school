package co.smartooth.app.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.smartooth.app.vo.SchoolClassInfoVO;
import co.smartooth.app.vo.UserVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 04. 28
 * 수정일 : 2022. 08. 03
 */
public interface UserService {
	
	
	// 사용자 아이디 중복 체크
	public int duplicateChkId(String userId) throws Exception;
	
	
	//	사용자 등록 (회원가입)
	public void insertUserInfo(UserVO userVO) throws Exception;

	
	// 사용자 정보 업데이트
	public void updateUserInfo(UserVO userVO) throws Exception;
	
	
	// 사용자 시퀀스 조회 ( 생성 전 SEQ_STR)
	public Integer selectUserSeqNo(@Param("userType") String userType) throws Exception;

	
	// 사용자 시퀀스 조회 ( 생성 전 SEQ_STR)
	public int selectUserSeqStr() throws Exception;

	
	// 사용자 시퀀스 생성 후 SEQ_NO
	public void updateUserSeqNo(@Param("userType") String userType, @Param("seqNo") int seqNo) throws Exception;

	
	// 사용자 시퀀스 업데이트  
	public void updateUserSeqStr(@Param("seqStr") int seqStr) throws Exception;

	
	// 사용자 정보 조회
	public UserVO selectUserInfo(@Param("userId") String userId) throws Exception;


	// 비밀번호 변경(찾기)
	public void updateUserPwd(UserVO userVO) throws Exception;
	
	
	// 회원 삭제 (임시)
	// public void deleteUser(String userId) throws Exception;
	
	
	// 기관 목록 조회
	public List<SchoolClassInfoVO> selectSchoolList() throws Exception;
	
	
	// 부서(반) 회원 리스트 조회
	public List<UserVO> selectDepartmentList(@Param("schoolCode") String schoolCode) throws Exception;

	
	// 부서(반) ID로 해당 피측정자 목록 조회
	public List<UserVO> selectMeasuredUserList(@Param("userId") String userId, @Param("orderBy") String orderBy) throws Exception;
	
	
	// 피측정자 상세 정보 등록
	public void insertUserDetail(UserVO userVO) throws Exception;
	
	
	// 부서(반) ID로 피측정자 목록 조회
	public List<UserVO> selectTestUserListByTc(@Param("userId") String userId, @Param("userName") String userName) throws Exception;
	
	
	// 피측정자 이름 변경
	public void updateTestUserName(@Param("userId") String userId, @Param("userName") String userName) throws Exception;
	
	
	
	
	
	
	
	
	
	
	
	
	// 피측정자 상세 정보 조회
	public UserVO selectUserDetail(@Param("userId") String userId) throws Exception;
	
	
	
	// 피측정자 아이디 중복 체크 (ID와 기관코드)
	public int duplicateChkPaUserId(@Param("userId") String userId, @Param("schoolCode") String schoolCode) throws Exception;
	
	
	
	
	}
