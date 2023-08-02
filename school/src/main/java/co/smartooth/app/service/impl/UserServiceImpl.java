package co.smartooth.app.service.impl;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.smartooth.app.mapper.UserMapper;
import co.smartooth.app.service.UserService;
import co.smartooth.app.vo.UserVO;


/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 04. 28
 * 수정일 : 2023. 08. 02
 * 서버분리 : 2023. 08. 01
 */
@Service
public class UserServiceImpl implements UserService{
	
	
	@Autowired(required = false)
	UserMapper userMapper;


	
	// 사용자 정보 조회
	@Override
	public UserVO selectUserInfo(@Param("userId") String userId) throws Exception {
		return userMapper.selectUserInfo(userId);
	}
	
	
	
	// 부서(반) ID로 해당 피측정자 목록 조회
	@Override
	public List<UserVO> selectMeasuredUserList(@Param("userId") String userId, @Param("orderBy") String orderBy) throws Exception {
		return userMapper.selectMeasuredUserList(userId, orderBy);
	}



	// 사용자 정보 업데이트
	@Override
	public void updateUserInfo(UserVO userVO) throws Exception {
		userMapper.updateUserInfo(userVO);
	}


	
	
}