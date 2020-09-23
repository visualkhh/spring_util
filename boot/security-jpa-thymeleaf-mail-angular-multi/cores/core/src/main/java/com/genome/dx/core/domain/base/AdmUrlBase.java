package com.genome.dx.core.domain.base;

import com.genome.dx.core.model.ModelBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
public class AdmUrlBase extends ModelBase implements Serializable {
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
