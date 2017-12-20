package com.khh.project.group.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.khh.DefaultVO;
@Entity
@Table(name="VW_AREA_GROUP")
public class AreaGroupVVO extends DefaultVO {
	private static final long serialVersionUID = -214856122342429317L;
	
	@Expose @Id @Column(name="GROUP_ID") Integer group_id	;//그룹ID	NUMBER
	@Expose @Column(name="NAME")	String name	;//그룹명	VARCHAR2
	@Expose @Column(name="AREA_ID")	Integer area_id	;//지역 ID	NUMBER
	@Expose @Column(name="AREA1")	String area1	;//지역1 ID	NUMBER
	@Expose @Column(name="AREA2")	String area2	;//지역2 ID	NUMBER
	@Expose @Column(name="STATUS")	String status	;//상태 ID	NUMBER
	
	public Integer getGroup_id() {
		return group_id;
	}
	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getArea_id() {
		return area_id;
	}
	public void setArea_id(Integer area_id) {
		this.area_id = area_id;
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
	 
	 
	
}
