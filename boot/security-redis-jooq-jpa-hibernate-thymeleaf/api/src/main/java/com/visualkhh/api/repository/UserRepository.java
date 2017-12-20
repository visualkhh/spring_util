package com.visualkhh.api.repository;

import com.visualkhh.api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findFirstByCponId(String cponId);
}