package com.khh.project.group.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.khh.DefaultVO;

@Entity
@Table(name="TB_AREA_GROUP_MAP")
public class AreaGroupMapVO extends DefaultVO {
	private static final long serialVersionUID = -7494917847319537925L;

	public AreaGroupMapVO() {
	}
	public AreaGroupMapVO(Integer group_id,Integer area_id) {
		this.group_id=group_id;
		this.area_id=area_id;
	}
	@Expose @Id @Column(name="GROUP_ID")	Integer group_id	;//??ID	NUMBER
	@Expose @Id @Column(name="AREA_ID")	Integer area_id	;//??ID	NUMBER
	public Integer getArea_id() {
		return area_id;
	}
	public void setArea_id(Integer area_id) {
		this.area_id = area_id;
	}
	public Integer getGroup_id() {
		return group_id;
	}
	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}
	

}
