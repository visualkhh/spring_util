package com.genome.dx.core.domain.base;

import com.fasterxml.jackson.annotation.JsonView;
import com.genome.dx.core.model.ModelBase;
import com.genome.dx.core.model.view.json.JsonViewFrontEnd;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
@IdClass(InputCombinedScoreBase.InputCombinedScoreId.class)
@EqualsAndHashCode(callSuper = false)
public class InputCombinedScoreBase extends ModelBase implements Serializable {

    @Getter
    @Setter
    @EqualsAndHashCode(callSuper = false)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InputCombinedScoreId implements Serializable {
        Long inputPid;
        String reference;
        String typeOfCombinedScore;
        String comparedEvidence;
    }

    @Id
    @JsonView({JsonViewFrontEnd.class})
    @Column(name = "INPUT_PID")
    Long inputPid;

    @Id
    @JsonView({JsonViewFrontEnd.class})
    @Column(name = "REFERENCE")
    String reference;

    @Id
    @JsonView({JsonViewFrontEnd.class})
    @Column(name = "TYPEOFCOMBINEDSCORE")
    String typeOfCombinedScore;

    @Id
    @JsonView({JsonViewFrontEnd.class})
    @Column(name = "COMPAREDEVIDENCE")
    String comparedEvidence;

    @JsonView({JsonViewFrontEnd.class})
    @Column(name = "COMBINEDSCORE")
    Double combinedScore;
}
