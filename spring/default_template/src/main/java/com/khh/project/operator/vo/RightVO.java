package com.khh.project.operator.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import com.google.gson.annotations.Expose;
import com.khh.DefaultVO;

@Entity
@Table(name = "TB_RIGHT")
public @Data class RightVO extends DefaultVO {
	private static final long serialVersionUID = 1L;
	@Expose @Column(name="URL")	String url	;//	VARCHAR2
	@Expose @Column(name="ICON")	String icon	;//	VARCHAR2
	@Expose @Column(name="ACTION")	String action	;//	VARCHAR2
	@Expose @Column(name="TYPE")	String type	;//	CHAR
	@Expose @Column(name="DESCRIPTION")	String description	;//??	VARCHAR2
	@Expose @Column(name="CODE")	String code	;//????	VARCHAR2
	@Expose @Column(name="GROUP_ID")	Integer group_id	;//????ID	NUMBER
	@Expose @Column(name="NAME")	String name	;//????	VARCHAR2
	@Expose @Id @Column(name="RIGHT_ID")	Integer right_id	;//??ID	NUMBER	
	
	
}
