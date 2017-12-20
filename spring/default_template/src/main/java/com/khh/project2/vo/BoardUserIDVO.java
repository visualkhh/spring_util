package com.khh.project2.vo;

import java.util.Date;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.google.gson.annotations.Expose;
import com.khh.DefaultVO;
import com.khh.security.encrypt.PrivacyEncryptor;
import com.khh.security.encrypt.PrivacyNameFormater;

@Entity
public class BoardUserIDVO extends DefaultVO {
	private static final long serialVersionUID = -727287862970747499L;
	@Expose
	String user_id  ;
	@Expose
	String user_pwd ;
	@Expose
	@Convert(converter=PrivacyNameFormater.class)
	String user_nm  ;
	@Expose
	String role 	;
	@Id
	@Expose
	String seq 	    ;
	@Expose
	String content  ;
	
	@Convert(converter=PrivacyEncryptor.class)
	@Expose
	String pwd 	    ;
	@Expose
	Date regdt 	;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public String getUser_nm() {
		return user_nm;
	}
	public void setUser_nm(String user_nm) {
		this.user_nm = user_nm;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Date getRegdt() {
		return regdt;
	}
	public void setRegdt(Date regdt) {
		this.regdt = regdt;
	}
	
	
}
