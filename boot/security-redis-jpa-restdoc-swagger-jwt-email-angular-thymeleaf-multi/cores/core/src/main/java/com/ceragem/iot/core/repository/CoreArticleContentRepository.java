package com.ceragem.iot.core.repository;

import com.ceragem.iot.core.domain.CoreArticleContent;
import com.ceragem.iot.core.domain.CoreCorpGrp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoreArticleContentRepository extends JpaRepository<CoreArticleContent, Long> {



//    int deleteByCorpGrpSeq(Long corpGrpSeq);
}
