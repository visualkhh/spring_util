package com.khh.project.group.vo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.gson.annotations.Expose;
import com.khh.DefaultVO;
@Entity
@Table(name="TB_AREA_GROUP")
@SequenceGenerator(
		name = "CBIS_SEQ_GENERATOR",
		sequenceName = "CBIS_SEQ",
		initialValue = 1, allocationSize = 1
)
public class AreaGroupJoinVO extends DefaultVO {
	public static final long serialVersionUID = -214856122342429317L;
	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CBIS_SEQ_GENERATOR")
	@Column(name = "GROUP_ID")
	Integer group_id;// 그룹ID NUMBER
	@Expose @NotEmpty
	@Column(name = "NAME")
	String name;// 그룹명 VARCHAR2

//	@Transient
	@Expose
	@OneToMany(fetch=FetchType.LAZY) //즉시로딩
	@JoinColumn(name="GROUP_ID", nullable=false, insertable=false, updatable=false)
	List<AreaGroupMapJoinVO> areas = new ArrayList<AreaGroupMapJoinVO>();
	
	public List<AreaGroupMapJoinVO> getAreas() {
		return areas;
	}
	public void setAreas(List<AreaGroupMapJoinVO> areas) {
		this.areas = areas;
	}
	 
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
	
}
