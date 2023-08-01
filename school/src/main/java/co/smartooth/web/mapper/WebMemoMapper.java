package co.smartooth.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import co.smartooth.web.vo.WebMemoVO;

/**
 * 작성자 : 정주현 작성일 : 2022. 10. 31
 */
@Mapper
public interface WebMemoMapper {

	// 측정 당시 메모 사항 조회 
	public String selectUserTeethMeasureMemo(WebMemoVO webMemoVO) throws Exception;
	
}