package com.omnicns.medicine.domain.base;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter @Setter @MappedSuperclass @EqualsAndHashCode(callSuper=false)
public class GameSetDayRstBase extends DomainBase implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer seq;

    @Column(name = "YEARWEEK")
    private Integer yearweek;

    @Column(name = "WEEK")
    private Integer week;

    @Column(name = "WEEKDAY")
    private Integer weekday;

    @Column(name = "USER_SEQ")
    private Integer userSeq;

    @Column(name = "CNT")
    private Long cnt;

    @Column(name = "DATE")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime date;

}
