package co.smartooth.app.vo;

import java.io.Serializable;

public class SchoolClassInfoVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String schoolCode;
	
	private String schoolName;
	
	private String classCode;
	
	private String className;
	
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

	public String getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(String isVisible) {
		this.isVisible = isVisible;
	}
	
	
}
