package com.genome.dx.core.domain;

import com.genome.dx.core.domain.base.ActionHistoryBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "T_ACTION_HISTORY")
public class ActionHistory extends ActionHistoryBase {
}
