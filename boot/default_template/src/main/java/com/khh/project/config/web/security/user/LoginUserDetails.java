package com.khh.project.config.web.security.user;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "USER")
@Data
public class LoginUserDetails implements org.springframework.security.core.userdetails.UserDetails, Serializable {

	@Id
	@Column(name = "USERNAME", unique = true, nullable = false)
	private String username;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Column(name = "ACCOUNTNONEXPIRED")
	private boolean accountNonExpired;

	@Column(name = "ACCOUNTNONLOCKED")
	private boolean accountNonLocked;

	@Column(name = "CREDENTIALSNONEXPIRED")
	private boolean credentialsNonExpired;

	@Column(name = "ENABLED")
	private boolean enabled;


	@OneToMany(fetch = FetchType.EAGER)
//	@OneToMany
	@JoinColumn(name="USERNAME")
	private Collection<LoginUserGrantedAuthority> authorities;

}
