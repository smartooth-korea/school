package co.smartooth.web.vo;

import java.io.Serializable;

public class WebProductVO implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	// 제품 ID
	private String prodId;
	// 제품명
	private String prodNm;
	// 제품번호
	private String prodNo;
	// 제품구분
	private String prodGubn;
	// 제품규격
	private String prodStandard;
	// 제품유형
	private String prodType;
	// 제조업체
	private String prodMaker;
	// 제품재고
	private int prodAmount;
	// 단가
	private int unitPrice;
	// 통화단위
	private String currencyCd;
	// 비고
	private String prodNote;
	
	
	
	
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getProdNm() {
		return prodNm;
	}
	public void setProdNm(String prodNm) {
		this.prodNm = prodNm;
	}
	public String getProdNo() {
		return prodNo;
	}
	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}
	public String getProdGubn() {
		return prodGubn;
	}
	public void setProdGubn(String prodGubn) {
		this.prodGubn = prodGubn;
	}
	public String getProdStandard() {
		return prodStandard;
	}
	public void setProdStandard(String prodStandard) {
		this.prodStandard = prodStandard;
	}
	public String getProdType() {
		return prodType;
	}
	public void setProdType(String prodType) {
		this.prodType = prodType;
	}
	public String getProdMaker() {
		return prodMaker;
	}
	public void setProdMaker(String prodMaker) {
		this.prodMaker = prodMaker;
	}
	public int getProdAmount() {
		return prodAmount;
	}
	public void setProdAmount(int prodAmount) {
		this.prodAmount = prodAmount;
	}
	public int getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getCurrencyCd() {
		return currencyCd;
	}
	public void setCurrencyCd(String currencyCd) {
		this.currencyCd = currencyCd;
	}
	public String getProdNote() {
		return prodNote;
	}
	public void setProdNote(String prodNote) {
		this.prodNote = prodNote;
	}
	
}
