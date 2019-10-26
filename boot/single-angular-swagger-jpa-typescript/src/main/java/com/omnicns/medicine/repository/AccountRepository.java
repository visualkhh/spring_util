package com.omnicns.medicine.repository;

import com.omnicns.medicine.domain.Adm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Adm, Integer> {
    //    Page<PlayList> findByUseTypeCdOrderByPlaylistOrdAsc(ContentsCateCode useTypeCd, Pageable pageable);
    Page<Adm> findAllByOrderByAdmSeqDesc(Pageable pageable);

}
