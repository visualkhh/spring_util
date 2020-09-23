package com.genome.dx.wcore.repository.security;

import com.genome.dx.wcore.domain.security.AuthDetail;
import com.genome.dx.wcore.domain.security.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/
//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
@Repository
public interface AuthDetailRepository extends JpaRepository<AuthDetail, AuthDetail.AuthId> {

//	@Query(value = "SELECT p FROM Place p LEFT JOIN FETCH p.author LEFT JOIN FETCH p.city c LEFT JOIN FETCH c.state where p.id = :id")
//	Place findById(@Param("id") int id);

	public List<AuthDetail> findByAdmLginId(String admLginId);
}
