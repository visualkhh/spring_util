package com.khh.project.area.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.khh.DefaultVO;

@Entity
@Table(name="VW_AREA")
public class AreaVVO extends DefaultVO {
	private static final long serialVersionUID = 7772635232307168982L;
	
	@Expose @Column(name="AREA1")	String area1	;//시도	VARCHAR2
	@Expose @Column(name="AREA2")	String area2	;//시구군	VARCHAR2
	@Expose @Column(name="AREA3")	String area3	;//동	VARCHAR2
	@Expose @Id @Column(name="AREA_ID")	Integer area_id	;//지역ID	NUMBER
	@Expose @Column(name="CODE")	String code	;//코드ID	VARCHAR2
	@Expose @Id @Column(name="CODE_ID")	Integer code_id	;//이통사지역ID	NUMBER
	@Expose @Id @Column(name="COMPANY_ID")	Integer company_id	;//이통사ID	NUMBER 
	public String getArea1() {
		return area1;
	}
	public void setArea1(String area1) {
		this.area1 = area1;
	}
	public String getArea2() {
		return area2;
	}
	public void setArea2(String area2) {
		this.area2 = area2;
	}
	public String getArea3() {
		return area3;
	}
	public void setArea3(String area3) {
		this.area3 = area3;
	}
	public Integer getArea_id() {
		return area_id;
	}
	public void setArea_id(Integer area_id) {
		this.area_id = area_id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getCode_id() {
		return code_id;
	}
	public void setCode_id(Integer code_id) {
		this.code_id = code_id;
	}
	public Integer getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}
	
}
