package com.khh.boot.vo;

import java.util.ArrayList;

import com.khh.DefaultVO;

public class BootArea extends DefaultVO{
	private static final long serialVersionUID = 2810935274971149227L;
	
	
	Integer area_id;
	String area;
	Integer order;
	
	BootArea parent;
	ArrayList<BootArea> childs = new ArrayList<BootArea>();
	
	
	
	public BootArea(Integer area_id, String area) {
		this.area_id=area_id;
		this.area=area;
	}
	public BootArea(Integer area_id, String area, Integer order) {
		this.area_id=area_id;
		this.area=area;
		this.order=order;
	}
	public BootArea(Integer area_id, String area, Integer order,BootArea parent) {
		this.area_id=area_id;
		this.area=area;
		this.order=order;
		this.parent=parent;
	}
	public BootArea(Integer area_id, String area, Integer order,BootArea parent,ArrayList<BootArea> childs) {
		this.area_id=area_id;
		this.area=area;
		this.order=order;
		this.parent=parent;
		this.childs=childs;
	}
	public BootArea() {
	}
	public Integer getArea_id() {
		return area_id;
	}
	public void setArea_id(Integer area_id) {
		this.area_id = area_id;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public BootArea getParent() {
		return parent;
	}
	public void setParent(BootArea parent) {
		this.parent = parent;
	}
	public ArrayList<BootArea> getChilds() {
		return childs;
	}
	public void setChilds(ArrayList<BootArea> childs) {
		this.childs = childs;
	}
	public void addChild(BootArea child) {
		this.childs.add(child);
	}
	public void removeChild(BootArea child) {
		this.childs.remove(child);
	}
	
	
	
}
