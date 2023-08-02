package co.smartooth.app.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import co.smartooth.app.vo.UserVO;


/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 04. 28
 * 수정일 : 2023. 08. 02
 * 서버분리 : 2023. 08. 01
 */
@Mapper
public interface UserMapper {
	
	
	// 사용자 정보 조회
	public UserVO selectUserInfo(@Param("userId") String userId) throws Exception;


	// 부서(반) ID로 해당 피측정자 목록 조회
	public List<UserVO> selectMeasuredUserList(@Param("userId") String userId, @Param("orderBy") String orderBy) throws Exception;
	

	// 사용자 정보 업데이트
	public void updateUserInfo(UserVO userVO) throws Exception;
	
	
}