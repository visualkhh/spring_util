package com.company.service.monitor.domain.mindcares.medicare;

import com.company.service.monitor.domain.mindcares.base.MeasureRelationshipBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity(name = "medicare-MeasureRelationship")
@Table(name = "T_MEASURE_RELATIONSHIP")
public class MeasureRelationship extends MeasureRelationshipBase implements Serializable {
}
