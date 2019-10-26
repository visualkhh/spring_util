package com.omnicns.medicine.domain.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.omnicns.medicine.code.GameCd;
import com.omnicns.medicine.code.GameSetCd;
import com.omnicns.medicine.code.UseCd;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Data
@Setter
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GameSetBase extends DomainBase implements Serializable {
    @Id
    @Column(name = "SET_SEQ")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer setSeq;

    @Column(name = "SET_CD")
    @Enumerated(EnumType.STRING)
    private GameSetCd setCd;

    @Column(name = "GAME_CD")
    @Enumerated(EnumType.STRING)
    private GameCd gameCd;

    @Column(name = "ORD")
    private Integer ord;

    @Column(name = "USE_CD")
    @Enumerated(EnumType.STRING)
    private UseCd useCd;
}
