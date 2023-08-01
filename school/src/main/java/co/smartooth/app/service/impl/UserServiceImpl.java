package co.smartooth.app.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.smartooth.app.mapper.UserMapper;
import co.smartooth.app.service.UserService;
import co.smartooth.app.vo.CalibrationVO;
import co.smartooth.app.vo.SchoolClassInfoVO;
import co.smartooth.app.vo.TeethInfoVO;
import co.smartooth.app.vo.TeethMeasureVO;
import co.smartooth.app.vo.ToothMeasureVO;
import co.smartooth.app.vo.UserVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 04. 28
 * 수정일 : 2022. 08. 03
 */
@Service
public class UserServiceImpl implements UserService{
	
	
	@Autowired(required = false)
	UserMapper userMapper;


	
	// 사용자 아이디 중복 체크
	@Override
	public int duplicateChkId(String userId) throws Exception {
		return userMapper.duplicateChkId(userId);
	}

	
	
	//	사용자 등록 (회원가입)
	@Override
	public void insertUserInfo(UserVO userVO) throws Exception {
		userMapper.insertUserInfo(userVO);
	}
	
	
	
	// 사용자 정보 업데이트
	@Override
	public void updateUserInfo(UserVO userVo) throws Exception {
		userMapper.updateUserInfo(userVo);
	}
	
	
	
	// 사용자 시퀀스 조회 ( 생성 전 SEQ_STR)
	@Override
	public Integer selectUserSeqNo(@Param("userType") String userType) throws Exception {
		return userMapper.selectUserSeqNo(userType);
	}
	
	
	
	// 사용자 시퀀스 조회 ( 생성 전 SEQ_STR)
	@Override
	public int selectUserSeqStr() throws Exception {
		return userMapper.selectUserSeqStr();
	}
	
	
	
	// 사용자 시퀀스 생성 후 SEQ_NO
	@Override
	public void updateUserSeqNo(@Param("userType") String userType, @Param("seqNo") int seqNo) throws Exception {
		userMapper.updateUserSeqNo(userType, seqNo);
	}

	
	
	// 사용자 시퀀스 업데이트  
	@Override
	public void updateUserSeqStr(@Param("seqStr") int seqStr) throws Exception {
		userMapper.updateUserSeqStr(seqStr);
	}

	
	
	// 사용자 정보 조회
	@Override
	public UserVO selectUserInfo(@Param("userId") String userId) throws Exception {
		return userMapper.selectUserInfo(userId);
	}
	
	
	
	// 비밀번호 변경(찾기)
	@Override
	public void updateUserPwd(UserVO userVO) throws Exception {
		userMapper.updateUserPwd(userVO);
	}
	
	
	
    // 회원의 삭제
    // @Override
    // public void deleteUser(String userId) throws Exception {
    //		userMapper.deleteUser(userId);
    // }
    

	
	// 기관 목록 조회
	@Override
	public List<SchoolClassInfoVO> selectSchoolList() throws Exception {
		return userMapper.selectSchoolList();
	}
	
	
    
    // 부서(반) 회원 리스트 조회
	@Override
	public List<UserVO> selectDepartmentList(@Param("schoolCode") String schoolCode) throws Exception {
		return userMapper.selectDepartmentList(schoolCode);
	}

	
	
	// 부서(반) ID로 해당 피측정자 목록 조회
	@Override
	public List<UserVO> selectMeasuredUserList(@Param("userId") String userId, @Param("orderBy") String orderBy) throws Exception {
		return userMapper.selectMeasuredUserList(userId, orderBy);
	}


	
	// 피측정자 상세 정보 등록
	@Override
	public void insertUserDetail(UserVO userVO) throws Exception {
		userMapper.insertUserDetail(userVO);
	}


	
	// 부서(반) ID로 피측정자 목록 조회
	@Override
	public List<UserVO> selectTestUserListByTc(@Param("userId") String userId, @Param("userName") String userName) throws Exception {
		return userMapper.selectTestUserListByTc(userId, userName);
	}


	// 피측정자 이름 변경
	@Override
	public void updateTestUserName(@Param("userId")  String userId, @Param("userName")  String userName) throws Exception {
		userMapper.updateTestUserName(userId, userName);
	}



	// 피측정자 상세 정보 조회
	@Override
	public UserVO selectUserDetail(@Param("userId")  String userId) throws Exception {
		return userMapper.selectUserDetail(userId);
	}



	// 피측정자 아이디 중복 체크 (ID와 기관코드)
	@Override
	public int duplicateChkPaUserId(@Param("userId") String userId, @Param("schoolCode") String schoolCode) throws Exception {
		return userMapper.duplicateChkPaUserId(userId, schoolCode);
	}
	
	
	

	
}