package com.visualkhh.api.repository;

import com.visualkhh.api.domain.Brd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrdRepository extends JpaRepository<Brd, Integer> {
    List<Brd> findByCateCdAndUseYnOrderByGrpNoDescGrpOrdAsc(String catCd, String useYn);
}