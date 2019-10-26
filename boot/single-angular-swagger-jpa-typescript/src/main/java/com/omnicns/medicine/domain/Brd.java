package com.omnicns.medicine.domain;

import com.omnicns.medicine.domain.base.BrdBase;
import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter @EqualsAndHashCode(callSuper=false) @Entity @Table(name = "T_BRD")
@ApiModel
public class Brd extends BrdBase {
    /*@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="ADM_SEQ" , referencedColumnName  = "ADM_SEQ",   insertable = false, updatable = false)
    Adm adm;*/
}

