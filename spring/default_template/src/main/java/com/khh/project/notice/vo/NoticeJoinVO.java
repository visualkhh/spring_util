package com.khh.project.notice.vo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.google.gson.annotations.Expose;
import com.khh.DefaultVO;

import lombok.Data;

@Entity
@Table(name = "TB_NOTICE")
public @Data class NoticeJoinVO extends DefaultVO {
	private static final long serialVersionUID = -4267105878896155910L;
	@Expose @Column(name="CONTENTS")	String contents	;//내용	VARCHAR2
	@Expose @Column(name="CREATE_DATE")	String create_date	;//등록날짜	CHAR
	@Expose @Column(name="CREATE_ID")	Integer create_id	;//등록자ID	NUMBER
	@Expose @Column(name="CREATE_TIME")	String create_time	;//등록시간	CHAR
	@Expose @Column(name="NOTICED")	String noticed	;//게시여부	CHAR
	@Expose @Id @Column(name="NOTICE_ID")	Integer notice_id	;//공지ID	NUMBER
	@Expose @Column(name="SHOW_TO")	String show_to	;//게시할 경로	VARCHAR2
	@Expose @Column(name="TITLE")	String title	;//제목	VARCHAR2
	
	@Expose
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="NOTICE_ID")
	List<NoticeFileVO> files = new ArrayList<NoticeFileVO>();
	
}
