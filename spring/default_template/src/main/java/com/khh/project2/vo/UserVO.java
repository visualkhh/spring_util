package com.khh.project2.vo;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.khh.DefaultVO;
import com.khh.security.encrypt.PrivacyNameFormater;

@Entity
@Table(name = "T_USER")
public class UserVO extends DefaultVO {
	private static final long serialVersionUID = -162352325348994088L;
	@Id
	@Column(name="USER_ID")
	@Expose
	String user_id;
	@Column(name="USER_NM")
	@Expose
	@Convert(converter=PrivacyNameFormater.class)
	String user_nm;
	@Column(name="USER_PWD")
	@Expose
	String user_pwd;
	@Column(name="ROLE")
	@Expose
	String role;
	
	
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUser_nm() {
		return user_nm;
	}
	public void setUser_nm(String user_nm) {
		this.user_nm = user_nm;
	}
	

}
