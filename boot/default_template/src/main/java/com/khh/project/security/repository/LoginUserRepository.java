package com.khh.project.security.repository;
import com.khh.project.security.domain.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;


interface LoginUserRepository extends JpaRepository<LoginUser,String>{
}