package com.khh.project.service.admin.repository;

import com.khh.project.service.admin.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

}