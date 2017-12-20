package com.khh.project.stat.vo;

import com.google.gson.annotations.Expose;
import com.khh.DefaultVO;

public class StatVO extends DefaultVO {
	private static final long serialVersionUID = 8955057618232342717L;
	
	@Expose String area1;
	@Expose String class2;
	@Expose String submit_date;
	@Expose Integer cnt;
	
	public String getArea1() {
		return area1;
	}
	public void setArea1(String area1) {
		this.area1 = area1;
	}
	public String getClass2() {
		return class2;
	}
	public void setClass2(String class2) {
		this.class2 = class2;
	}
	public String getSubmit_date() {
		return submit_date;
	}
	public void setSubmit_date(String submit_date) {
		this.submit_date = submit_date;
	}
	public Integer getCnt() {
		return cnt;
	}
	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}
	
}
