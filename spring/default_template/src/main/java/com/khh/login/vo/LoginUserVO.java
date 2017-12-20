package com.khh.login.vo;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.khh.DefaultVO;
import com.khh.security.spring.CustomGrantedObjAuthority;
import lombok.Data;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.google.gson.annotations.Expose;
import com.omnicns.java.date.DateUtil;

@Entity
@Table(name = "TB_OPERATOR")
public @Data class LoginUserVO extends DefaultVO implements UserDetails{
	private static final long serialVersionUID = -162352325348994087L;
	
	@Expose
	@Column(name="AREA")
	String area;                                             //  --??                    VARCHAR2
	@Column(name="LOCKED")
	String locked;                                           //  --??????                CHAR
	@Column(name="CARD_NO")
	String card_no;                                          //  --??????                VARCHAR2
	@Expose
	@Column(name="OP_LEVEL")
	Integer op_level;                                         //  --?????                 NUMBER
	@Expose
	@Column(name="CREATE_TIME")
	String create_time;                                      //  --????                  CHAR
	@Expose
	@Column(name="CREATE_DATE")
	String create_date;                                      //  --????                  CHAR
	@Column(name="CREATE_ID")
	Integer create_id;                                        //  --???ID                 NUMBER
	@Column(name="CAN_LOGIN")
	String can_login;                                        //  --??? ????              CHAR
	@Column(name="DELETED")
	String deleted;                                          //  --????                  CHAR
	@Column(name="PHONE2")
	String phone2;                                           //  --????                  VARCHAR2
	@Column(name="PHONE1")
	String phone1;                                           //  --????                  VARCHAR2
	@Expose
	@Column(name="PART")
	String part;                                             //  --??                    VARCHAR2
	@Expose
	@Column(name="POSITION")
	String position;                                         //  --??                    VARCHAR2
	@Expose
	@Column(name="EMAIL")
	String email;                                            //  --?????                 VARCHAR2
	@Column(name="NAME")
	@Expose
	String name;                                             //  --????                  VARCHAR2
	@Column(name="LOGIN_PW")
	String login_pw;                                         //  --?????                 VARCHAR2
	@Column(name="LOGIN_ID")
	String login_id;                                         //  --???ID                 VARCHAR2
	@Expose
	@Id
	@Column(name="OPERATOR_ID")
	Integer operator_id;                                      //  --???ID                 NUMBER
	
	
	@Column(name="LOGIN_FAIL")
	Integer login_fail;									//	NUMBER	No	0 	19	로그인실패수
	@Column(name="LAST_PWD_UPDATE")
	String last_pwd_update;								// VARCHAR2(14 BYTE)	Yes		20	마지막PWD수정일 yyyymmddhh24miss
	@Column(name="LAST_LOGIN_IP")
	String last_login_ip;								//	VARCHAR2(50 BYTE)	Yes		21	마지막로그인IP
	@Column(name="LAST_LOGIN_SESSION")
	String last_login_session;							//	VARCHAR2(100 BYTE)	Yes		22	마지막로그인SESSION_ID
	@Column(name="LAST_LOGIN_DATE")
	String last_login_date;							//	VARCHAR2(14 BYTE)	Yes	 yyyymmddhh24miss
	@Column(name="INDV_KEY")
	String indv_key;							//	VARCHAR2(14 BYTE)	Yes	 yyyymmddhh24miss
	@Column(name="PWD_UPDATE")							// CHAR(1 BYTE)	No	'N' 	25	PWD신청유무
	String pwd_update;
	

	@Expose
	@OneToOne
	@JoinColumn(name="CARD_NO", referencedColumnName="CARD_NO", insertable=false, updatable=false)
	LoginCheckCardJoinVO checkCard;
	
	@Transient
	boolean enabled;			//마지막 카드까지 처리된사람은 true 처리되야한다.
	@Transient
	boolean pwdNonExpired;			

//	@Expose
	@Transient
	List<CustomGrantedObjAuthority> authorities;
	
	
	@Expose
	@OneToOne(optional=true)
	@JoinColumn(name="OPERATOR_ID", referencedColumnName="OPERATOR_ID", updatable=false, insertable=false)
	LoginOperatorIPVO ip;
	
	public LoginUserVO(){
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
		if(null==last_pwd_update){
			last_pwd_update = DateUtil.getDate("yyyyMMddHHmmss");
		}
		if(null==pwd_update){
			pwd_update = "N";
		}
	}
	
	public Integer getLogin_fail(){
		return (null==this.login_fail?new Integer(0):login_fail);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return getLogin_pw();
	}

	@Override
	public String getUsername() {
		return getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;//에는 만료기간이 없다.
	}
	public boolean isPwdNonExpired() {
		return pwdNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		boolean sw=false;
		if(null!=getLocked() && "N".equals(getLocked())){
			sw=true;
		}
		return sw;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	public void setEnabled(boolean enabled){
		this.enabled = enabled;
	}
	@Override
	public boolean isEnabled() {
		return this.enabled;
	}
	


}
