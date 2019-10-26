package com.omnicns.medicine.model.api;

import com.omnicns.medicine.code.GameCd;
import com.omnicns.medicine.code.PtcpGrpCd;
import com.omnicns.medicine.domain.base.ModelBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
public class GameResultBase extends ModelBase {

    @ApiModelProperty(notes = "셋트 측정 번호", required = true, hidden = true)
    private String setMsmtNo;


    @NotNull
    @ApiModelProperty(notes = "게임 코드", required = true)
    private GameCd gameCd;

    @NotNull
    @ApiModelProperty(notes = "게임 순서", required = true, example = "1")
    private Integer ord;

    @NotNull
    @ApiModelProperty(notes = "참여 그룹 코드", required = true)
    private PtcpGrpCd ptcpGrpCd;


    @NotNull
    @ApiModelProperty(notes = "집중도", required = true, example = "4")
    private Double cent;


    @NotNull
    @ApiModelProperty(notes = "점수", required = true, example = "555")
    private Double scr;

    @NotNull
    @ApiModelProperty(notes = "등급(0~5)", required = true, example = "3")
    private Double grd;

    @NotNull
    @ApiModelProperty(notes = "측정 시작", required = true)
    private ZonedDateTime msmtStDt;

    @NotNull
    @ApiModelProperty(notes = "측정 종료", required = true)
    private ZonedDateTime msmtEndDt;
}
