package com.ceragem.iot.core.domain.base;

import com.ceragem.iot.core.convert.PrivateConvert;
import com.ceragem.iot.core.model.ModelBase;
import com.fasterxml.jackson.annotation.JsonView;
import com.ceragem.iot.core.code.JobCd;
import com.ceragem.iot.core.code.UseCd;
import com.ceragem.iot.core.model.view.json.JsonViewFrontEnd;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
public class AdmBase extends ModelBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADM_SEQ")
    @JsonView({JsonViewFrontEnd.class})
    private Long admSeq;

    @Column(name = "ADM_LGIN_ID")
    @JsonView({JsonViewFrontEnd.class})
    private String admLginId;

    //    @JsonIgnore
    @Column(name = "ADM_LGIN_PW")
    private String admLginPw;

    @Column(name = "ADM_NM")
    @JsonView({JsonViewFrontEnd.class})
    private String admNm;

    @Column(name = "USE_CD")
    @JsonView({JsonViewFrontEnd.class})
    @Enumerated(EnumType.STRING)
    private UseCd useCd;

    @Column(name = "HOME_URL")
    @JsonView({JsonViewFrontEnd.class})
    private String homeUrl;

    @Convert(converter = PrivateConvert.class)
    @Column(name = "EMAIL")
    @JsonView({JsonViewFrontEnd.class})
    private String email;

    @Convert(converter = PrivateConvert.class)
    @Column(name = "PHONE")
    @JsonView({JsonViewFrontEnd.class})
    private String phone;

    @Column(name = "COMPANY_NM")
    @JsonView({JsonViewFrontEnd.class})
    private String companyNm;

    @Column(name = "JOB_CD")
    @JsonView({JsonViewFrontEnd.class})
    @Enumerated(EnumType.STRING)
    private JobCd jobCd;

    @Column(name = "LGIN_FAIL_CNT")
    @JsonView({JsonViewFrontEnd.class})
    private Long lginFailCnt;

    @Column(name = "CORP_GRP_SEQ")
    @JsonView({JsonViewFrontEnd.class})
    private Long corpGrpSeq;

    @Column(name = "LGIN_WAIT_DT")
    @JsonView({JsonViewFrontEnd.class})
    private ZonedDateTime lginWaitDt;

    @Column(name = "START_DT")
    @JsonView({JsonViewFrontEnd.class})
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime startDt;

    @Column(name = "END_DT")
    @JsonView({JsonViewFrontEnd.class})
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime endDt;

    @Column(name = "UPD_DT")
    @JsonView({JsonViewFrontEnd.class})
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime updDt;

    @Column(name = "REG_DT")
    @JsonView({JsonViewFrontEnd.class})
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime regDt;

    @PrePersist
    protected void onCreate() {
        if (regDt == null) {
            regDt = ZonedDateTime.now();
        }
    }

}
