package com.visualkhh.common.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter @Setter @MappedSuperclass @EqualsAndHashCode(callSuper=false)
public class CorpGrpAuthBase extends DomainBase implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer corpGrpAuthSeq;

    @Column
    private Integer corpGrpSeq;

    @Column
    private String authId;
}
