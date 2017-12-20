package com.khh.project.msg.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.google.gson.annotations.Expose;
import com.khh.DefaultVO;
import com.sun.org.glassfish.gmbal.DescriptorFields;
@Entity
@Table(name="VW_MSG_TEMPLATE")
public class MsgTemplateVVO extends DefaultVO {
	private static final long serialVersionUID = -4414006408197602759L;
	@Expose @DescriptorFields("재난유형ID") @Column(name="CLASS_ID")	Integer class_id	;//재난유형ID	NUMBER
	@Expose @DescriptorFields("재난유형") @Column(name="CLASS_NAME")	String class_name	;//재난유형	VARCHAR2
	@Expose @DescriptorFields("전체메시지") @Column(name="CONTENTS_ALL")	String contents_all	;//전체메시지	VARCHAR2
	@Expose @DescriptorFields("KT메시지") @Column(name="CONTENTS_KTF")	String contents_ktf	;//KT메시지	VARCHAR2
	@Expose @DescriptorFields("LGU+메시지") @Column(name="CONTENTS_LGT")	String contents_lgt	;//LGU+메시지	VARCHAR2
	@Expose @DescriptorFields("SKT메시지") @Column(name="CONTENTS_SKT")	String contents_skt	;//SKT메시지	VARCHAR2
	@NotNull
	@Expose @DescriptorFields("템플릿ID") @Id @Column(name="TEMPLATE_ID")	Integer template_id	;//템플릿ID	NUMBER
	@Expose @DescriptorFields("템플릿명") @Column(name="TITLE")	String title	;//템플릿명	VARCHAR2
	
	public MsgTemplateVVO() {
	}
	public MsgTemplateVVO(int template_id) {
		this.template_id=template_id;
	}
	
	
	public String getContents_lgt() {
		return contents_lgt;
	}
	public void setContents_lgt(String contents_lgt) {
		this.contents_lgt = contents_lgt;
	}
	public String getContents_ktf() {
		return contents_ktf;
	}
	public void setContents_ktf(String contents_ktf) {
		this.contents_ktf = contents_ktf;
	}
	public String getContents_skt() {
		return contents_skt;
	}
	public void setContents_skt(String contents_skt) {
		this.contents_skt = contents_skt;
	}
	public String getContents_all() {
		return contents_all;
	}
	public void setContents_all(String contents_all) {
		this.contents_all = contents_all;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	public int getClass_id() {
		return class_id;
	}
	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}
	public int getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(int template_id) {
		this.template_id = template_id;
	}
	
	
}
