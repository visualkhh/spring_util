package com.khh.project.operator.vo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.khh.DefaultVO;

import lombok.Data;

@Entity
@Table(name = "TB_CHECK_CARD")
public @Data class CheckCardJoinVO extends DefaultVO {
	
	private static final long serialVersionUID = -8684813873054858585L;
	@Expose @Id @Column(name="CARD_NO")	String card_no	;//????	VARCHAR2
	@Expose @Column(name="CARD_ID")	String card_id	;//??ID	VARCHAR2
	
	@OneToMany
	@JoinColumn(name="CARD_NO")
	List<CheckCodeJoinVO> codes = new ArrayList<CheckCodeJoinVO>();
}
