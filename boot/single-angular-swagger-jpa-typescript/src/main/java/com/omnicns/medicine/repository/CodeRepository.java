package com.omnicns.medicine.repository;

import com.omnicns.medicine.domain.Code;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodeRepository extends JpaRepository<Code, String> {
    List<Code> findByPrntCd(String prntCd);
    List<Code> findByCd(String cd);
//    List<Code> findAllByOrderByCdOrd();


    List<Code> findByPrntCdIsNull();
    List<Code> findByPrntCdIsNotNull();
}
