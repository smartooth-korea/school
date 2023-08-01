package co.smartooth.web.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.smartooth.web.mapper.WebRegistMapper;
import co.smartooth.web.service.WebRegistService;
import co.smartooth.web.vo.WebLocationVO;
import co.smartooth.web.vo.WebSeqNoVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 4. 28 ~
 */
@Service
public class WebRegistServiceImpl implements WebRegistService {

	@Autowired(required = false)
	WebRegistMapper webRegistMapper;

	
	
	// 나라 이름으로 나라 코드 조회 :: ISO_ALPHA2 코드 조회
	@Override
	public String selectCountryCd(String countryNm) throws Exception {
		return webRegistMapper.selectCountryCd(countryNm);
	}

	
	
	// 시도 이름으로 시도 코드 조회
	@Override
	public String selectSiDoCd(String siDoNm) throws Exception {
		return webRegistMapper.selectSidoCd(siDoNm);
	}

	
	
	// 지역 정보 조회
	@Override
	public WebLocationVO selectLocationInfo(WebLocationVO webLocationVO) throws Exception {
		return webRegistMapper.selectLocationInfo(webLocationVO);
	}

	
	
	// 시퀀스 번호 조회
	@Override
	public WebSeqNoVO selectSeqNoAll() throws Exception {
		return webRegistMapper.selectSeqNoAll();
	}

	
	
	// 기관 코드 시퀀스 번호 증가
	@Override
	public void updateSeqNo(@Param("seqNm") String seqNm, @Param("seqNo") String seqNo) throws Exception {
		webRegistMapper.updateSeqNo(seqNm, seqNo);
	}

	
	
	// 기관 정보 등록
	@Override
	public void insertSchInfo(@Param("schCd") String schCd, @Param("schNm") String schNm,
			@Param("sidoNm") String sidoNm, @Param("sigunguNm") String sigunguNm,
			@Param("eupmyeondongNm") String eupmyeondongNm) throws Exception {
		webRegistMapper.insertSchInfo(schCd, schNm, sidoNm, sigunguNm, eupmyeondongNm);
	}

	
	
	// 부서 정보 등록
	@Override
	public void insertClassInfo(@Param("classCd") String classCd, @Param("classNm") String classNm) throws Exception {
		webRegistMapper.insertClassInfo(classCd, classNm);
	}

	
	
	// 측정 회원 부모님 아이디 등록
	@Override
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
			) throws Exception {

		webRegistMapper.insertUserCommonInfo(userId, userPwd, userType, userName, userBirthday, countryCd, userAddr, userTelNo, userSex, countryNm, sidoNm, sigunguNm, eupmyeondongNm);

	}

	
	
	// 부서 상세 정보 등록
	@Override
	public void insertOrganUserDetail(@Param("userId") String userId, @Param("schCd") String schCd, @Param("departCd") String departCd) throws Exception {
		webRegistMapper.insertOrganUserDetail(userId, schCd, departCd);
	}
	
	
	
	// 측정 회원 부모님 상세 정보 등록
	@Override
	public void insertPaUserDetail(@Param("userId") String userId, @Param("childId") String childId) throws Exception {
		webRegistMapper.insertPaUserDetail(userId, childId);
	}


	// 측정 회원 상세 정보 등록
	@Override
	public void insertStUserDetail(@Param("userId") String userId, @Param("schType") String schType, @Param("tcUserId") String tcUserId) throws Exception {
		webRegistMapper.insertStUserDetail(userId, schType, tcUserId);
	}


	// 측정 회원 치아 정보 등록
	@Override
	public void insertStUserTeethInfo(@Param("userId") String userId) throws Exception {
		webRegistMapper.insertStUserTeethInfo(userId);
	}
	
	

}