package com.omnicns.medicine.domain.base;

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

    @Column(name = "CRUD_TYPE")
    private String crudType;

    @Column(name = "AUTH_ID")
    private String authId;

    @Column(name = "URL_SEQ")
    private Integer urlSeq;

    @Column(name = "REG_DT")
    private ZonedDateTime regDt;

    @PrePersist
    protected void onCreate() {
        if (regDt == null) { regDt = ZonedDateTime.now(); }
    }
}
