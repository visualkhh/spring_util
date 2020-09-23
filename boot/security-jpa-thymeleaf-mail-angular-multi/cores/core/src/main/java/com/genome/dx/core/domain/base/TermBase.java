package com.genome.dx.core.domain.base;

import com.fasterxml.jackson.annotation.JsonView;
import com.genome.dx.core.code.BrdCateCd;
import com.genome.dx.core.code.UseCd;
import com.genome.dx.core.model.ModelBase;
import com.genome.dx.core.model.view.json.JsonViewFrontEnd;
import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
public class TermBase extends ModelBase implements Serializable {

    @Id
    @Column(name = "ID")
    Long id;

    @Column(name = "NAME")
    String name;

    @Column(name = "IS_OBSOLETE")
    Long isObsolete;

    @Column(name = "IS_ROOT")
    Long isRoot;

    @Column(name = "SUBONTOLOGY")
    String subontology;

    @Column(name = "COMMENT")
    String comment;

    @Column(name = "ACC")
    String acc;

}
