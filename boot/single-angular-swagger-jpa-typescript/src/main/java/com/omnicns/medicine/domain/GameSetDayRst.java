package com.omnicns.medicine.domain;

import com.omnicns.medicine.domain.base.GameSetDayRstBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter @EqualsAndHashCode(callSuper=false) @Entity @Table(name="V_GAME_SET_DAY_RST")
public class GameSetDayRst extends GameSetDayRstBase {
    public GameSetDayRst() {
    }

//    public GameSetDayRst(Integer cnt) {
//        this.setCnt(cnt);
//    }
    public GameSetDayRst(Integer userSeq) {
        this.setUserSeq(userSeq);

    }
    public GameSetDayRst(Integer userSeq, Long cnt) {
        this.setUserSeq(userSeq);
        this.setCnt(cnt);
    }

}
