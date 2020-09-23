package com.genome.dx.core.repository;

import com.genome.dx.core.domain.Term2termOrder;
import com.genome.dx.core.domain.base.Term2termOrderBase;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Deprecated
@Repository
public interface Term2termOrderRepository extends JpaRepository<Term2termOrder, String> {

    @Query(
            "select a from Term2termOrder a order by a.ordkey asc"
    )
    List<Term2termOrder> findShortDataAll(Pageable pageable);
}
