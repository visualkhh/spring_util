package com.khh.boot.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.khh.DefaultVO;

@Entity
@Table(name = "TB_RIGHT")
public class BootMenuVO extends DefaultVO{
	private static final long serialVersionUID = 6927644255559388415L;
	@Column(name="NAME")
	 String name;                                            //--????                  VARCHAR2
	@Id
	@Column(name="RIGHT_ID")
	 String right_id;                                        //--??ID                  NUMBER
	@Column(name="TYPE")
	 String type;                                            //--                      CHAR
	@Column(name="URL")
	 String url;                                             //--                      VARCHAR2
	@Column(name="DESCRIPTION")
	 String description;                                     //--??                    VARCHAR2
	@Column(name="CODE")
	 String code;                                            //--????                  VARCHAR2
	@Column(name="GROUP_ID")
	 String group_id;                                        //--????ID                NUMBER
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRight_id() {
		return right_id;
	}
	public void setRight_id(String right_id) {
		this.right_id = right_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

	
}
