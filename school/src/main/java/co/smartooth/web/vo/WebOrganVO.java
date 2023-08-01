package co.smartooth.web.vo;

import java.io.Serializable;

public class WebOrganVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String schoolCode;
	
	private String schoolName;
	
	private String classCode;
	
	private String className;
	
	private String organSidoNm;
	
	private String organSigunguNm;
	
	private String organEupmyeondongNm;

	private int userSeqNo;
	
	private String isVisible;
	
	
	
	public String getSchoolCode() {
		return schoolCode;
	}

	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getUserSeqNo() {
		return userSeqNo;
	}

	public void setUserSeqNo(int userSeqNo) {
		this.userSeqNo = userSeqNo;
	}

	public String getOrganSidoNm() {
		return organSidoNm;
	}

	public void setOrganSidoNm(String organSidoNm) {
		this.organSidoNm = organSidoNm;
	}

	public String getOrganSigunguNm() {
		return organSigunguNm;
	}

	public void setOrganSigunguNm(String organSigunguNm) {
		this.organSigunguNm = organSigunguNm;
	}

	public String getOrganEupmyeondongNm() {
		return organEupmyeondongNm;
	}

	public void setOrganEupmyeondongNm(String organEupmyeondongNm) {
		this.organEupmyeondongNm = organEupmyeondongNm;
	}
	
	public String getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(String isVisible) {
		this.isVisible = isVisible;
	}
	
}
