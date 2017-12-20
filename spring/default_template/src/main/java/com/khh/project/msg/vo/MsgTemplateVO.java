package com.khh.project.msg.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.khh.DefaultVO;
import org.hibernate.validator.constraints.NotEmpty;

import com.google.gson.annotations.Expose;

@Entity
@SequenceGenerator(
		name = "CBIS_SEQ_GENERATOR",
		sequenceName = "CBIS_SEQ",
		initialValue = 1, allocationSize = 1
)
@Table(name="TB_MSG_TEMPLATE")
public class MsgTemplateVO extends DefaultVO {
	private static final long serialVersionUID = 2792247202465295261L;
	
	@Expose 
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "CBIS_SEQ_GENERATOR")
	@Column(name="TEMPLATE_ID")	Integer template_id	;//???ID	NUMBER
	@Expose @Column(name="CONTENTS_LGT")	String contents_lgt	;//LGT? ?????	VARCHAR2
	@Expose @Column(name="CONTENTS_KTF")	String contents_ktf	;//KTF? ?????	VARCHAR2
	@Expose @Column(name="CONTENTS_SKT")	String contents_skt	;//SKT? ?????	VARCHAR2
	@Expose @Column(name="CONTENTS_ALL")	String contents_all	;//?? ?????	VARCHAR2
	@NotEmpty
	@Expose @Column(name="TITLE")	String title	;//??????	VARCHAR2
	@Expose @Column(name="CLASS_ID")	Integer class_id	;//????ID	NUMBER
	public Integer getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(Integer template_id) {
		this.template_id = template_id;
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
	public Integer getClass_id() {
		return class_id;
	}
	public void setClass_id(Integer class_id) {
		this.class_id = class_id;
	}
	
	
}
