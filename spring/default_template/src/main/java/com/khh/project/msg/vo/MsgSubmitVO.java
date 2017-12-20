package com.khh.project.msg.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.khh.DefaultVO;

@Entity
@Table(name = "TB_MSG_SUBMIT")
public class MsgSubmitVO extends DefaultVO {
	private static final long serialVersionUID = 8955057618232342717L;
	@Expose @Column(name="APPROVE_DATE")	String approve_date	;//승인날짜	CHAR
	@Expose @Column(name="APPROVE_ID")	Integer approve_id	;//승인ID	NUMBER
	@Expose @Column(name="APPROVE_TIME")	String approve_time	;//승인시간	CHAR
	@Expose @Column(name="CALLBACK_DATA")	String callback_data	;//콜백내용	VARCHAR2
	@Expose @Column(name="CALLBACK_TYPE")	String callback_type	;//콜백종류	CHAR
	@Expose @Column(name="CH_ID")	String ch_id	;//	VARCHAR2
	@Expose @Column(name="CLASS_ID")	Integer class_id	;//정보분류ID	NUMBER
	@Expose @Column(name="CONTENTS_ALL")	String contents_all	;//전체 컨텐츠내용	VARCHAR2
	@Expose @Column(name="CONTENTS_KTF")	String contents_ktf	;//KTF 컨텐츠내용	VARCHAR2
	@Expose @Column(name="CONTENTS_KT_LTE")	String contents_kt_lte	;//KT LTE	VARCHAR2
	@Expose @Column(name="CONTENTS_LGT")	String contents_lgt	;//LGT 컨텐츠내용	VARCHAR2
	@Expose @Column(name="CONTENTS_LGU_LTE")	String contents_lgu_lte	;//LG U+ LTE	VARCHAR2
	@Expose @Column(name="CONTENTS_SKT")	String contents_skt	;//SKT 컨텐츠내용	VARCHAR2
	@Expose @Column(name="CONTENTS_SKT_LTE")	String contents_skt_lte	;//SKT LTE	VARCHAR2
	@Expose @Column(name="CREATE_DATE")	String create_date	;//등록날짜	CHAR
	@Expose @Column(name="CREATE_ID")	Integer create_id	;//등록자ID	NUMBER
	@Expose @Column(name="CREATE_TIME")	String create_time	;//등록시간	CHAR
	@Expose @Column(name="DESCRIPTION")	String description	;//설명	VARCHAR2
	@Expose @Column(name="GRADE_ID")	Integer grade_id	;//정보등급ID	NUMBER
	@Expose @Column(name="STATUS")	String status	;//상태	VARCHAR2
	@Expose @Column(name="SUBMIT_DATE")	String submit_date	;//완료날짜	CHAR
	@Expose @Column(name="SUBMIT_TIME")	String submit_time	;//완료시간	CHAR
	@Expose @Column(name="TEST_CH")	String test_ch	;//테스트송출	CHAR
	@Expose @Column(name="WAIT_DATE")	String wait_date	;//예약날짜	CHAR
	@Expose @Id @Column(name="WAIT_ID")	Integer wait_id	;//결과ID	NUMBER
	@Expose @Column(name="WAIT_TIME")	String wait_time	;//예약시간	CHAR
	
	public String getContents_lgu_lte() {
		return contents_lgu_lte;
	}
	public void setContents_lgu_lte(String contents_lgu_lte) {
		this.contents_lgu_lte = contents_lgu_lte;
	}
	public String getContents_kt_lte() {
		return contents_kt_lte;
	}
	public void setContents_kt_lte(String contents_kt_lte) {
		this.contents_kt_lte = contents_kt_lte;
	}
	public String getContents_skt_lte() {
		return contents_skt_lte;
	}
	public void setContents_skt_lte(String contents_skt_lte) {
		this.contents_skt_lte = contents_skt_lte;
	}
	public String getTest_ch() {
		return test_ch;
	}
	public void setTest_ch(String test_ch) {
		this.test_ch = test_ch;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSubmit_time() {
		return submit_time;
	}
	public void setSubmit_time(String submit_time) {
		this.submit_time = submit_time;
	}
	public String getSubmit_date() {
		return submit_date;
	}
	public void setSubmit_date(String submit_date) {
		this.submit_date = submit_date;
	}
	public String getApprove_time() {
		return approve_time;
	}
	public void setApprove_time(String approve_time) {
		this.approve_time = approve_time;
	}
	public String getApprove_date() {
		return approve_date;
	}
	public void setApprove_date(String approve_date) {
		this.approve_date = approve_date;
	}
	public int getApprove_id() {
		return approve_id;
	}
	public void setApprove_id(int approve_id) {
		this.approve_id = approve_id;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public int getCreate_id() {
		return create_id;
	}
	public void setCreate_id(int create_id) {
		this.create_id = create_id;
	}
	public String getWait_time() {
		return wait_time;
	}
	public void setWait_time(String wait_time) {
		this.wait_time = wait_time;
	}
	public String getWait_date() {
		return wait_date;
	}
	public void setWait_date(String wait_date) {
		this.wait_date = wait_date;
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
	public String getCallback_data() {
		return callback_data;
	}
	public void setCallback_data(String callback_data) {
		this.callback_data = callback_data;
	}
	public String getCallback_type() {
		return callback_type;
	}
	public void setCallback_type(String callback_type) {
		this.callback_type = callback_type;
	}
	public int getClass_id() {
		return class_id;
	}
	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}
	public int getGrade_id() {
		return grade_id;
	}
	public void setGrade_id(int grade_id) {
		this.grade_id = grade_id;
	}
	public int getWait_id() {
		return wait_id;
	}
	public void setWait_id(int wait_id) {
		this.wait_id = wait_id;
	}
	
}
