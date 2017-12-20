package com.khh.project.msg.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import com.google.gson.annotations.Expose;
import com.khh.DefaultVO;

@Entity
@Table(name = "TB_MSG_COMPANY")
public @Data class MsgCompanyVO extends DefaultVO {
	private static final long serialVersionUID = 7696471112761096812L;
	@Expose @Id @Column(name="COMPANY_ID")	Integer company_id	;//???ID
//	@JoinColumn(name="WAIT_ID")
	@Expose @Id @Column(name="WAIT_ID")	Integer wait_id	;//??ID
	
}
