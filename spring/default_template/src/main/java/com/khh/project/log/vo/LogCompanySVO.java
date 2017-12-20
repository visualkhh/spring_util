package com.khh.project.log.vo;

import javax.persistence.Column;

import com.google.gson.annotations.Expose;

public class LogCompanySVO {
	private static final long serialVersionUID = -6316638820571303029L;
	@Expose @Column(name="START_DT")	String start_dt;// 시작일
	@Expose @Column(name="END_DT")	String end_dt;// 종료일
	@Expose @Column(name="COMPANY_ID")	String company_id	;//이통사	CHAR/지역 CHAR 
	
	public String getStart_dt() {	
		if(null!=start_dt) {
			return start_dt.replace("-", "");
		}
		else {
			return start_dt;
		}
	}
	public void setStart_dt(String start_dt) {
		this.start_dt = start_dt;
	}
	public String getEnd_dt() {
		if(null!=end_dt) {
			return end_dt.replace("-", "");
		}
		else {
			return end_dt;
		} 
	}
	public void setEnd_dt(String end_dt) {
		this.end_dt = end_dt;
	}
	
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	} 
	
}
