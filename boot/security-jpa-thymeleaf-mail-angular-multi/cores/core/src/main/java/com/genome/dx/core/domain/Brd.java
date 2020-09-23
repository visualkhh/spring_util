package com.genome.dx.core.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.genome.dx.core.domain.base.BrdBase;
import com.genome.dx.core.model.view.json.JsonViewFrontEnd;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
//@DynamicUpdate
//@DynamicInsert
@Table(name = "T_BRD")
//@NamedEntityGraph(name = "Brd.adms", attributeNodes = @NamedAttributeNode("adms"))
@NamedEntityGraph(name = "Brd.brds", attributeNodes = @NamedAttributeNode("brds"))
public class Brd extends BrdBase {

    @ManyToOne
    @JoinColumn(name = "ADM_SEQ", referencedColumnName = "ADM_SEQ", insertable = false, updatable = false)
    @JsonView({JsonViewFrontEnd.class})
    Adm adm;

    @OneToMany
    @JoinColumn(name = "PRNT_BRD_SEQ", referencedColumnName = "BRD_SEQ")
    @JsonView({JsonViewFrontEnd.class})
    List<Brd> brds;
}
