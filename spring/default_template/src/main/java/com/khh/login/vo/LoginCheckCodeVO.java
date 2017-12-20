package com.khh.login.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.khh.DefaultVO;
import lombok.Data;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "TB_CHECK_CODE")
public @Data class LoginCheckCodeVO extends DefaultVO {
	private static final long serialVersionUID = -9220842008341369522L;
	@Expose @Id @Column(name="CODE")	Integer code	;//????	NUMBER
	@Expose @Id @Column(name="CODE_ID")	Integer code_id	;//??ID	NUMBER
	@Expose @Id @Column(name="CARD_ID")	Integer card_id	;//??ID	NUMBER	
}
