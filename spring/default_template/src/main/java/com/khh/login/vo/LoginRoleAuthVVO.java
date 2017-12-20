package com.khh.login.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

import com.google.gson.annotations.Expose;
import com.khh.DefaultVO;


@Entity
@Table(name = "VW_OP_RIGHT")
public @Data class LoginRoleAuthVVO extends DefaultVO{
	private static final long serialVersionUID = 8193792338950852286L;
	@Column(name="CREATE_TIME")
	 String create_time;                                        //--????                  CHAR
	@Column(name="CREATE_DATE")
	 String create_date;                                        //--????                  CHAR
	@Column(name="CREATE_ID")
	int create_id;                                          //--??ID                  NUMBER
	@Id
	@Column(name="RIGHT_ID")
	 int right_id;                                           //--??ID                  NUMBER
	@Expose
	@Id
	@Column(name="GROUP_ID")
	int group_id;                                           //--??ID                  NUMBER
	@Expose
	@Id
	@Column(name="OPERATOR_ID")
	int operator_id;                                        //--???ID                 NUMBER
	
	@Expose
	@ManyToOne
	@JoinColumn(name="RIGHT_ID", nullable=false,updatable=false,insertable=false)
	LoginRoleAuthRightVO right;
	@Expose
	@ManyToOne
	@JoinColumn(name="GROUP_ID", nullable=false,updatable=false,insertable=false)
	LoginRoleAuthRightGroupVO right_group;
	
	

}
