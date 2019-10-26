package com.omnicns.medicine.test.datajpa.repository;

import com.omnicns.medicine.test.datajpa.domain.EventRcrtmtInfoTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRcrtmtInfoTestRepository extends JpaRepository<EventRcrtmtInfoTest, Integer> {
}
