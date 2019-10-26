package com.omnicns.medicine.domain.base;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter @Setter @MappedSuperclass @EqualsAndHashCode(callSuper=false)
public class AdmAuthBase extends DomainBase implements Serializable {
    @Id
    @Column(name = "ADM_AUTH_SEQ")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer admAuthSeq;

    @Column(name = "ADM_SEQ")
    private Integer admSeq;

    @Column(name = "AUTH_ID")
    private String authId;
}
