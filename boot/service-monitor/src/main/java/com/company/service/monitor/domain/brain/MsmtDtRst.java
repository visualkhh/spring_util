package com.company.service.monitor.domain.brain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "T_MSMT_DT_RST")
public class MsmtDtRst {

    @Id
    @Column(name = "MSMT_DT_RST_SEQ")
    Double msmtDtRstSeq;

    @Column(name = "CPON_ID")
    String cponId;

    @Column(name = "SERIAL_NO")
    String serialNo;

    @Column(name = "TYPE")
    String type;

    @Column(name = "CENT")
    Double cent;

    @Column(name = "CENT_CD")
    String centCd;

    @Column(name = "STRS")
    Double strs;

    @Column(name = "ATIV_LVL")
    Double ativLvl;

    @Column(name = "BLCR")
    Double blcr;

    @Column(name = "RELAX")
    Double relax;

    @Column(name = "BRN_TOT_SCR")
    String brnTotScr;

    @Column(name = "REG_DT")
    ZonedDateTime regDt;
}
