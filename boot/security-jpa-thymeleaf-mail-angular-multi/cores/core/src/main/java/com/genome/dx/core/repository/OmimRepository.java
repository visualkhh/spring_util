package com.genome.dx.core.repository;

import com.genome.dx.core.domain.Omim;
import com.genome.dx.core.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OmimRepository extends JpaRepository<Omim, Long> {

}
