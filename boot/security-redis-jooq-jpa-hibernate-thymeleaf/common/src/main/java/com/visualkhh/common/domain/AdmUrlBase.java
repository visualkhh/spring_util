package com.visualkhh.common.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@MappedSuperclass
public class AdmUrlBase extends DomainBase implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer authUrlSeq;

    @Column
    private String crudType;

    @Column
    private String authId;

    @Column
    private Integer url_seq;

    @Column
    private ZonedDateTime regDt;

    @PrePersist
    protected void onCreate() {
        if (regDt == null) { regDt = ZonedDateTime.now(); }
    }
}
