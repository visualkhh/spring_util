package com.omnicns.medicine.domain.base;

import com.omnicns.medicine.code.ComCd;
import com.omnicns.medicine.code.GameCd;
import com.omnicns.medicine.code.GiftCd;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter @Setter @MappedSuperclass @EqualsAndHashCode(callSuper = false)
@ApiModel
public class GiftBase extends DomainBase implements Serializable {

    @Id
    @Column(name = "GIFT_SEQ")
    @GeneratedValue(strategy= GenerationType.AUTO)
    @ApiModelProperty(hidden = true)
    private Integer giftSeq;

    @Column(name = "GIFT_CD")
    @Enumerated(EnumType.STRING)
    private GiftCd giftCd;

    @Enumerated(EnumType.STRING)
    @Column(name = "GAME_CD")
    private GameCd gameCd;

    @Column(name = "CONT")
    private String cont;


    @Enumerated(EnumType.STRING)
    @Column(name = "COM_CD")
    private ComCd comCd;

    @Column(name = "USER_SEQ")
    @ApiModelProperty(hidden = true)
    private Integer userSeq;

    @Column(name = "REQ_DT")
    private ZonedDateTime reqDt;

    @Column(name = "REG_DT")
    @ApiModelProperty(hidden = true)
    private ZonedDateTime regDt;


    @PrePersist
    protected void onCreate() {
        if (regDt == null) { regDt = ZonedDateTime.now(); }
    }

}
