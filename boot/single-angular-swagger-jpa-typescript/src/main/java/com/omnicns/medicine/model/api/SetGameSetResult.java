package com.omnicns.medicine.model.api;

import com.omnicns.medicine.code.GameSetCd;
import com.omnicns.medicine.domain.base.ModelBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
//import org.hibernate.validator.constraints.NotEmpty;
//import javax.validation.constraints.NotBlank;
import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@ApiModel(description = "게임 세트 결과")
public class SetGameSetResult extends ModelBase {


    @NotNull
    @ApiModelProperty(notes = "셋트 코드", required = true)
    private GameSetCd setCd;

    @NotNull
    @ApiModelProperty(notes = "측정 시작", required = true)
    private ZonedDateTime msmtStDt;

    @NotNull
    @ApiModelProperty(notes = "측정 종료", required = true)
    private ZonedDateTime msmtEndDt;

    @NotNull
    @ApiModelProperty(notes = "셋트 집중도", required = true)
    private Double cent;

    @NotNull
    @ApiModelProperty(notes = "게임 결과", required = true)
    @Valid
    List<SetGameResult> results;
}
