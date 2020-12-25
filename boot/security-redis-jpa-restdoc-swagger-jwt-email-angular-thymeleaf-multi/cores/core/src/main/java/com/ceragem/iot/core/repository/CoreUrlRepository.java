package com.ceragem.iot.core.repository;

import com.ceragem.iot.core.domain.CoreUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoreUrlRepository extends JpaRepository<CoreUrl, String> {

}
