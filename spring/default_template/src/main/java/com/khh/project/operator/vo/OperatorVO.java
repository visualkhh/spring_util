package com.khh.project.operator.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import com.khh.DefaultVO;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.google.gson.annotations.Expose;
import com.omnicns.java.date.DateUtil;

import lombok.Data;

@Entity
@Table(name = "TB_OPERATOR")
@SequenceGenerator(
		name = "CBIS_SEQ_GENERATOR",
		sequenceName = "CBIS_SEQ",
		initialValue = 1, allocationSize = 1
)


















public @Data class OperatorVO extends DefaultVO {
	private static final long serialVersionUID = 9142448674994762455L;
	@Expose @Column(name="AREA")	String area	;//??	VARCHAR2
	@Expose @Column(name="LOCKED")	String locked	;//??????	CHAR
	@Expose @Column(name="CARD_NO")	String card_no	;//??????	VARCHAR2
	@Expose @Column(name="OP_LEVEL")	Integer op_level	;//?????	NUMBER
	@Expose @Column(name="CREATE_TIME")	String create_time	;//????	CHAR
	@Expose @Column(name="CREATE_DATE")	String create_date	;//????	CHAR
	@Expose @Column(name="CREATE_ID")	Integer create_id	;//???ID	NUMBER
	@Expose @Column(name="CAN_LOGIN")	String can_login	;//??? ????	CHAR
	@Expose @Column(name="DELETED")	String deleted	;//????	CHAR
	@Expose @Column(name="PHONE2")	String phone2	;//????	VARCHAR2
	@NotEmpty
	@Expose @Column(name="PHONE1")	String phone1	;//????	VARCHAR2
	@Expose @Column(name="PART")	String part	;//??	VARCHAR2
	@Expose @Column(name="POSITION")	String position	;//??	VARCHAR2
	@NotEmpty
	@Email(message="Email 정보를 입력해 주세요.")
	@Expose @Column(name="EMAIL")	String email	;//?????	VARCHAR2
	@NotEmpty
	@Expose @Column(name="NAME")	String name	;//????	VARCHAR2
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{9,30}$|^$",message="비밀번호는 영문, 숫자, 특수문자(!@#$%^&*=)를 포함하는 9자 이상 30자 이하로 구성해야 합니다.")
	@Expose @Column(name="LOGIN_PW")	String login_pw	;//?????	VARCHAR2
	@NotEmpty(message="로그인 ID를 입력해 주세요.")
	@Expose @Column(name="LOGIN_ID")	String login_id	;//???ID	VARCHAR2
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CBIS_SEQ_GENERATOR")
	@Expose @Id @Column(name="OPERATOR_ID")	Integer operator_id	;//???ID	NUMBER
	@Expose @Column(name="LOGIN_FAIL") Integer login_fail;									//	NUMBER	No	0 	19	로그인실패수
	@Expose @Column(name="LAST_PWD_UPDATE")	String last_pwd_update;								// VARCHAR2(14 BYTE)	Yes		20	마지막PWD수정일 yyyymmddhh24miss
	@Expose @Column(name="LAST_LOGIN_IP")	String last_login_ip;								//	VARCHAR2(50 BYTE)	Yes		21	마지막로그인IP
	@Expose @Column(name="LAST_LOGIN_SESSION")	String last_login_session;							//	VARCHAR2(100 BYTE)	Yes		22	마지막로그인SESSION_ID
	@Expose @Column(name="LAST_LOGIN_DATE")	String last_login_date;							//	VARCHAR2(14 BYTE)	Yes	 yyyymmddhh24miss
//	@Expose @Column(name="_IP")	String _ip;
	@Column(name="INDV_KEY")
	String indv_key;
	@Expose @Column(name="PWD_UPDATE") String pwd_update;									// CHAR(1 BYTE)	No	'N' 	25	PWD신청유무
	
	public OperatorVO() {
		init();
	}
	public OperatorVO(Integer operator_id) {
		this.operator_id=operator_id;
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
		if(null==pwd_update){
			pwd_update = "N";
		}
	}

}
