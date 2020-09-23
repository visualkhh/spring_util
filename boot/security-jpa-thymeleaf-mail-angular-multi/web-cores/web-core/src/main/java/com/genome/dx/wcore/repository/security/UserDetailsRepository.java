package com.genome.dx.wcore.repository.security;

import com.genome.dx.wcore.domain.security.UserDetails;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/
//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {

//	@Query(value = "SELECT p FROM Place p LEFT JOIN FETCH p.author LEFT JOIN FETCH p.city c LEFT JOIN FETCH c.state where p.id = :id")
//	Place findById(@Param("id") int id);

//	public UserDetails findAdmByAdmLginId(String admLginId);
	@EntityGraph(value = "UserDetails.authDetails", type = EntityGraph.EntityGraphType.LOAD)
	public UserDetails findByAdmLginId(String admLginId);

	@Modifying
	@Transactional
	@Query(value = "UPDATE UserDetails as a SET a.lginFailCnt = a.lginFailCnt + 1 where a.admLginId = :admLginId")
    Integer pulseLginFailCntByLginId(@Param("admLginId") String admLginId);


//	public UserDetails save();

//	@Modifying @Transactional
//	@Query("UPDATE UserDetails SET lginFailCnt = lginFailCnt+1 WHERE admLginId = :admLginId")
//	public Integer setLginFailCnt(@Param("admLginId") String admLginId);

//	@Modifying @Query(nativeQuery =true)
//	Integer setLginFailCnt(@Param("lginId") String lginId);
//	@Query(value = "SELECT u FROM UserDetails u LEFT JOIN FETCH u.auths where u.admLginId = :admLginId")
//	public UserDetails findByAdmLginId(@Param("admLginId") String admLginId);

	

}
