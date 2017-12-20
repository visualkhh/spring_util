package com.khh.api.resource.repository.inner;

import com.khh.api.resource.domain.inner.Board2;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

//@RepositoryRestResource
@Repository
public interface Board2Repository extends PagingAndSortingRepository<Board2, Long> {}

