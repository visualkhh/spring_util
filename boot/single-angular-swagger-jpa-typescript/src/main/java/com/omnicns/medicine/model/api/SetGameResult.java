package com.omnicns.medicine.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@ApiModel(description = "게임 결과")
public class SetGameResult extends GameResultBase {

    @NotEmpty
    @ApiModelProperty(notes = "게임 측정 번호", required = true, example = "test-gameMsmtNo-for-set")
    private String gameMsmtNo;

    @NotEmpty
    @ApiModelProperty(notes = "집중도 그래프", required = true, example = "-1,0,0,-1,0,0,10,0,10,5,3,2,6,2,4,10,2")
    private String centGrph;
}
