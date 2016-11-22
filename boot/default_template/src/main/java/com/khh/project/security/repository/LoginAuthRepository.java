package com.khh.project.security.repository;

import com.khh.project.security.domain.LoginAuth;
import com.khh.project.security.domain.LoginAuthKey;
import org.springframework.data.jpa.repository.JpaRepository;

interface LoginAuthRepository extends JpaRepository<LoginAuth, LoginAuthKey> {
}
