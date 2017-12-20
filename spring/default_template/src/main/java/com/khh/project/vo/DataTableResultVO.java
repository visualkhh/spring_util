package com.khh.project.vo;

import com.google.gson.annotations.Expose;
import com.khh.DefaultVO;

//http://stackoverflow.com/questions/29889909/handle-data-table-request-parameters-in-spring-request
public class DataTableResultVO extends DefaultVO {

	private static final long serialVersionUID = -4815250369341559230L;
	
	@Expose
	String draw;
	@Expose
    long recordsTotal;
	@Expose
	long recordsFiltered;
	@Expose
    Object data;
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getDraw() {
		return draw;
	}
	public void setDraw(String draw) {
		this.draw = draw;
	}
	public long getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsTotal;
		
	}
	public long getRecordsFiltered() {
		return recordsFiltered;
	}
//	public void setRecordsFiltered(int recordsFiltered) {
//		this.recordsFiltered = recordsFiltered;
//	}

	
	
}
