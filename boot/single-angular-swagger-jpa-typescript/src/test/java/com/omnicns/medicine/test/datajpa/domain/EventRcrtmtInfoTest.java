package com.omnicns.medicine.test.datajpa.domain;

import com.omnicns.medicine.domain.convert.PrivateConvert;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter @Setter @EqualsAndHashCode(callSuper=false) @Entity @Table(name = "T_EVENT_RCRTMT_INFO")
public class EventRcrtmtInfoTest {
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
    @Column(name = "ADDR")
    private String addr;

    @Column(name = "REG_DT")
    private ZonedDateTime regDt;
    @Column(name = "EVENT_SEQ")
    private Integer eventSeq;
    @PrePersist
    protected void onCreate() {
        if (regDt == null) { regDt = ZonedDateTime.now(); }
    }
    @Column(name = "SEND_INFO")
    private String sendInfo;

//    @ManyToOne
//    @JoinColumns({
//            @JoinColumn(name = "USER_NM", referencedColumnName = "USER_NM"),
//            @JoinColumn(name = "BIRDT", referencedColumnName = "BIRDT"),
//            @JoinColumn(name = "CPON", referencedColumnName = "CPON")
//    })
//    public EventRcrtmtTest eventRcrtmtTest;

}
