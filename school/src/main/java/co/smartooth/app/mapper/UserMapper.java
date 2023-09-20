package co.smartooth.app.mapper;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import co.smartooth.app.vo.UserVO;


/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 04. 28
 * 수정일 : 2023. 08. 29
 * 서버분리 : 2023. 08. 01
 */
@Mapper
public interface UserMapper {
	
	
	// 회원 정보 조회
	public UserVO selectUserInfo(@Param("userId") String userId) throws Exception;


	// 회원 정보 업데이트
	public void updateUserInfo(UserVO userVO) throws Exception;

	
	// 부서(반) ID로 해당 피측정자 목록 조회
	public List<HashMap<String, Object>> selectMeasuredUserList(@Param("userId") String userId, @Param("orderBy") String orderBy) throws Exception;
	
	
	// 피측정자의 법정대리인 정보 조회
	public HashMap<String, Object> selectPrUserInfo(@Param("userId") String userId) throws Exception;
	
	
	
	
	
	
	// 측정자 등록
	public void insertMeasurerInfo(@Param("measurerId") String measurerId, @Param("measurerNm") String measurerNm, @Param("measurerEmail") String measurerEmail, @Param("measurerTelNo") String measurerTelNo) throws Exception;
	
	
	// 측정자 수정(업데이트)
	public void updateMeasurerInfo(@Param("measurerId") String measurerId, @Param("measurerNm") String measurerNm, @Param("measurerEmail") String measurerEmail, @Param("measurerTelNo") String measurerTelNo) throws Exception;
	
	
	// 측정자 목록 조회
	public List<HashMap<String, Object>> selectMeasurerList() throws Exception;
	
	
	
	
	
}