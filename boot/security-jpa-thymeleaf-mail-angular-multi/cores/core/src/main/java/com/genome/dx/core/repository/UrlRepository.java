package com.genome.dx.core.repository;

import com.genome.dx.core.domain.Code;
import com.genome.dx.core.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlRepository extends JpaRepository<Url, String> {

}
