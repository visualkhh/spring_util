package com.khh.project.notice.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.khh.DefaultVO;
import lombok.Data;

import com.google.gson.annotations.Expose;
import com.omnicns.java.date.DateUtil;

@Entity
@Table(name = "TB_NOTICE")
@SequenceGenerator(
		name = "CBIS_SEQ_GENERATOR",
		sequenceName = "CBIS_SEQ",
		initialValue = 1, allocationSize = 1
)
public @Data class NoticeVO extends DefaultVO {
	private static final long serialVersionUID = -4267105878896155910L;
	@Expose @Column(name="CONTENTS")	String contents	;//내용	VARCHAR2
	@Expose @Column(name="CREATE_DATE")	String create_date	;//등록날짜	CHAR
	@Expose @Column(name="CREATE_ID")	Integer create_id	;//등록자ID	NUMBER
	@Expose @Column(name="CREATE_TIME")	String create_time	;//등록시간	CHAR
	@Expose @Column(name="NOTICED")	String noticed	;//게시여부	CHAR
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CBIS_SEQ_GENERATOR")
	@Expose @Id @Column(name="NOTICE_ID")	Integer notice_id	;//공지ID	NUMBER
	@Expose @Column(name="SHOW_TO")	String show_to	;//게시할 경로	VARCHAR2
	@Expose @Column(name="TITLE")	String title	;//제목	VARCHAR2
	public NoticeVO(){
		init();
	}
	private void init() {
		if(null==create_date){
			create_date = DateUtil.getDate("yyyyMMdd");
		}
		if(null==create_time){
			create_time = DateUtil.getDate("HHmmss");
		}
	}
}
