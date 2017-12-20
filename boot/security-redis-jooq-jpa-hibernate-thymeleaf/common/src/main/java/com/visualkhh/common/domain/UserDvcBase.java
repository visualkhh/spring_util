package com.visualkhh.common.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter @Setter @MappedSuperclass @EqualsAndHashCode(callSuper=false) @AllArgsConstructor @NoArgsConstructor
public class UserDvcBase extends DomainBase implements Serializable {
    @Id
    @Column(name = "USER_DVC_SEQ")
    @GeneratedValue(strategy= GenerationType.AUTO)
    protected int userDvcSeq;

    @Column(name = "AGE_CD")
    private String ageCd;

    @Column(name = "GEN_CD")
    private String genCd;

    @Column(name = "LST_LGIN_IP")
    private String lstLginIp;

    @Column(name = "USER_SEQ")
    private Integer userSeq;

    @Column(name = "DVC_SEQ")
    private Integer dvcSeq;

    @Column(name = "REG_DT")
    private ZonedDateTime regDt;

    @PrePersist
    protected void onCreate() {
        if (regDt == null) { regDt = ZonedDateTime.now(); }
    }
}
