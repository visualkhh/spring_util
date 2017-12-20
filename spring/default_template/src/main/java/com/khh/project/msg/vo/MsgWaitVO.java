package com.khh.project.msg.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.khh.DefaultVO;
import com.khh.code.Code;
import lombok.Data;

import com.google.gson.annotations.Expose;
import com.omnicns.java.date.DateUtil;

@Entity
@Table(name = "TB_MSG_WAIT")
@SequenceGenerator(
		name = "CBIS_SEQ_GENERATOR",
		sequenceName = "CBIS_SEQ",
		initialValue = 1, allocationSize = 1
)

public @Data class MsgWaitVO extends DefaultVO {
	private static final long serialVersionUID = 3957786793896721440L;
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CBIS_SEQ_GENERATOR")
	@Expose @Id @Column(name="WAIT_ID")	Integer wait_id	;//예약ID	NUMBER
	@Expose @Column(name="APPROVE_DATE")	String approve_date	;//승인날짜	CHAR
	@Expose @Column(name="APPROVE_ID")	Integer approve_id	;//승인ID	NUMBER
	@Expose @Column(name="APPROVE_TIME")	String approve_time	;//승인시간	CHAR
	@Expose @Column(name="CALLBACK_DATA")	String callback_data	;//콜백내용	VARCHAR2
	@Expose @Column(name="CALLBACK_TYPE")	String callback_type	;//콜백종류	CHAR
	@Expose @Column(name="CH_ID")	String ch_id	;//	VARCHAR2
	@Expose @Column(name="CLASS_ID")	Integer class_id	;//정보분류ID	NUMBER
	@Expose @Column(name="CONTENTS_ALL")	String contents_all	;//전체 컨텐츠내용	VARCHAR2
	@Expose @Column(name="CONTENTS_KTF")	String contents_ktf	;//KTF용 컨텐츠내용	VARCHAR2
	@Expose @Column(name="CONTENTS_KT_LTE")	String contents_kt_lte	;//KT LTE	VARCHAR2
	@Expose @Column(name="CONTENTS_LGT")	String contents_lgt	;//LGT용 컨텐츠내용	VARCHAR2
	@Expose @Column(name="CONTENTS_LGU_LTE")	String contents_lgu_lte	;//LG U+ LTE	VARCHAR2
	@Expose @Column(name="CONTENTS_SKT")	String contents_skt	;//SKT용 컨텐츠내용	VARCHAR2
	@Expose @Column(name="CONTENTS_SKT_LTE")	String contents_skt_lte	;//SKT LTE	VARCHAR2
	@Expose @Column(name="CREATE_DATE")	String create_date	;//등록날짜	CHAR
	@Expose @Column(name="CREATE_ID")	Integer create_id	;//등록자ID	NUMBER
	@Expose @Column(name="CREATE_TIME")	String create_time	;//등록시간	CHAR
	@Expose @Column(name="GRADE_ID")	Integer grade_id	;//정보등급ID	NUMBER
	@Expose @Column(name="STATUS")	String status	;//상태	VARCHAR2
	@Expose @Column(name="TEST_CH")	String test_ch	;//테스트송출	CHAR
	@Expose @Column(name="WAIT_DATE")	String wait_date	;//예약날짜	CHAR
	@Expose @Column(name="WAIT_TIME")	String wait_time	;//예약시간	CHAR
	public MsgWaitVO(){
		init();
	}
	
	public void init(){
		if(null==create_date){
			create_date = DateUtil.getDate("yyyyMMdd");
		}
		if(null==create_time){
			create_time = DateUtil.getDate("HHmmss");
		}
		if(null==wait_date){
			wait_date = DateUtil.getDate("yyyyMMdd");
		}
		if(null==wait_time){
			wait_time = DateUtil.getDate("HHmmss");
		}
		if(null==status){
			status = Code.CODE_MSG_STATUS_WAIT.cd();
		}
		if(null==callback_type){
			callback_type = Code.CODE_CALLBACK_TYPE_TEXT.cd();
		}
	}
	
	public void refreshWaiteDateTimeFromNow(){
		wait_date = DateUtil.getDate("yyyyMMdd");
		wait_time = DateUtil.getDate("HHmmss");
	}
	public void refreshApproveDateTimeFromNow(){
		approve_date = DateUtil.getDate("yyyyMMdd");
		approve_time = DateUtil.getDate("HHmmss");
	}
	
	public String getContentsFirst() {
		String msg="";
		if (null != getContents_all()) {
			msg = (getContents_all());
		}else if (null != getContents_ktf()) {
			msg = (getContents_ktf());
		}else if (null != getContents_kt_lte()) {
			msg = (getContents_kt_lte());
		}else if (null != getContents_lgt()) {
			msg = (getContents_lgt());
		}else if (null != getContents_lgu_lte()) {
			msg = (getContents_lgu_lte());
		}else if (null != getContents_skt()) {
			msg = (getContents_skt());
		}else if (null != getContents_skt_lte()){
			msg = (getContents_skt_lte());
		}
		return msg;
	}
	public String getContentsFull(){
		List<String> list = new ArrayList<>();
		if(null!=getContents_all())
			list.add(getContents_all());
		if(null!=getContents_ktf())
			list.add(getContents_ktf());
		if(null!=getContents_kt_lte())
			list.add(getContents_kt_lte());
		if(null!=getContents_lgt())
			list.add(getContents_lgt());
		if(null!=getContents_lgu_lte())
			list.add(getContents_lgu_lte());
		if(null!=getContents_skt())
			list.add(getContents_skt());
		if(null!=getContents_skt_lte())
			list.add(getContents_skt_lte());
		return list.stream().collect(Collectors.joining(","));
	}
	
}
