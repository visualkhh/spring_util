package com.ceragem.iot.core.repository;

import com.ceragem.iot.core.domain.CoreDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoreDeviceRepository extends JpaRepository<CoreDevice, Long> {
}
