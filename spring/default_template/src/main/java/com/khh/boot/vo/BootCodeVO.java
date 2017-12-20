package com.khh.boot.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.khh.DefaultVO;

@Entity
@Table(name = "T_CODE")
public class BootCodeVO extends DefaultVO {
	private static final long serialVersionUID = 1367816522424696003L;
	@Id
	@Column(name="cd")
	String cd;
	@Column(name="cd_nm")
	String cd_nm;
	@Column(name="group_cd")
	String group_cd;
	
	public String getCd() {
		return cd;
	}
	public void setCd(String cd) {
		this.cd = cd;
	}
	
	public String getGroup_cd() {
		return group_cd;
	}
	public void setGroup_cd(String group_cd) {
		this.group_cd = group_cd;
	}
	public String getCd_nm() {
		return cd_nm;
	}
	public void setCd_nm(String cd_nm) {
		this.cd_nm = cd_nm;
	}
	
	
}
