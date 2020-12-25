package com.ceragem.iot.core.repository;

import com.ceragem.iot.core.domain.CoreCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoreCodeRepository extends JpaRepository<CoreCode, String> {
    List<CoreCode> findByPrntCd(String prntCd);
    List<CoreCode> findByCd(String cd);
//    List<Code> findAllByOrderByCdOrd();


    List<CoreCode> findByPrntCdIsNull();
    List<CoreCode> findByPrntCdIsNotNull();
}
