package com.khh.project.msg.vo;

import javax.persistence.Column;

import com.google.gson.annotations.Expose;

public class MsgSubmitSVO {
	private static final long serialVersionUID = -6316638820571303029L;
	@Expose @Column(name="START_DT")	String start_dt;// 시작일
	@Expose @Column(name="END_DT")	String end_dt;// 종료일
	@Expose @Column(name="CLASS_ID")	Integer class_id	;//정보분류ID	NUMBER   
	@Expose @Column(name="GRADE_ID")	Integer grade_id	;//정보등급ID	NUMBER 
	@Expose @Column(name="STATUS")	String status	;//상태	VARCHAR2
	@Expose @Column(name="SUBMIT_DATE")	String submit_date	;//전송날짜	CHAR     
	@Expose @Column(name="COMPANY")	String company	;//이통사	CHAR
	@Expose @Column(name="AREAS")	String areas	;//지역 CHAR
	@Expose @Column(name="AREAS_GROUP")	String areas_group	;//지역 CHAR 
	
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
	public Integer getClass_id() {
		return class_id;
	}
	public void setClass_id(Integer class_id) {
		this.class_id = class_id;
	}
	public Integer getGrade_id() {
		return grade_id;
	}
	public void setGrade_id(Integer grade_id) {
		this.grade_id = grade_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSubmit_date() {
		return submit_date;
	}
	public void setSubmit_date(String submit_date) {
		this.submit_date = submit_date;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getAreas() {
		return areas;
	}
	public void setAreas(String areas) {
		this.areas = areas;
	}
	public String getAreas_group() {
		return areas_group;
	}
	public void setAreas_group(String areas_group) {
		this.areas_group = areas_group;
	}
	
	
	
}
