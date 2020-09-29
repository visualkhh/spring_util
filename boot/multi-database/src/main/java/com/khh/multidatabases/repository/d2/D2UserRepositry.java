package com.khh.multidatabases.repository.d2;

import com.khh.multidatabases.domain.d2.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface D2UserRepositry extends JpaRepository<User, Long> {
}
