package com.khh.project.operator.vo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.khh.DefaultVO;
import lombok.Data;

import com.google.gson.annotations.Expose;
import com.omnicns.java.date.DateUtil;

@Entity
@Table(name = "TB_OPERATOR_REQ")
public @Data class OperatorReqRightJoinVO extends DefaultVO {
	private static final long serialVersionUID = 4067935767352330474L;
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
	@Expose @Column(name="LOGIN_PW")	String login_pw	;//?????	VARCHAR2
	@Expose @Id @Column(name="LOGIN_ID")	String login_id	;//???ID	VARCHAR2
	@Expose @Column(name="OPERATOR_ID")	Integer operator_id	;//???ID	NUMBER
	@Expose @Column(name="AREA")	String area	;//?	VARCHAR2
	@Expose @Column(name="LOCKED")	String locked	;//??????	CHAR
	@Expose @Column(name="LOGIN_FAIL") Integer login_fail;									//	NUMBER	No	0 	19	로그인실패수
	@Expose @Column(name="LAST_PWD_UPDATE")	String last_pwd_update;								// VARCHAR2(14 BYTE)	Yes		20	마지막PWD수정일 yyyymmddhh24miss
	@Expose @Column(name="LAST_LOGIN_IP")	String last_login_ip;								//	VARCHAR2(50 BYTE)	Yes		21	마지막로그인IP
	@Expose @Column(name="LAST_LOGIN_SESSION")	String last_login_session;							//	VARCHAR2(100 BYTE)	Yes		22	마지막로그인SESSION_ID
	@Expose @Column(name="LAST_LOGIN_DATE")	String last_login_date;							//	VARCHAR2(14 BYTE)	Yes	 yyyymmddhh24miss
	@Expose @Column(name="INDV_KEY") String indv_key; // INDV_KEY	VARCHAR2(500 BYTE)	Yes		19	개인키
	@Expose
	@Transient
	List<OpRightVO> opRights = new ArrayList<OpRightVO>();
	
	@Expose
	@Transient 
	List<OperatorGrantVO> grants = new ArrayList<OperatorGrantVO>();
	
	@Expose
	@Transient 
	OperatorIPVO ip;
	public OperatorReqRightJoinVO() {
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
		if(null==login_fail){
			login_fail=0;
		}
//		if(null==last_pwd_update){
//			last_pwd_update = DateUtil.getDate("yyyyMMddHHmmss");
//		}
	}
	public OperatorReqRightJoinVO(String login_id) {
		this.login_id=login_id;
		// TODO Auto-generated constructor stub
	}
	
	
	
}
