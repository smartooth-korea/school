package co.smartooth.web.vo;

import java.io.Serializable;

public class WebAdviceVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private String schoolCode;
	
	private String dentistName;
	
	private String dentistNum;

	
	
	
	public String getSchoolCode() {
		return schoolCode;
	}

	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}

	public String getDentistName() {
		return dentistName;
	}

	public void setDentistName(String dentistName) {
		this.dentistName = dentistName;
	}
	
	public String getDentistNum() {
		return dentistNum;
	}

	public void setDentistNum(String dentistNum) {
		this.dentistNum = dentistNum;
	}
	
	

	
	
	
}
