package com.khh.project.msg.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.google.gson.annotations.Expose;
import com.khh.DefaultVO;
@Entity
@Table(name="TB_MSG_CLASS")
public class MsgClassVO extends DefaultVO {
	private static final long serialVersionUID = -3240505052110517018L;
	@Expose @Column(name="CLASS2_ORDER")	Integer class2_order	;//????????	NUMBER
	@Expose @Column(name="CLASS1_ORDER")	Integer class1_order	;//??????	NUMBER
	@Expose @Column(name="CLASS2")	String class2	;//??????	VARCHAR2
	@Expose @Column(name="CLASS1")	String class1	;//????	VARCHAR2
	@NotNull
	@Expose @Id @Column(name="CLASS_ID")	Integer class_id	;//????ID	NUMBER
	
	
	public MsgClassVO() {
	}
	
	public MsgClassVO(String class1, String class2) {
		this.class1 = class1;
		this.class2 = class2;
	}
	public Integer getClass2_order() {
		return class2_order;
	}
	public void setClass2_order(Integer class2_order) {
		this.class2_order = class2_order;
	}
	public Integer getClass1_order() {
		return class1_order;
	}
	public void setClass1_order(Integer class1_order) {
		this.class1_order = class1_order;
	}
	public String getClass2() {
		return class2;
	}
	public void setClass2(String class2) {
		this.class2 = class2;
	}
	public String getClass1() {
		return class1;
	}
	public void setClass1(String class1) {
		this.class1 = class1;
	}
	public Integer getClass_id() {
		return class_id;
	}
	public void setClass_id(Integer class_id) {
		this.class_id = class_id;
	}
	
}
