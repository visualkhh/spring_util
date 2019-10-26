package com.omnicns.medicine.domain.base;

import com.omnicns.medicine.code.UseCd;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter @Setter @MappedSuperclass @EqualsAndHashCode(callSuper=false)
public class AdmBase extends DomainBase implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ADM_SEQ")
    private Integer admSeq;

    @Column(name = "ADM_LGIN_ID")
    private String admLginId;

    @Column(name = "ADM_LGIN_PW")
    private String admLginPw;

    @Column(name = "ADM_NM")
    private String admNm;

    @Column(name = "USE_CD")
    @Enumerated(EnumType.STRING)
    private UseCd useCd;

    @Column(name = "HOME_URL")
    private String homeUrl;

    @Column(name = "LGIN_FAIL_CNT")
    private Integer lginFailCnt;

    @Column(name = "CORP_GRP_SEQ")
    private Integer corpGrpSeq;

    @Column(name = "LGIN_WAIT_DT")
    private ZonedDateTime lginWaitDt;

    @Column(name = "REG_DT")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime regDt;

    @PrePersist
    protected void onCreate() {
        if (regDt == null) { regDt = ZonedDateTime.now(); }
    }
}
