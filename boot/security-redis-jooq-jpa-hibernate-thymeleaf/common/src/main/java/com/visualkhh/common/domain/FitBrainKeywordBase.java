package com.visualkhh.common.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter @Setter @MappedSuperclass @EqualsAndHashCode(callSuper=false)
public class FitBrainKeywordBase extends DomainBase implements Serializable {
    @Id
    @Column(name = "FIT_BRN_KEYWORD_SEQ")
    private Integer fitBrnKeywordSeq;

    /*@Column(name = "FIT_BRAIN_SEQ")
    private Integer fitBrainSeq;*/

    @Column(name = "FIT_BRAIN_KEYWORD_CD")
    private String fitBrainKeywordCd;

    @Transient
    private String fitBrainKeywordCdNm;
}
