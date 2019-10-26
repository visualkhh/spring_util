package com.omnicns.medicine.model.api;

import com.omnicns.medicine.code.GameSetCd;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@ApiModel(description = "게임 결과")
public class GameResult extends GameResultBase {

    @NotNull
    @ApiModelProperty(notes = "셋트 코드", required = true)
    private GameSetCd setCd;
}
