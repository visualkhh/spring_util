package com.khh.project.msg.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

import com.google.gson.annotations.Expose;
import com.khh.DefaultVO;
import com.sun.org.glassfish.gmbal.DescriptorFields;

@Entity
@Table(name = "VW_MSG_SUBMIT_LIST")
public @Data class MsgSubmitVVO extends DefaultVO {
	private static final long serialVersionUID = -6316638820571303029L;
	 
	@Expose @DescriptorFields("등록날짜") 	@Column(name="CREATE_DATE")		String create_date	;//등록날짜	CHAR
	@Expose @DescriptorFields("등록시간") 	@Column(name="CREATE_TIME")		String create_time	;//등록시간	CHAR
	@Expose @DescriptorFields("승인날짜") 	@Column(name="APPROVE_DATE")	String approve_date	;//승인날짜	CHAR
	@Expose @DescriptorFields("승인시간") 	@Column(name="APPROVE_TIME")	String approve_time	;//승인시간	CHAR
	@Expose @DescriptorFields("송출날짜") 	@Column(name="SUBMIT_DATE")		String submit_date	;//전송날짜	CHAR
	@Expose @DescriptorFields("송출시간") 	@Column(name="SUBMIT_TIME")		String submit_time	;//전송시간	CHAR
	@Expose @DescriptorFields("이통사") 	@Column(name="COMPANY")			String company		;//이통사	CHAR
	@Expose @DescriptorFields("지역") 		@Column(name="AREAS")			String areas		;//지역 CHAR
	@Expose @DescriptorFields("송출내용") 	@Column(name="CONTENTS_ALL")	String contents_all		;//전체 컨텐츠내용	VARCHAR2
	@Expose @DescriptorFields("정보등급") 	@Column(name="GRADE_NAME")		String grade_name		;//정보등급명	VARCHAR2
	@Expose @DescriptorFields("정보분류") 	@Column(name="CLASS_NAME")		String class_name		;//정보분류명	VARCHAR2
	@Expose @DescriptorFields("등록자명") 	@Column(name="CREATE_NAME")		String create_name		;//등록자명	VARCHAR2
	@Expose @DescriptorFields("등록자소속") @Column(name="CREATE_POSITION")	String create_position	;//등록자소속	VARCHAR2
	@Expose @DescriptorFields("승인자명") 	@Column(name="APPROVE_NAME")	String approve_name		;//승인자명	VARCHAR2
	@Expose @DescriptorFields("송출채널") 	@Column(name="CH_ID")			String ch_id			;//	VARCHAR2
	@Expose @DescriptorFields("전송결과") 	@Column(name="DESCRIPTION")		String description		;//전송결과	VARCHAR2
	@Expose @Column(name="CALLBACK_DATA")									String callback_data	;//콜백내용	VARCHAR2
	@Expose @Column(name="CALLBACK_TYPE")									String callback_type	;//콜백종류	CHAR
	@Expose @Column(name="CLASS_ID")										Integer class_id		;//정보분류ID	NUMBER
	@Expose @Column(name="CONTENTS_KTF")									String contents_ktf		;//KTF 컨텐츠내용	VARCHAR2
	@Expose @Column(name="CONTENTS_KT_LTE")									String contents_kt_lte	;//	VARCHAR2
	@Expose @Column(name="CONTENTS_LGT")									String contents_lgt		;//LGT 컨텐츠내용	VARCHAR2
	@Expose @Column(name="CONTENTS_LGU_LTE")								String contents_lgu_lte	;//	VARCHAR2
	@Expose @Column(name="CONTENTS_SKT")									String contents_skt		;//SKT 컨텐츠내용	VARCHAR2
	@Expose @Column(name="CONTENTS_SKT_LTE")								String contents_skt_lte	;//	VARCHAR2
	@Expose @Column(name="CREATE_ID")										Integer create_id		;//등록자ID	NUMBER
	@Expose @Column(name="GRADE_ID")										Integer grade_id		;//정보등급ID	NUMBER
	@Expose @Column(name="STATUS")											String status			;//상태	VARCHAR2
	@Expose @Column(name="TEST_CH")											String test_ch			;//테스트송출	CHAR
	@Expose @Column(name="WAIT_DATE")										String wait_date		;//예약날짜	CHAR
	@Expose @Id @Column(name="WAIT_ID")										Integer wait_id			;//송출ID	NUMBER
	@Expose @Column(name="WAIT_TIME")										String wait_time		;//예약시간	CHAR
	@Expose @Column(name="AREAS_GROUP")										String areas_group		;//지역 CHAR
	
	@Transient
	@Expose
	boolean mine=false;
	
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
	public String getCompanyFullName(){
		List<String> list = new ArrayList<>();
		if(null!=getContents_all())
			list.add("전체");
		if(null!=getContents_ktf())
			list.add("KT 2G");
		if(null!=getContents_kt_lte())
			list.add("KT LTE");
		if(null!=getContents_lgt())
			list.add("LG 2G");
		if(null!=getContents_lgu_lte())
			list.add("LGU LTE");
		if(null!=getContents_skt())
			list.add("SKT 2G");
		if(null!=getContents_skt_lte())
			list.add("SKT LTE");
		return list.stream().collect(Collectors.joining(","));
	}
}
