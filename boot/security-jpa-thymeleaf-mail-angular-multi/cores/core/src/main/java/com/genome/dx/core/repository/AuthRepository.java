package com.genome.dx.core.repository;

import com.genome.dx.core.domain.Auth;
import com.genome.dx.core.domain.CorpGrp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Auth, String> {


}
