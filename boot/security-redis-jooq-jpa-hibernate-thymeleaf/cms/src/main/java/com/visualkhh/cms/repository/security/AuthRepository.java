package com.visualkhh.cms.repository.security;

import com.visualkhh.cms.domain.security.Auth;
import com.visualkhh.cms.domain.security.AuthId;
import com.visualkhh.cms.domain.security.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

//https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/
//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
public interface AuthRepository extends JpaRepository<Auth, AuthId> {

//	@Query(value = "SELECT p FROM Place p LEFT JOIN FETCH p.author LEFT JOIN FETCH p.city c LEFT JOIN FETCH c.state where p.id = :id")
//	Place findById(@Param("id") int id);

	public UserDetails findAuthByAdmLginId(String admLginId);
	public UserDetails findByAdmLginId(String admLginId);
}
