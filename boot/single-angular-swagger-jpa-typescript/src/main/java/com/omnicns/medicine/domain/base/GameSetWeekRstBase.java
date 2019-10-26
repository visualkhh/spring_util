package com.omnicns.medicine.domain.base;

import com.omnicns.medicine.code.ConformityCd;
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
public class GameSetWeekRstBase extends DomainBase implements Serializable {
    @Id
    @Column(name = "SEQ")
    private String seq;

    @Column(name = "START_DT")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime startDt;
    @Column(name = "END_DT")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime endDt;

    @Column(name = "YEARWEEK")
    private Integer yearweek;
    @Column(name = "USER_SEQ")
    private Integer userSeq;


    @Column(name = "MONDAY")
    private Long monday;
    @Column(name = "TUESDAY")
    private Long tuesday;
    @Column(name = "WEDNESDAY")
    private Long wednesday;
    @Column(name = "THURSDAY")
    private Long thursday;
    @Column(name = "FRIDAY")
    private Long friday;
    @Column(name = "SATURDAY")
    private Long saturday;
    @Column(name = "SUNDAY")
    private Long sunday;

    @Column(name = "CONFORMITY_CD")
    @Enumerated(EnumType.STRING)
    ConformityCd conformityCd;
}
