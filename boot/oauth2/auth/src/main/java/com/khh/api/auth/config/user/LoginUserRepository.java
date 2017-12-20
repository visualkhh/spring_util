package com.khh.api.auth.config.user;

import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
interface LoginUserRepository extends JpaRepository<LoginUserDetails, String> {
    LoginUserDetails findByUsername(String username);

    LoginUserDetails findByUsernameAndPassword(String username, String password);

}