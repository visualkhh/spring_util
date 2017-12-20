package com.khh.api.resource.repository.primary;

import com.khh.api.resource.domain.primary.Board;
import org.springframework.data.repository.PagingAndSortingRepository;

//@RepositoryRestResource
public interface BoardRepository extends PagingAndSortingRepository<Board, Long> {}

