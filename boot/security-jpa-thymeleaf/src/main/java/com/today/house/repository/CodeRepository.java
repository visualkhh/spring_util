package com.today.house.repository;

import com.today.house.domain.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodeRepository extends JpaRepository<Code, String> {
    List<Code> findByPrntCd(String prntCd);
    List<Code> findByCd(String cd);
//    List<Code> findAllByOrderByCdOrd();


    List<Code> findByPrntCdIsNull();
    List<Code> findByPrntCdIsNotNull();
}
