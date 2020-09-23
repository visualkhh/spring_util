package com.genome.dx.core.repository;

import com.genome.dx.core.domain.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface TermRepository extends JpaRepository<Term, String> {


    List<Term> findByIdInOrderByIdAsc(Collection<Long> ids);


}
