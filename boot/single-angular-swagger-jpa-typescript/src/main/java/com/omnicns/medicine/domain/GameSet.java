package com.omnicns.medicine.domain;

import com.omnicns.medicine.domain.base.GameSetBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter @Setter @EqualsAndHashCode(callSuper=false) @Entity @Table(name = "T_GAME_SET")
// https://www.baeldung.com/hibernate-dynamic-mapping    where and filter
//@Where(clause = "USE_CD = '"+ "-"+UseCode.USE001.name()+"-" +"'")
public class GameSet extends GameSetBase {
}
