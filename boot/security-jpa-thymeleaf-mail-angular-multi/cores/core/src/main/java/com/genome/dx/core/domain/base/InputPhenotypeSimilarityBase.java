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
@IdClass(InputPhenotypeSimilarityBase.InputPhenotypeSimilarityId.class)
@EqualsAndHashCode(callSuper = false)
public class InputPhenotypeSimilarityBase extends ModelBase implements Serializable {

    @Getter
    @Setter
    @EqualsAndHashCode(callSuper = false)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InputPhenotypeSimilarityId implements Serializable {
        Long inputPid;
        String reference;
        Long comparedEvidence;
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
    @Column(name = "COMPAREDEVIDENCE")
    Long comparedEvidence;

    @JsonView({JsonViewFrontEnd.class})
    @Column(name = "PHENOTYPESCORE")
    Double phenotypeScore;
}
