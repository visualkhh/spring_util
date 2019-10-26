package com.omnicns.medicine.repository.security;

import com.omnicns.medicine.domain.security.Auth;
import com.omnicns.medicine.domain.security.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/
//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
@Repository
public interface AuthRepository extends JpaRepository<Auth, Auth.AuthId> {

//	@Query(value = "SELECT p FROM Place p LEFT JOIN FETCH p.author LEFT JOIN FETCH p.city c LEFT JOIN FETCH c.state where p.id = :id")
//	Place findById(@Param("id") int id);

	public UserDetails findAuthByAdmLginId(String admLginId);
	public UserDetails findByAdmLginId(String admLginId);
}
