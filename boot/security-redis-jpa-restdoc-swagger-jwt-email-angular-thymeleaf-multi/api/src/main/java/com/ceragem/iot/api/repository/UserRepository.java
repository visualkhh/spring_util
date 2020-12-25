package com.ceragem.iot.api.repository;

import com.ceragem.iot.api.domain.User;
import com.ceragem.iot.core.domain.CoreUser;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(value = "User.userHasProducts", type = EntityGraph.EntityGraphType.LOAD)
    Optional<User> findByEmail(String email);

    @EntityGraph(value = "User.userHasProducts", type = EntityGraph.EntityGraphType.LOAD)
    Optional<User> findByNo(Long no);

    @Query(value = "SELECT PASSWORD(:password)", nativeQuery = true)
    String generatorPassword(@Param("password") String password);

}
