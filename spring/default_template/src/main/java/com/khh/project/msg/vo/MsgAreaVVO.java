package com.khh.project.msg.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.khh.DefaultVO;

@Entity
@Table(name = "VW_MSG_AREA")
public class MsgAreaVVO extends DefaultVO {
	private static final long serialVersionUID = 7023397489978733666L;
	@Expose @Column(name="AREA1")	String area1	;//시도	VARCHAR2
	@Expose @Column(name="AREA2")	String area2	;//시구군	VARCHAR2
	@Expose @Column(name="AREA3")	String area3	;//읍면동	VARCHAR2
	@Expose @Column(name="AREA_ID")	Integer area_id	;//지역ID	NUMBER
	@Expose @Id @Column(name="WAIT_ID")	Integer wait_id	;//송출ID	NUMBER
	
	public int getWait_id() {
		return wait_id;
	}
	public void setWait_id(int wait_id) {
		this.wait_id = wait_id;
	}
	public String getArea3() {
		return area3;
	}
	public void setArea3(String area3) {
		this.area3 = area3;
	}
	public String getArea2() {
		return area2;
	}
	public void setArea2(String area2) {
		this.area2 = area2;
	}
	public String getArea1() {
		return area1;
	}
	public void setArea1(String area1) {
		this.area1 = area1;
	}
	public int getArea_id() {
		return area_id;
	}
	public void setArea_id(int area_id) {
		this.area_id = area_id;
	}
	
}
