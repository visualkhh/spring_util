package com.khh.project.operator.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import com.google.gson.annotations.Expose;
import com.khh.DefaultVO;
import com.omnicns.java.date.DateUtil;

@Entity
@Table(name = "TB_OPERATOR_REQ")
public class OperatorReqVO extends DefaultVO {
	@Expose @Column(name="CARD_NO")	String card_no	;//??????	VARCHAR2
	@Expose @Column(name="OP_LEVEL")	Integer op_level	;//?????	NUMBER
	@Expose @Column(name="CREATE_TIME")	String create_time	;//????	CHAR
	@Expose @Column(name="CREATE_DATE")	String create_date	;//????	CHAR
	@Expose @Column(name="CREATE_ID")	Integer create_id	;//???ID	NUMBER
	@Expose @Column(name="CAN_LOGIN")	String can_login	;//??? ????	CHAR
	@Expose @Column(name="DELETED")	String deleted	;//????	CHAR
	@Expose @Column(name="PHONE2")	String phone2	;//????	VARCHAR2
	@Expose @Column(name="PHONE1")	String phone1	;//????	VARCHAR2
	@Expose @Column(name="PART")	String part	;//??	VARCHAR2
	@Expose @Column(name="POSITION")	String position	;//??	VARCHAR2
	@Expose @Column(name="EMAIL")	String email	;//?????	VARCHAR2
	@Expose @Column(name="NAME")	String name	;//????	VARCHAR2
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{9,30}$", message="비밀번호 형식을 다시 한번 확인해 주세요.")
	@Expose @Column(name="LOGIN_PW")	String login_pw	;//?????	VARCHAR2
	@Expose @Id @Column(name="LOGIN_ID")	String login_id	;//???ID	VARCHAR2
	@Expose @Column(name="OPERATOR_ID")	Integer operator_id	;//???ID	NUMBER
	@Expose @Column(name="AREA")	String area	;//?	VARCHAR2
	@Expose @Column(name="LOCKED")	String locked	;//??????	CHAR
	@Expose @Column(name="INDV_KEY") String indv_key; // INDV_KEY	VARCHAR2(500 BYTE)	Yes		19	개인키

	public OperatorReqVO() {
		
		init();
	}
		
	public void init(){
		if(null==create_date){
			create_date = DateUtil.getDate("yyyyMMdd");
		}
		if(null==create_time){
			create_time = DateUtil.getDate("HHmmss");
		}
		if(null==can_login){
			can_login="Y";
		}
		if(null==deleted){
			deleted="N";
		}
		if(null==locked){
			locked="N";
		}
	}
	public OperatorReqVO(String login_id) {
		this.login_id=login_id;
		// TODO Auto-generated constructor stub
	}
	
	public String getCard_no() {
		return card_no;
	}
	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}
	public Integer getOp_level() {
		return op_level;
	}
	public void setOp_level(Integer op_level) {
		this.op_level = op_level;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public Integer getCreate_id() {
		return create_id;
	}
	public void setCreate_id(Integer create_id) {
		this.create_id = create_id;
	}
	public String getCan_login() {
		return can_login;
	}
	public void setCan_login(String can_login) {
		this.can_login = can_login;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogin_pw() {
		return login_pw;
	}
	public void setLogin_pw(String login_pw) {
		this.login_pw = login_pw;
	}
	public String getLogin_id() {
		return login_id;
	}
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}
	public Integer getOperator_id() {
		return operator_id;
	}
	public void setOperator_id(Integer operator_id) {
		this.operator_id = operator_id;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getLocked() {
		return locked;
	}
	public void setLocked(String locked) {
		this.locked = locked;
	}
	public String getIndv_key() {
		return indv_key;
	}
	public void setIndv_key(String indv_key) {
		this.indv_key = indv_key;
	}
	
}
