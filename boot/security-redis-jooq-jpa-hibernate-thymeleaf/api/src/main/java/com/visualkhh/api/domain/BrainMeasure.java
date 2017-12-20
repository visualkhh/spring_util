package com.visualkhh.api.domain;

import com.visualkhh.common.domain.BrainMeasureBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "T_BRAIN_MEASURE")
public class BrainMeasure extends BrainMeasureBase {
}
