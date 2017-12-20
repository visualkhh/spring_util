package com.khh.boot.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.khh.DefaultVO;

@Entity
@Table(name = "TB_RIGHT")
public class BootRightVO extends DefaultVO{
	private static final long serialVersionUID = 5350462120678264589L;
	@Expose @Column(name="URL")	String url	;//	VARCHAR2
	@Expose @Column(name="ICON")	String icon	;//	VARCHAR2
	@Expose @Column(name="ACTION")	String action	;//	VARCHAR2
	@Expose @Column(name="TYPE")	String type	;//	CHAR
	@Expose @Column(name="DESCRIPTION")	String description	;//??	VARCHAR2
	@Expose @Column(name="CODE")	String code	;//????	VARCHAR2
	@Expose @Column(name="GROUP_ID")	Integer group_id	;//????ID	NUMBER
	@Expose @Column(name="NAME")	String name	;//????	VARCHAR2
	@Expose @Id @Column(name="RIGHT_ID")	Integer right_id	;//??ID	NUMBER
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getGroup_id() {
		return group_id;
	}
	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getRight_id() {
		return right_id;
	}
	public void setRight_id(Integer right_id) {
		this.right_id = right_id;
	}
	
	
}
