package com.khh.project.operator.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.khh.DefaultVO;
import com.omnicns.java.date.DateUtil;

@Entity
@Table(name = "TB_OP_RIGHT")
public class OpRightVO extends DefaultVO {
	private static final long serialVersionUID = 1L;
	@Expose @Column(name="CREATE_TIME")	String create_time	;//????	CHAR
	@Expose @Column(name="CREATE_DATE")	String create_date	;//????	CHAR
	@Expose @Column(name="CREATE_ID")	Integer create_id	;//???ID	NUMBER
	@Expose @Id @Column(name="RIGHT_ID")	Integer right_id	;//??ID	NUMBER
	@Expose @Id @Column(name="OPERATOR_ID")	Integer operator_id	;//???ID	NUMBER
	
	public OpRightVO() {
		init();
	}
		
	public void init(){
		if(null==create_date){
			create_date = DateUtil.getDate("yyyyMMdd");
		}
		if(null==create_time){
			create_time = DateUtil.getDate("HHmmss");
		}
	}

	
	
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public Integer getCreate_id() {
		return create_id;
	}
	public void setCreate_id(Integer create_id) {
		this.create_id = create_id;
	}
	public Integer getRight_id() {
		return right_id;
	}
	public void setRight_id(Integer right_id) {
		this.right_id = right_id;
	}
	public Integer getOperator_id() {
		return operator_id;
	}
	public void setOperator_id(Integer operator_id) {
		this.operator_id = operator_id;
	}
	
	
	
}
