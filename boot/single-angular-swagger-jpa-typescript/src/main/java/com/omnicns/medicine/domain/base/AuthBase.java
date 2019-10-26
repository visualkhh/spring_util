package com.omnicns.medicine.domain.base;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Getter @Setter @MappedSuperclass @EqualsAndHashCode(callSuper=false)
public class AuthBase extends DomainBase implements Serializable {

    @Id
    @Column(name="AUTH_ID")
    protected String authId;


    @Column(name="AUTH_NM")
    protected String authNm;

    @Column(name="XPLN")
    protected String xpln;
}
