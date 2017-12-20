package com.visualkhh.api.domain;

import com.visualkhh.common.domain.BrdBase;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter @Setter @EqualsAndHashCode(callSuper=false) @Entity @Table(name = "T_BRD") @AllArgsConstructor
public class Brd extends BrdBase {

}