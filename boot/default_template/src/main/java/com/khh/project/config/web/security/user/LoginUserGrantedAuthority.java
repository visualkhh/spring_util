package com.khh.project.config.web.security.user;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AUTHORITY")
@Data
public class LoginUserGrantedAuthority implements org.springframework.security.core.GrantedAuthority {
	@Id
	@Column(name = "USERNAME")
	private String username;

	@Id
	@Column(name = "AUTHORITY")
	private String authority = null;

	public LoginUserGrantedAuthority() {
	}

	public LoginUserGrantedAuthority(String authority) {
		this.authority = authority;
	}

}
