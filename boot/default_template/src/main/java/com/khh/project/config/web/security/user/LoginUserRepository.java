package com.khh.project.config.web.security.user;

import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
interface LoginUserRepository extends JpaRepository<LoginUserDetails, String> {
    LoginUserDetails findByUsername(String username);

    LoginUserDetails findByUsernameAndPassword(String username, String password);

}