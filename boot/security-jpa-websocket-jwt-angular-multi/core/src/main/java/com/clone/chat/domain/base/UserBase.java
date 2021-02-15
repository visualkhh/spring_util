package com.clone.chat.domain.base;

import com.clone.chat.code.UserRole;
import com.clone.chat.model.ModelBase;
import com.clone.chat.model.view.json.JsonViewApi;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter @Setter
@MappedSuperclass
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class UserBase extends ModelBase {

	@Id
	@Column(name = "ID")
	@JsonView({JsonViewApi.class})
	String id;

	@Column(name = "PASSWORD")
	String password;

	@Column(name = "NICK_NAME")
	@JsonView({JsonViewApi.class})
	String nickName;

	@Column(name = "PHONE")
	@JsonView({JsonViewApi.class})
	String phone;

	@Enumerated(EnumType.STRING)
	@JsonView({JsonViewApi.class})
	@Column(name = "ROLE")
	UserRole role;

	@Column(name = "STATUS_MSG")
	@JsonView({JsonViewApi.class})
	String statusMsg;

	@Builder
	public UserBase(String id, String password, String nickName, String phone, UserRole role, String statusMsg) {
		this.id = id;
		this.password = password;
		this.nickName = nickName;
		this.phone = phone;
		this.role = role;
		this.statusMsg = statusMsg;
	}

//	@CreatedDate
	@Column(name = "REG_DT")
	private ZonedDateTime regDt;

//	@LastModifiedDate
	@Column(name = "UPD_DT")
	private ZonedDateTime updDt;

	@PrePersist
	public void onPrePersist() {
		if(null == this.regDt) {
			this.regDt = ZonedDateTime.now();
		}
	}

	@PreUpdate
	public void onPreUpdate() {
		this.updDt = ZonedDateTime.now();
	}

}
