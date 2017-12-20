package com.khh.project.operator.vo;

import javax.persistence.Column; 
import com.google.gson.annotations.Expose; 
 
public class OperatorSVO { 
	
	@Expose @Column(name="AUTH_SEARCH_MODE")	String auth_search_mode;// 시작일
	@Expose @Column(name="SEARCH_MODE")	String search_mode;// 종료일
	@Expose @Column(name="SEARCH_KEY")	String search_key	;//정보분류ID	NUMBER   
	public OperatorSVO() {
	}
	public String getAuth_search_mode() {
		return auth_search_mode;
	}
	public void setAuth_search_mode(String auth_search_mode) {
		this.auth_search_mode = auth_search_mode;
	}
	public String getSearch_mode() {
		return search_mode;
	}
	public void setSearch_mode(String search_mode) {
		this.search_mode = search_mode;
	}
	public String getSearch_key() {
		
		return null==search_key?"":search_key;
	}
	public void setSearch_key(String search_key) {
		this.search_key = search_key;
	}
	
	
	
}
