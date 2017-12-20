package com.nhis.ggij.api.cms.repository.primary;

import com.nhis.ggij.api.cms.domain.primary.Board;
import org.springframework.data.jpa.repository.JpaRepository;

//@RepositoryRestResource
public interface BoardRepository extends JpaRepository<Board, Long> {}

