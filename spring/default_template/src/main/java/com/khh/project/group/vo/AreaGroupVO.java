package com.khh.project.group.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.khh.DefaultVO;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="TB_AREA_GROUP")
@SequenceGenerator(
		name = "CBIS_SEQ_GENERATOR",
		sequenceName = "CBIS_SEQ",
		initialValue = 1, allocationSize = 1
)
public class AreaGroupVO extends DefaultVO {
	public static final long serialVersionUID = -214856122342429317L;

	@Expose @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CBIS_SEQ_GENERATOR")
			@Column(name = "GROUP_ID") Integer group_id;// 그룹ID NUMBER
	@Expose	@Column(name = "NAME")	String name;// 그룹명 VARCHAR2
	@Expose	@Column(name = "STATUS") String status;// 상태 CHAR

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
