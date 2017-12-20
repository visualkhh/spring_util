package com.khh.project.log.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.khh.DefaultVO;
import com.sun.org.glassfish.gmbal.DescriptorFields;
import lombok.Data;

@Entity
@Table(name = "VW_MSG_COMPANY_LOG")
@Data
public class LogCompanyVO extends DefaultVO {
	private static final long serialVersionUID = -4267105878896155910L;	
	
	@Expose @Id @Column(name="WAIT_ID")			Integer wait_id;//송출ID	NUMBER
	@Expose @DescriptorFields("결과") 			@Column(name="RESULT")				String result	;//전송결과	VARCHAR2
	@Expose @Column(name="DESCRIPTION")			String description;//설명	VARCHAR2
	@Expose @DescriptorFields("송출날짜") 		@Column(name="CREATE_DATE")			String create_date	;//송출날짜	CHAR
	@Expose @DescriptorFields("송출시간") 		@Column(name="CREATE_TIME")			String create_time	;//송출시간	CHAR
	@Expose @Id @Column(name="COMPANY_ID")		String company_id;//이통사ID	NUMBER
	@Expose @DescriptorFields("이통사") 		@Column(name="COMPANY_NAME")		String company_name ;//이통사ID	NUMBER
	@Expose @DescriptorFields("시도") 			@Column(name="AREA1")				String area1	;//시도	VARCHAR2
	@Expose @DescriptorFields("시구군") 		@Column(name="AREA2")				String area2	;//시구군	VARCHAR2
	@Expose @DescriptorFields("읍면동") 		@Column(name="AREA3")				String area3	;//읍면동	VARCHAR2
	@Expose @Column(name="TEST_CH")				String test_ch;//테스트유무	CHAR
	@Expose @Id @Column(name="AREA_ID")			Integer area_id;//	CHAR
	@Expose @DescriptorFields("메시지") 		@Column(name="CONTENTS_ALL")		String contents_all;//
	@Expose @Column(name="CONTENTS_SKT")		String contents_skt;
	@Expose @Column(name="CONTENTS_LGT")		String contents_lgt;
	@Expose @Column(name="CONTENTS_SKT_LTE")	String contents_skt_lte;
	@Expose @Column(name="CONTENTS_KT_LTE")		String contents_kt_lte;
	@Expose @Column(name="CONTENTS_LGU_LTE")	String contents_lgu_lte;
	@Expose @Id @Column(name="CODE_ID")			Integer code_id;

	public String getContentsFirst() {
		String msg="";
		if (null != getContents_all()) {
			msg = (getContents_all());
		}else if (null != getContents_kt_lte()) {
			msg = (getContents_kt_lte());
		}else if (null != getContents_lgt()) {
			msg = (getContents_lgt());
		}else if (null != getContents_lgu_lte()) {
			msg = (getContents_lgu_lte());
		}else if (null != getContents_skt()) {
			msg = (getContents_skt());
		}else if (null != getContents_skt_lte()){
			msg = (getContents_skt_lte());
		}
		return msg;
	}
}
