package com.khh.project.msg.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.khh.DefaultVO;

import lombok.Data;

@Entity
@Table(name = "TB_MSG_AREA")
public @Data class MsgAreaVO extends DefaultVO {
	private static final long serialVersionUID = -3252609790179614864L;
	@Expose @Id @Column(name="AREA_ID")	Integer area_id	;//??ID	NUMBER
//	@JoinColumn(name="WAIT_ID")
	@Expose @Id @Column(name="WAIT_ID")	Integer wait_id	;//??ID	NUMBER
	
	public MsgAreaVO(){
	}
	public MsgAreaVO(Integer area_id){
		this.area_id=area_id;
	}
	public MsgAreaVO(Integer area_id,Integer wait_id){
		this.area_id=area_id;
		this.wait_id=wait_id;
	}
}
