package com.visualkhh.api.repository;

import com.visualkhh.api.domain.DvcInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DvcInfoRepository extends JpaRepository<DvcInfo, Integer> {
	DvcInfo findFirstBySerialNo(String serialNo);
}