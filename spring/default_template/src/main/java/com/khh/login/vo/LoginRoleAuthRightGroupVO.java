package com.khh.login.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import com.google.gson.annotations.Expose;
import com.khh.DefaultVO;

@Entity
@Table(name = "TB_RIGHT_GROUP")
public @Data class LoginRoleAuthRightGroupVO extends DefaultVO{
	private static final long serialVersionUID = 7272882117167781194L;
	@Expose
	@Column(name="CODE")
	String code;                                               //--????                  VARCHAR2
	@Expose
	@Column(name="NAME")
	String name;                                               //--???                   VARCHAR2
	@Expose
	@Id
	@Column(name="GROUP_ID")
	int group_id;                                           //--??ID                  NUMBER
	@Expose
	@Column(name="ICON")
	String icon;   
	

}
