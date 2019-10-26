package com.omnicns.medicine.repository;

import com.omnicns.medicine.code.GameSetCd;
import com.omnicns.medicine.code.UseCd;
import com.omnicns.medicine.domain.GameSet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameSetRepository extends JpaRepository<GameSet, Integer> {
    List<GameSet> findByUseCd(UseCd useCode);
    List<GameSet> findBySetCdAndUseCd(GameSetCd setCd, UseCd useCode);
}
