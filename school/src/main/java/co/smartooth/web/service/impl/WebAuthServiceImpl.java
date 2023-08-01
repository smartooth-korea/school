package co.smartooth.web.service.impl;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import co.smartooth.web.mapper.WebAuthMapper;
import co.smartooth.web.service.WebAuthService;
import co.smartooth.web.vo.WebAuthVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 07. 18 ~
 */
@Component
@Service
public class WebAuthServiceImpl implements WebAuthService{

	
	@Autowired(required = false)
	WebAuthMapper webAuthMapper;
	
	
	
	// 회원 아이디와 비밀번호로 존재 여부 확인 :: true = 1, false = 0
	@Override
	public int loginChkByIdPwd(WebAuthVO webAuthVO) throws Exception {
		return webAuthMapper.loginChkByIdPwd(webAuthVO);
	}

	
	
	// 회원 아이디가 존재하는지 여부 확인 :: true = 1, false = 0
	@Override
	public int isIdExist(WebAuthVO webAuthVO) throws Exception {
		return webAuthMapper.isIdExist(webAuthVO);
	}

	
	// 회원 아이디와 기관 코드 대조 
	@Override
	public int loginChkByIdOrganCd(@Param("userId") String userId, @Param("organCd") String organCd) throws Exception {
		return webAuthMapper.loginChkByIdOrganCd(userId, organCd);
	}
}
