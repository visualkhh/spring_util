package com.genome.dx.core.domain.base;

import com.fasterxml.jackson.annotation.JsonView;
import com.genome.dx.core.model.ModelBase;
import com.genome.dx.core.model.view.json.JsonViewFrontEnd;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@IdClass(PtntPntypeBase.PtntPntypeId.class)
@EqualsAndHashCode(callSuper = false)

public class PtntPntypeBase extends ModelBase implements Serializable {


    @Getter
    @Setter
    @EqualsAndHashCode(callSuper = false)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PtntPntypeId implements Serializable {
        Long ptntSeq;
        Long termId;
    }

    @Id
    @Column(name = "PTNT_SEQ")
//    @GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value="ptntInfo"))
//    @GeneratedValue(generator = "generator")
    @JsonView({JsonViewFrontEnd.class})
    Long ptntSeq;

    @Id
    @Column(name = "TERM_ID")
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({JsonViewFrontEnd.class})
    Long termId;

    @Column(name = "REG_DT")
    @JsonView({JsonViewFrontEnd.class})
    ZonedDateTime regDt;

    @PrePersist
    protected void onCreate() {
        if (regDt == null) {
            regDt = ZonedDateTime.now();
        }
    }
}
