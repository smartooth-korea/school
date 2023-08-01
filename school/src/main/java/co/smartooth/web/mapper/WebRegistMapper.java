package co.smartooth.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import co.smartooth.web.vo.WebLocationVO;
import co.smartooth.web.vo.WebSeqNoVO;

/**
 * 작성자 : 정주현 작성일 : 2022. 10. 14
 */
@Mapper
public interface WebRegistMapper {

	
	// 나라 이름으로 나라 코드 조회 :: ISO_ALPHA2 코드 조회
	public String selectCountryCd(String countryNm) throws Exception;

	
	
	// 시도 이름으로 시도 코드 조회
	public String selectSidoCd(String siDoNm) throws Exception;

	
	
	// 지역 정보 조회
	public WebLocationVO selectLocationInfo(WebLocationVO webLocationVO) throws Exception;

	
	
	// 시퀀스 번호 조회
	public WebSeqNoVO selectSeqNoAll() throws Exception;

	
	
	// 시퀀스 번호 업데이트
	public void updateSeqNo(@Param("seqNm") String seqNm, @Param("seqNo") String seqNo) throws Exception;

	
	
	// 기관 정보 등록
	public void insertSchInfo(@Param("schCd") String schCd, @Param("schNm") String schNm,
			@Param("sidoNm") String sidoNm, @Param("sigunguNm") String sigunguNm,
			@Param("eupmyeondongNm") String eupmyeondongNm) throws Exception;

	
	
	// 부서 정보 등록
	public void insertClassInfo(@Param("classCd") String classCd, @Param("classNm") String classNm) throws Exception;
	
	
	
	// 측정 회원 부모님 아이디 등록
	public void insertUserCommonInfo(
				@Param("userId") String userId,
				@Param("userPwd") String userPwd,
				@Param("userType") String userType,
				@Param("userName") String userName,
				@Param("userBirthday") String userBirthday,
				@Param("countryCd") String countryCd,
				@Param("userAddr") String userAddr,
				@Param("userTelNo") String userTelNo,
				@Param("userSex") String userSex,
				@Param("countryNm") String countryNm,
				@Param("sidoNm") String sidoNm,
				@Param("sigunguNm") String sigunguNm,
				@Param("eupmyeondongNm") String eupmyeondongNm
			) throws Exception;

	
	
	// 부서 아이디 상세 정보 등록
	public void insertOrganUserDetail(@Param("userId") String userId, @Param("schCd") String schCd,
			@Param("departCd") String departCd) throws Exception;
	
	
	
	// 측정 회원 부모님 아이디 상세 정보 등록
	public void insertPaUserDetail(@Param("userId") String userId, @Param("childId") String childId) throws Exception;
	
	
	
	// 측정 회원 상세 정보 등록
	public void insertStUserDetail(@Param("userId") String userId, @Param("schType") String schType, @Param("teacherId") String tcUserId) throws Exception;
	
	
	
	// 측정 회원 치아 정보 등록
	public void insertStUserTeethInfo(@Param("userId") String userId) throws Exception;
	
}