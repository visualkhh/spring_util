package com.ceragem.iot.core.repository;

import com.ceragem.iot.core.domain.CoreAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoreAuthRepository extends JpaRepository<CoreAuth, String> {


}
