package com.genome.dx.core.repository;

import com.genome.dx.core.code.ActionCd;
import com.genome.dx.core.domain.ActionHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActionHistoryRepository extends JpaRepository<ActionHistory, Long> {

    Page<ActionHistory> findByAdmSeqAndActCdInOrderByRegDtDesc(Long admSeq, List<ActionCd> actionCds, Pageable pageable);
}
