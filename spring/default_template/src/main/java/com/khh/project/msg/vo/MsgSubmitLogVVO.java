package com.khh.project.msg.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.khh.DefaultVO;

@Entity
@Table(name = "VW_MSG_SUBMIT_LOG")
public class MsgSubmitLogVVO extends DefaultVO {

	private static final long serialVersionUID = -5285777372610151495L;
	@Expose @Column(name="AREA1")	String area1	;//시도	VARCHAR2
	@Expose @Column(name="AREA2")	String area2	;//시구군	VARCHAR2
	@Expose @Column(name="AREA3")	String area3	;//읍면동	VARCHAR2
	@Expose @Column(name="CBPP_ID")	Integer cbpp_id	;//이통사내부코드	NUMBER
	@Expose @Column(name="CODE_ID")	Integer code_id	;//코드ID	NUMBER
	@Expose @Column(name="COMPANY_ID")	Integer company_id	;//이통사ID	NUMBER
	@Expose @Column(name="COMPANY_NAME")	String company_name;//이통사ID	NUMBER
	@Expose @Column(name="CREATE_DATE")	String create_date	;//송출날짜	CHAR
	@Expose @Column(name="CREATE_TIME")	String create_time	;//송출시간	CHAR
	@Expose @Column(name="DESCRIPTION")	String description	;//설명	VARCHAR2
	@Expose @Column(name="RESULT")	String result	;//전송결과	VARCHAR2
	@Expose @Column(name="TEST_CH")	String test_ch	;//테스트유무	CHAR
	@Expose @Id @Column(name="WAIT_ID")	Integer wait_id	;//송출ID	NUMBER
	
	public String getArea1() {
		return area1;
	}
	public void setArea1(String area1) {
		this.area1 = area1;
	}
	public String getArea2() {
		return area2;
	}
	public void setArea2(String area2) {
		this.area2 = area2;
	}
	public String getArea3() {
		return area3;
	}
	public void setArea3(String area3) {
		this.area3 = area3;
	}
	public Integer getCbpp_id() {
		return cbpp_id;
	}
	public void setCbpp_id(Integer cbpp_id) {
		this.cbpp_id = cbpp_id;
	}
	public Integer getCode_id() {
		return code_id;
	}
	public void setCode_id(Integer code_id) {
		this.code_id = code_id;
	}
	public Integer getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getTest_ch() {
		return test_ch;
	}
	public void setTest_ch(String test_ch) {
		this.test_ch = test_ch;
	}
	public Integer getWait_id() {
		return wait_id;
	}
	public void setWait_id(Integer wait_id) {
		this.wait_id = wait_id;
	}
	 
	
}
