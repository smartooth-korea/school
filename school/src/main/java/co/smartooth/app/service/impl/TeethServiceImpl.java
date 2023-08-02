package co.smartooth.app.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.smartooth.app.mapper.TeethMapper;
import co.smartooth.app.service.TeethService;
import co.smartooth.app.vo.TeethInfoVO;
import co.smartooth.app.vo.TeethMeasureVO;
import co.smartooth.app.vo.ToothMeasureVO;
import co.smartooth.app.vo.UserVO;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 04. 28
 * 수정일 : 2023. 08. 02
 * 서버분리 : 2023. 08. 01
 */
@Service
public class TeethServiceImpl implements TeethService{
	
	
	@Autowired(required = false)
	TeethMapper teethMapper;

	
	// 피측정자 치아 측정 값 INSERT
	@Override
	public void insertUserTeethMeasureValue(TeethMeasureVO teethMeasureVO) throws Exception {
		teethMapper.insertUserTeethMeasureValue(teethMeasureVO);
	}
	
	
	
	// 피측정자 치아 측정 값 입력 UPDATE
	@Override
	public void updateUserTeethMeasureValue(TeethMeasureVO teethMeasureVO) throws Exception {
		teethMapper.updateUserTeethMeasureValue(teethMeasureVO);
	}

	
	
	// 피측정자 개별 치아 측정 값 UPDATE
	@Override
	public void updateUserToothMeasureValue(ToothMeasureVO toothMeasureVO) throws Exception {
		teethMapper.updateUserToothMeasureValue(toothMeasureVO);
		
	}
	
	
	
	// 회원 충치 개수 UPDATE (측정일별)
	@Override
	public void updateUserCavityCntByMeasureDt(TeethMeasureVO teethMeasureVO) throws Exception {
		teethMapper.updateUserCavityCntByMeasureDt(teethMeasureVO);
	}
	
	
	
	// 피측정자의 SYSDATE(오늘)의 측정 값이 있는지 여부 확인 (0 : 오늘X, 1: 오늘)
	@Override
	public Integer isExistSysDateRow(@Param("userId") String userId) throws Exception {
		return teethMapper.isExistSysDateRow(userId);
	}
	
	
	
	// 피측정자의 기존 치아 측정 값 있는지 여부 반환(0 : 없음 / 1이상: 있음)
	@Override
	public Integer isExistOldRow(@Param("userId") String userId) throws Exception {
		return teethMapper.isExistOldRow(userId);
	}
	
	
	
//	// 피측정자 치아 측정 값 조회 (기간)
//	@Override
//	public TeethMeasureVO selectUserTeethMeasureValue(TeethMeasureVO teethMeasureVO) throws Exception {
//		return dentalTeethMapper.selectUserTeethMeasureValue(teethMeasureVO);
//	}
	@Override
	public List<TeethMeasureVO> selectUserTeethMeasureValue(TeethMeasureVO teethMeasureVO) throws Exception {
		return teethMapper.selectUserTeethMeasureValue(teethMeasureVO);
	}

	
	
//	// 피측정자 치아 개별 측정 값 조회 (기간)
//	@Override
//	public ToothMeasureVO selectUserToothMeasureValue(ToothMeasureVO toothMeasureVO) throws Exception {
//		return dentalTeethMapper.selectUserToothMeasureValue(toothMeasureVO);
//	}
	@Override
	public List<ToothMeasureVO> selectUserToothMeasureValue(ToothMeasureVO toothMeasureVO) throws Exception {
		return teethMapper.selectUserToothMeasureValue(toothMeasureVO);
	}

	
	
	// 피측정자 진단 정보 조회 (측정일)
	@Override
	public TeethMeasureVO selectDiagCd(@Param("userId") String userId, @Param("measureDt") String measureDt) throws Exception{
		return teethMapper.selectDiagCd(userId, measureDt);
	}


	// 피측정자 진단 정보 업데이트 
	@Override
	public void updateDiagCd(@Param("userId") String userId, @Param("diagCd") String diagCd , @Param("measureDt") String measureDt) throws Exception {
		teethMapper.updateDiagCd(userId, diagCd, measureDt);
	}


	// 피측정자 비고(메모) 정보 조회 (측정일)
	@Override
	public TeethMeasureVO selectMemo(@Param("userId") String userId, @Param("measureDt") String measureDt) throws Exception{
		return teethMapper.selectMemo(userId, measureDt);
	}
	
	
	// 피측정자 비고(메모) 정보 업데이트
	@Override
	public void updateMemo(@Param("userId") String userId, @Param("memo") String memo, @Param("measureDt") String measureDt) throws Exception {
		teethMapper.updateMemo(userId, memo, measureDt);
	}


	
	// 피측정자 치아 정보 조회
	@Override
	public String selectTeethStatus(@Param("userId") String userId) throws Exception {
		return teethMapper.selectTeethStatus(userId);
	}



	// 피측정자 치아 정보 업데이트
	@Override
	public void updateTeethStatus(@Param("userId") String userId, @Param("teethStatus") String teethStatus, @Param("recordDt") String recordDt) throws Exception {
		teethMapper.updateTeethStatus(userId, teethStatus, recordDt);
	}



	// 피측정자 치아 정보 등록
	@Override
	public void insertTeethStatus(@Param("userId") String userId, @Param("teethStatus") String teethStatus) throws Exception {
		teethMapper.insertTeethStatus(userId, teethStatus);
	}


	
	// 피측정자 치아 상태 정보 존재 여부 확인 (오늘 날짜 기준)
	@Override
	public int isExistTeethInfo(@Param("userId") String userId, @Param("recordDt") String recordDt) throws Exception {
		return teethMapper.isExistTeethInfo(userId, recordDt);
	}
	
	
	
	// 피측정자 치아 측정 상태 목록 조회 (IsMeasuring) 
	@Override
	public List<UserVO> selectUserIsMeasuring(UserVO userVO) throws Exception {
		return teethMapper.selectUserIsMeasuring(userVO);
	}

	
	
    // 피측정자 치아 측정 상태 업데이트
	@Override
	public void updateStUserIsMeasuring(UserVO userVO) throws Exception {
		teethMapper.updateStUserIsMeasuring(userVO);
	}
	
	
	
    // 충치 단계별 수치 조회
	@Override
	public HashMap<String, Integer> selectCavityLevel() throws Exception {
		return teethMapper.selectCavityLevel();
	}



}