package com.khh.project.operator.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.khh.DefaultVO;
import com.khh.project.area.vo.AreaVO;

import lombok.Data;

@Entity
@Table(name = "TB_OPERATOR_GRANT")
public @Data class OperatorGrantVO extends DefaultVO {
	private static final long serialVersionUID = 5188252116443516015L;
	@Expose @Id @Column(name="OPERATOR_ID")	Integer operator_id	; // 운영자ID	NUMBER
	@Expose @Id @Column(name="AREA_ID")	Integer area_id	; //지역ID	NUMBER
	
	@Expose
//	@OneToOne(fetch=FetchType.EAGER) //즉시로딩
	@OneToOne(optional=true,fetch=FetchType.EAGER)
	@JoinColumn(name="AREA_ID",  insertable=false, updatable=false)
	AreaVO area = null;

}
