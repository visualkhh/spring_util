package com.omnicns.medicine.test.datajpa.repository;

import com.omnicns.medicine.test.datajpa.domain.EventRcrtmtReviewTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRcrtmtReviewTestRepository extends JpaRepository<EventRcrtmtReviewTest, Integer> {
}
