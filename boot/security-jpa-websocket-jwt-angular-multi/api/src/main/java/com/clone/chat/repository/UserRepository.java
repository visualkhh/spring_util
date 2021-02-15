package com.clone.chat.repository;

import com.clone.chat.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    @EntityGraph(value = "User.friends", type = EntityGraph.EntityGraphType.LOAD)
    public Optional<User> findById(String id);
    @EntityGraph(attributePaths = "file", type = EntityGraph.EntityGraphType.LOAD)
    Optional<User> findByIdEquals(String userId);
}
