package com.omnicns.medicine.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.omnicns.medicine.domain.base.GameSetRstBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter @Setter @EqualsAndHashCode(callSuper=false) @Entity @Table(name="T_GAME_SET_RST")
public class GameSetRst extends GameSetRstBase implements Serializable {

    @OneToMany
    @JoinColumn(name = "SET_MSMT_NO", referencedColumnName = "SET_MSMT_NO")
    @OrderBy("ord ASC")
    @JsonView({Views.Details.class})
    List<GameRst> gameRsts;
}
