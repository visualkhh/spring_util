package com.khh.project.operator.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.khh.DefaultVO;

import lombok.Data;

@Entity
@Table(name = "TB_OPERATOR_IP")
//@SequenceGenerator(
//		name = "OPERATOR_IP_SEQ_GENERATOR",
//		sequenceName = "OPERATOR_IP_SEQ",
//		initialValue = 1, allocationSize = 1
//)

public @Data class OperatorIPVO extends DefaultVO {
	@Expose @Column(name="STATUS")	String status	;//????	CHAR
	@Expose @Column(name="ID")	Integer id	;//????	NUMBER
	@Expose @Id @Column(name="OPERATOR_ID")	Integer operator_id	;//???ID	NUMBER
	@Expose @Column(name="IP_RANGE")	String ip_range	;//?????	VARCHAR2
	@Expose @Column(name="IP_NUMBER")	String ip_number	;//???	VARCHAR2
	
	public OperatorIPVO() {
		init();
	}
	public OperatorIPVO(Integer operator_id) {
		this.operator_id=operator_id;
		init();
	}
	public void init(){
		if(null==status){
			status = "Y";
		}
	}

}
