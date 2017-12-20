package com.khh.login.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import com.google.gson.annotations.Expose;
import com.khh.DefaultVO;

@Entity
@Table(name = "TB_RIGHT")
public @Data class LoginRoleAuthRightVO extends DefaultVO{
	private static final long serialVersionUID = 6927644755559388415L;
	@Expose
	@Column(name="NAME")
	 String name;                                            //--????                  VARCHAR2
	@Expose
	@Id
	@Column(name="RIGHT_ID")
	 int right_id;                                        //--??ID                  NUMBER
	@Expose
	@Column(name="TYPE")
	 String type;                                            //--                      CHAR
	@Expose
	@Column(name="URL")
	 String url;                                             //--                      VARCHAR2
	@Expose
	@Column(name="DESCRIPTION")
	 String description;                                     //--??                    VARCHAR2
	@Expose
	@Column(name="CODE")
	 String code;                                            //--????                  VARCHAR2
	@Expose
	@Column(name="GROUP_ID")
	 int group_id;                                        //--????ID                NUMBER
	@Expose
	@Column(name="ACTION")
	String action;                                        
	@Expose
	@Column(name="ICON")
	String icon;                                        

}
