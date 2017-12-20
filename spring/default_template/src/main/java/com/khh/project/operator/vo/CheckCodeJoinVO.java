package com.khh.project.operator.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.khh.DefaultVO;

@Entity
@Table(name = "TB_CHECK_CODE")
public class CheckCodeJoinVO extends DefaultVO {
	
	private static final long serialVersionUID = 5471399098120249753L;
	@Expose @Id @Column(name="CODE")	Integer code	;//????	NUMBER
	@Expose @Id @Column(name="CODE_ID")	Integer code_id	;//??ID	NUMBER
	@Expose @Id @Column(name="CARD_ID")	Integer card_id	;//??ID	NUMBER	
}
