package com.khh.project.security.repository;

import com.khh.project.security.domain.LoginAuthURL;
import org.springframework.data.jpa.repository.JpaRepository;

interface LoginAuthURLRepository extends JpaRepository<LoginAuthURL, String> {
}
