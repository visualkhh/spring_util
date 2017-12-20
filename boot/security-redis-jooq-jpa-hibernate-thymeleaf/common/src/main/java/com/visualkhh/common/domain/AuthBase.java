package com.visualkhh.common.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Getter @Setter @MappedSuperclass @EqualsAndHashCode(callSuper=false)
public class AuthBase extends DomainBase implements Serializable {
    @Id
    @Column
    protected String authId;

    @Column
    protected String authNm;

    @Column
    protected String xpln;
}
