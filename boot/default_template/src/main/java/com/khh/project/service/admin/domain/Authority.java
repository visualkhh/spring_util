package com.khh.project.service.admin.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "AUTHORITY")
@Data
public class Authority implements org.springframework.security.core.GrantedAuthority {
	@Id
	@Column(name = "USERNAME")
	private String username;

	@Id
	@Column(name = "AUTHORITY")
	private String authority = null;



	public Authority() {
	}

	public Authority(String authority) {
		this.authority = authority;
	}

}
