package com.khh.project.operator.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

import com.google.gson.annotations.Expose;
import com.khh.DefaultVO;
import com.omnicns.java.date.DateUtil;

@Entity
@Table(name = "TB_OP_RIGHT")
public @Data class OpRightJoinVO extends DefaultVO {
	private static final long serialVersionUID = 1L;
	@Expose @Column(name="CREATE_DATE")	String create_date	;//????	CHAR
	@Expose @Column(name="CREATE_ID")	Integer create_id	;//???ID	NUMBER
	@Expose @Column(name="CREATE_TIME")	String create_time	;//????	CHAR
	@Expose @Id @Column(name="OPERATOR_ID")	Integer operator_id	;//???ID	NUMBER
	@Expose @Id @Column(name="RIGHT_ID")	Integer right_id	;//??ID	NUMBER
	
	@Expose
	@OneToOne(optional=true, fetch=FetchType.EAGER)
	@JoinColumn(name="RIGHT_ID", referencedColumnName="RIGHT_ID",  updatable=false, insertable=false)
//	@JoinTable(name="TB_RIGHT", joinColumns=@JoinColumn(name="RIGHT_ID"), inverseJoinColumns=@JoinColumn(name="RIGHT_ID"))
	RightJoinVO rights;
	
	
	
	public OpRightJoinVO() {
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
	public void setCreate_time(String create_time) {
		if(null==create_time){
			create_time = DateUtil.getDate("HHmmss");
		}
		this.create_time = create_time;
	}
	public String getCreate_date() {
		if(null==create_date){
			create_date = DateUtil.getDate("yyyyMMdd");
		}
		return create_date;
	}

	
	
}
