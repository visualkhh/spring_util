package com.omnicns.medicine.domain;

import com.omnicns.medicine.code.ConformityCd;
import com.omnicns.medicine.domain.base.GameSetWeekRstBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Getter @Setter @EqualsAndHashCode(callSuper=false) @Entity @Table(name="V_GAME_SET_WEEK_RST")
public class GameSetWeekRst extends GameSetWeekRstBase {
    public GameSetWeekRst() {
    }
    public GameSetWeekRst(ZonedDateTime startDt, ZonedDateTime endDt, Integer yearweek, String conformityCd) {
        this.setStartDt(startDt);
        this.setEndDt(endDt);
        this.setYearweek(yearweek);
        this.setConformityCd(ConformityCd.valueOf(conformityCd));
    }

}
