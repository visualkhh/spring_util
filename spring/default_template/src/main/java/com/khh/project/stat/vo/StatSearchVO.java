package com.khh.project.stat.vo;

import com.khh.project.vo.DataTableVO;

/**
 * 
  * @FileName : StatSearchVO.java
  * @Project : project
  * @Date : 2016. 6. 27. 
  * @작성자 : tkyung
  * @변경이력 :
  * @프로그램 설명 : 통계 Search VO
 */

public class StatSearchVO extends DataTableVO {
	private static final long serialVersionUID = 8955057618232342717L;
	
	String area;
	Integer date_query;
	String search_start;
	String search_end;
	String search_telecom;
	String class_id;
	
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Integer getDate_query() {
		return date_query;
	}

	public void setDate_query(Integer date_query) {
		this.date_query = date_query;
	}

	public String getSearch_start() {
		return search_start;
	}

	public void setSearch_start(String search_start) {
		this.search_start = search_start;
	}

	public String getSearch_end() {
		return search_end;
	}

	public void setSearch_end(String search_end) {
		this.search_end = search_end;
	}

	public String getSearch_telecom() {
		return search_telecom;
	}

	public void setSearch_telecom(String search_telecom) {
		this.search_telecom = search_telecom;
	}

	public String getClass_id() {
		return class_id;
	}

	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}
}
