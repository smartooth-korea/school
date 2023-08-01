package co.smartooth.web.vo;


/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 11. 04
 */
public class WebSeqNoVO {
	
	// 학교 코드 시퀀스 넘버
	private int schSeqNo;
	// 반 코드 시퀀스 넘버
	private int classSeqNo;
	// 일반 회원 시퀀스 넘버
	private int grSeqNo;
	// 학생 회원 시퀀스 넘버
	private int stSeqNo;
	// 부모님 회원 시퀀스 넘버 
	private int paSeqNo;
	// 선생님 회원 시퀀스 넘버
	private int tcSeqNo;
	// 코디 회원 시퀀스 넘버
	private int coSeqNo;
	// 관리자 회원 시퀀스 넘버
	private int maSeqNo;
	// 학생 회원 아이디 시퀀스 알파벳 (ex : A = 65)
	private int stSeqStr;
	// 반 회원 아이디 시퀀스 알파벳 (ex : A = 65)
	private int clSeqStr;
	// 일반 회원 시퀀스 알파벳 (ex : A = 65)
	private int grSeqStr;
	// 자녀 회원 시퀀스 알파벳 (ex : A = 65)
	private int chSeqStr;
	
	
	
	public int getSchSeqNo() {
		return schSeqNo;
	}
	public void setSchSeqNo(int schSeqNo) {
		this.schSeqNo = schSeqNo;
	}
	public int getClassSeqNo() {
		return classSeqNo;
	}
	public void setClassSeqNo(int classSeqNo) {
		this.classSeqNo = classSeqNo;
	}
	public int getGrSeqNo() {
		return grSeqNo;
	}
	public void setGrSeqNo(int grSeqNo) {
		this.grSeqNo = grSeqNo;
	}
	public int getStSeqNo() {
		return stSeqNo;
	}
	public void setStSeqNo(int stSeqNo) {
		this.stSeqNo = stSeqNo;
	}
	public int getPaSeqNo() {
		return paSeqNo;
	}
	public void setPaSeqNo(int paSeqNo) {
		this.paSeqNo = paSeqNo;
	}
	public int getTcSeqNo() {
		return tcSeqNo;
	}
	public void setTcSeqNo(int tcSeqNo) {
		this.tcSeqNo = tcSeqNo;
	}
	public int getCoSeqNo() {
		return coSeqNo;
	}
	public void setCoSeqNo(int coSeqNo) {
		this.coSeqNo = coSeqNo;
	}
	public int getMaSeqNo() {
		return maSeqNo;
	}
	public void setMaSeqNo(int maSeqNo) {
		this.maSeqNo = maSeqNo;
	}
	public int getStSeqStr() {
		return stSeqStr;
	}
	public void setStSeqStr(int stSeqStr) {
		this.stSeqStr = stSeqStr;
	}
	public int getClSeqStr() {
		return clSeqStr;
	}
	public void setClSeqStr(int clSeqStr) {
		this.clSeqStr = clSeqStr;
	}
	public int getGrSeqStr() {
		return grSeqStr;
	}
	public void setGrSeqStr(int grSeqStr) {
		this.grSeqStr = grSeqStr;
	}
	public int getChSeqStr() {
		return chSeqStr;
	}
	public void setChSeqStr(int chSeqStr) {
		this.chSeqStr = chSeqStr;
	}
	
	
}
