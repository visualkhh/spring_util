package com.khh.multidatabases.repository.d1;

import com.khh.multidatabases.domain.d1.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface D1UserRepositry extends JpaRepository<User, Long> {
}
