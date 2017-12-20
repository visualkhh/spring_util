package com.visualkhh.api.domain;

import com.visualkhh.common.domain.DvcInfoBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter @Setter @Entity @Table(name = "T_DVC_INFO") @EqualsAndHashCode(callSuper = false)
public class DvcInfo extends DvcInfoBase {

}
