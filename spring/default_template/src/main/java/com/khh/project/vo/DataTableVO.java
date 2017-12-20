package com.khh.project.vo;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import com.khh.DefaultVO;
import com.omnicns.java.gson.GsonUtil;
import com.omnicns.java.reflection.ReflectionUtil;

//http://stackoverflow.com/questions/29889909/handle-data-table-request-parameters-in-spring-request
public class DataTableVO extends DefaultVO {

	private static final long serialVersionUID = -4810450369341559230L;
	private String draw;
	//private List<HashMap<String, String>> columns;
	//private List<HashMap<String, String>> order;
	//https://examples.javacodegeeks.com/enterprise-java/hibernate/pagination-in-hibernate-with-criteria-api/
	private int start=0;	//Pagination
	private int length=15;
	HashMap<String, String> search;
	
	
	public String getDraw() {
		return draw;
	}

	public void setDraw(String draw) {
		this.draw = draw;
	}
	public String getSearchValue(){
		String searchStr = null;
		if(search!=null){
			searchStr = (String)search.get("value");
		}
		return null==searchStr?"":searchStr;
	}
	public boolean isSearchJson(){
		String search = getSearchValue();
		if(search.startsWith("{") && search.endsWith("}")){
			return true;
		}else{
			return false;
		}
	}
	public <T> T getSearchJson(Class<T> klass) throws SecurityException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException{
		if(isSearchJson()){
			return GsonUtil.toObject(getSearchValue(), klass);
		}else{
			return (T) ReflectionUtil.newClass(klass);
		}
	}
	
//	public List<HashMap<String, String>> getColumns() {
//		return columns;
//	}
//
//	public void setColumns(List<HashMap<String, String>> columns) {
//		this.columns = columns;
//	}
//
//	public List<HashMap<String, String>> getOrder() {
//		return order;
//	}
//
//	public void setOrder(List<HashMap<String, String>> order) {
//		this.order = order;
//	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public HashMap<String, String> getSearch() {
		return search;
	}

	public void setSearch(HashMap<String, String> search) {
		this.search = search;
	}
	
	public DataTableResultVO makeResult(List data){
		return makeResult(data.size(),data);
	}
	public DataTableResultVO makeResult(long cnt, List data){
		DataTableResultVO rt = new DataTableResultVO();
		rt.setRecordsTotal(cnt);
		rt.setDraw(getDraw());
		rt.setData(data);
		return rt;
	}
	
}
