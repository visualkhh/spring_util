package com.khh.project2.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.gson.annotations.Expose;
import com.khh.DefaultVO;
import com.khh.security.encrypt.PrivacyEncryptor;

@Entity
@Table(name="T_BOARD")
public class BoardVO extends DefaultVO {
	private static final long serialVersionUID = 8764615873423245828L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="SEQ")
	@Expose
	Integer seq;
	
	@Column(name="USER_ID")
	String user_id;
	
	@Column(name="CONTENT")
	@Expose
	String content;
	
	@Convert(converter=PrivacyEncryptor.class)
	@NotEmpty
	@Column(name="PWD")
	@Expose
	String pwd;
	
	
	
	
//	@Column(name="REGDT", columnDefinition="date default sysdate")
//	@Generated(value = GenerationTime.INSERT)
//    @Temporal(javax.persistence.TemporalType.DATE)
	@Column(name="REGDT", nullable = false)
	//@Generated(value=GenerationTime.ALWAYS)
	@Temporal(TemporalType.TIMESTAMP) //DB에서 가져올때? 포맷
	@Version
	@Expose
	Date regdt;
//	@OneToOne
//	@ManyToOne(fetch = FetchType.LAZY)
	@ManyToOne
	@JoinColumn(name="USER_ID", nullable=false,updatable=false,insertable=false)
	@Expose
	private UserVO user;
	
	
	
//	@PrePersist
//	protected void onCreate() {
//		regdt = new Date();
//	}
//	@PreUpdate
//	protected void onUpdate() {
//		regdt = new Date();
//	}
	
	
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Date getRegdt() {
		return regdt;
	}
	public void setRegdt(Date regdt) {
		this.regdt = regdt;
	}
	public UserVO getUser() {
		return user;
	}
	public void setUser(UserVO user) {
		this.user = user;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	
	
	
}
