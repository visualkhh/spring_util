package com.visualkhh.cms.repository.security;

import com.visualkhh.cms.domain.security.UserDetails;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

//https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/
//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {

//	@Query(value = "SELECT p FROM Place p LEFT JOIN FETCH p.author LEFT JOIN FETCH p.city c LEFT JOIN FETCH c.state where p.id = :id")
//	Place findById(@Param("id") int id);

//	public UserDetails findAdmByAdmLginId(String admLginId);
	@EntityGraph(value = "UserDetails.auths", type = EntityGraph.EntityGraphType.LOAD)
	public UserDetails findByAdmLginId(String admLginId);
//	@Query(value = "SELECT u FROM UserDetails u LEFT JOIN FETCH u.auths where u.admLginId = :admLginId")
//	public UserDetails findByAdmLginId(@Param("admLginId") String admLginId);


}
