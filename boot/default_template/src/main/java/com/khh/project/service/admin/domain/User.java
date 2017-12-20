package com.khh.project.service.admin.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "USER")
@Data
public class User implements org.springframework.security.core.userdetails.UserDetails, Serializable {

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


//	@OneToMany(fetch = FetchType.EAGER)

	//기본적인 하이버네이트로 EAGER 조인을 걸게되면 검색된 자식테이블만큼 부모것도 돌려줘버린다 이래서 아래 SELECT를 쓰면 각각 다시 조인쿼리날린다
	//@Fetch(FetchMode.SELECT)  //http://stackoverflow.com/questions/19431021/hibernate-and-criteria-that-return-the-same-items-multiple-times
	//또는 crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);  처럼 옵션을주면된다
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="USERNAME")
	private Collection<Authority> authorities;

}
