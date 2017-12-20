package com.khh.sign.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

import com.google.gson.annotations.Expose;
import com.omnicns.java.date.DateUtil;

@Entity
@Table(name="TB_OPERATOR_LOG")
@SequenceGenerator(
		name = "OP_LOG_SEQ_GENERATOR",
		sequenceName = "OP_LOG_SEQ",
		initialValue = 1, allocationSize = 1
)
public @Data class OperatorLogVO {
	@Expose @Column(name="DATA")	String data	;//??	VARCHAR2
	@Expose @Column(name="ACCESS_IP")	String access_ip	;//??IP	VARCHAR2
	@Expose @Column(name="SCRIPT_NAME")	String script_name	;//?????	VARCHAR2
	@Expose @Column(name="CREATE_TIME")	String create_time	;//????	CHAR
	@Expose @Column(name="CREATE_DATE")	String create_date	;//????	CHAR
	@Expose @Column(name="OPERATOR_ID")	Integer operator_id	;//???ID	NUMBER
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OP_LOG_SEQ_GENERATOR")
	@Expose @Id @Column(name="OP_LOG_ID")	Integer op_log_id	;//??ID	NUMBER
	public OperatorLogVO() {
		init();
	}
	private void init() {
		if(null==create_date){
			create_date = DateUtil.getDate("yyyyMMdd");
		}
		if(null==create_time){
			create_time = DateUtil.getDate("HHmmss");
		}
	}
}
