package com.khh.project.group.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.khh.DefaultVO;
import lombok.Data;

import com.google.gson.annotations.Expose;
import com.sun.org.glassfish.gmbal.DescriptorFields;
@Entity
@Table(name="VW_AREA_GROUP_LIST")
public @Data class AreaGroupListVVO extends DefaultVO {
	private static final long serialVersionUID = -214856122342429317L;
	
	@Expose  @DescriptorFields("그룹ID") @Id @Column(name="GROUP_ID") Integer group_id	;//그룹ID	
	@Expose  @DescriptorFields("그룹명") @Column(name="NAME")	String name	;//그룹명	VARCHAR2
	@Expose  @DescriptorFields("지역리스트") @Column(name="AREA")	String area;//지역 리스트
	@Expose  @DescriptorFields("지역 아이디 리스트") @Column(name="AREA_ID")	String area_id;//지역 아이디 리스트	
	@Expose  @Column(name="STATUS")	String status;//지역 아이디 리스트	
}
