package com.visualkhh.common.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter @Setter @MappedSuperclass @EqualsAndHashCode(callSuper=false)
public class AdmAuthBase extends DomainBase implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer admAuthSeq;

    @Column
    private Integer admSeq;

    @Column
    private String authId;
}
