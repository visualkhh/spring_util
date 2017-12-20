package com.khh.project.area.vo;

import javax.persistence.Column;
import javax.persistence.Entity; 
import javax.persistence.Id; 
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.google.gson.annotations.Expose;
import com.khh.DefaultVO;

@Entity
@Table(name="TB_AREA_BASE")
public class AreaBaseVO extends DefaultVO {
	private static final long serialVersionUID = 7772635232307168982L;

	@NotNull
	@Expose @Id @Column(name="AREA_ID")	Integer area_id	;//지역ID	NUMBER 
	@Expose @Id @Column(name="COMPANY_ID")	Integer company_id;//시도	VARCHAR2
	@Expose @Id @Column(name="AREA1")	String area1	;//시도	VARCHAR2
	@Expose @Id @Column(name="AREA2")	String area2	;//시구군	VARCHAR2
	@Expose @Id @Column(name="AREA3")	String area3	;//동	VARCHAR2
	@Expose @Id @Column(name="AREA4")	String area4	;//동	VARCHAR2
	@Expose @Id @Column(name="AREA5")	String area5	;//동	VARCHAR2 
	
	public Integer getArea_id() {
		return area_id;
	}
	public void setArea_id(Integer area_id) {
		this.area_id = area_id;
	}
	public Integer getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}
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
	public String getArea4() {
		return area4;
	}
	public void setArea4(String area4) {
		this.area4 = area4;
	}
	public String getArea5() {
		return area5;
	}
	public void setArea5(String area5) {
		this.area5 = area5;
	}


}
