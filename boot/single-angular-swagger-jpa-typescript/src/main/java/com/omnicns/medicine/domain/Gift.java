package com.omnicns.medicine.domain;

import com.omnicns.medicine.domain.base.GiftBase;
import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter @Setter @EqualsAndHashCode(callSuper=false) @Entity @Table(name = "T_GIFT")
@ApiModel
public class Gift extends GiftBase {
    @ManyToOne
    @JoinColumn(name = "USER_SEQ", referencedColumnName = "USER_SEQ", insertable = false, updatable = false)
    User user;
}

