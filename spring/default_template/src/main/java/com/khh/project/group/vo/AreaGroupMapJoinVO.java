package com.khh.project.group.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.khh.DefaultVO;
import com.khh.project.area.vo.AreaVO;
@Entity
@Table(name="TB_AREA_GROUP_MAP")
public class AreaGroupMapJoinVO extends DefaultVO {
	private static final long serialVersionUID = -7494917847309537925L;

	
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
	
	@Expose
	@OneToOne(fetch=FetchType.EAGER) //즉시로딩
	@JoinColumn(name="AREA_ID", nullable=false, insertable=false, updatable=false)
	AreaVO area = null;

	public AreaVO getArea() {
		return area;
	}

	public void setArea(AreaVO area) {
		this.area = area;
	}

}
