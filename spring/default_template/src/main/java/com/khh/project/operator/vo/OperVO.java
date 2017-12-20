package com.khh.project.operator.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.khh.DefaultVO;

@Entity
@Table(name = "TB_OPER")
public class OperVO extends DefaultVO {
	private static final long serialVersionUID = -4267105878896155910L;
	@Id
	@Expose
	@Column(name="NOTICE_ID")
	int notice_id;// number no 1 ??id
	@Expose
	@Column(name="TITLE")
	String title;// varchar2(1000 byte) no 2 ??
	@Expose
	@Column(name="CONTENTS")
	String contents;// varchar2(4000 byte) no 3 ??
	@Expose
	@Column(name="NOTICED")
	String noticed; // char(1 byte) no 'n' 4 ????
	@Expose
	@Column(name="CREATE_ID")
	int create_id;// number no 5 ???id
	@Expose
	@Column(name="CREATE_DATE")
	String create_date;// char(8 byte) no to_char(sysdate, 'yyyymmdd') 6 ????
	@Expose
	@Column(name="CREATE_TIME")
	String create_time;// char(6 byte) no to_char(sysdate, 'hh24miss') 7 ????
	@Expose
	@Column(name="SHOW_TO")
	String show_to;// varchar2(4 byte) yes 8 ??? ??
	public int getNotice_id() {
		return notice_id;
	}
	public void setNotice_id(int notice_id) {
		this.notice_id = notice_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getNoticed() {
		return noticed;
	}
	public void setNoticed(String noticed) {
		this.noticed = noticed;
	}
	public int getCreate_id() {
		return create_id;
	}
	public void setCreate_id(int create_id) {
		this.create_id = create_id;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getShow_to() {
		return show_to;
	}
	public void setShow_to(String show_to) {
		this.show_to = show_to;
	}
	
	
	
	
	
}
