package com.genome.dx.core.repository;

import com.genome.dx.core.code.BrdCateCd;
import com.genome.dx.core.code.UseCd;
import com.genome.dx.core.domain.Brd;
import com.genome.dx.core.domain.PolicyInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyInfoRepository extends JpaRepository<PolicyInfo, Long> {

}
