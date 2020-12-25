package com.ceragem.iot.core.repository;

import com.ceragem.iot.core.domain.CoreUser;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoreUserRepository extends JpaRepository<CoreUser, Long> {

//    @EntityGraph(value = "User.userHasProducts", type = EntityGraph.EntityGraphType.LOAD)
//    CoreUser findByEmail(String email);
//
//    @EntityGraph(value = "User.userHasProducts", type = EntityGraph.EntityGraphType.LOAD)
//    Optional<CoreUser> findByNo(Long no);
//
//    @Query(value = "SELECT PASSWORD(:password)", nativeQuery = true)
//    String generatorPassword(@Param("password") String password);

}
