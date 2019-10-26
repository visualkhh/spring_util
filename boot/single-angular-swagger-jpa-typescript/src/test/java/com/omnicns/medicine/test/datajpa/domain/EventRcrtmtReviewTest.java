package com.omnicns.medicine.test.datajpa.domain;

import com.omnicns.medicine.domain.convert.PrivateConvert;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter @Setter @EqualsAndHashCode(callSuper=false) @Entity @Table(name = "T_EVENT_RCRTMT_REVIEW")
public class EventRcrtmtReviewTest {
    @Id
    @Column(name = "EVENT_RCRTMT_SEQ")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer eventRcrtmtSeq;
    @Column(name = "USER_NM")
    @Convert(converter = PrivateConvert.class)
    private String userNm;
    @Column(name = "BIRDT")
    @Convert(converter = PrivateConvert.class)
    private String birdt;
    @Column(name = "CPON")
    @Convert(converter = PrivateConvert.class)
    private String cpon;
    @Convert(converter = PrivateConvert.class)
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "SNS_URL")
    private String snsUrl;
    @Column(name = "SNS_URL2")
    private String snsUrl2;
    @Column(name = "SNS_URL3")
    private String snsUrl3;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "SERIAL_NO")
    private ZonedDateTime serialNo;
    @Column(name = "REG_DT")
    private ZonedDateTime regDt;
    @Column(name = "EVENT_SEQ")
    private Integer eventSeq;
    @PrePersist
    protected void onCreate() {
        if (regDt == null) { regDt = ZonedDateTime.now(); }
    }

}
