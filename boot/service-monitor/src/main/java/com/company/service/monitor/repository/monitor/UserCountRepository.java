package com.company.service.monitor.repository.monitor;

import com.company.service.monitor.domain.monitor.UserCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCountRepository extends JpaRepository<UserCount, Integer> {

}
