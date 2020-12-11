package com.company.service.monitor.domain.mindcares.kr;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "MANUAL")
public class Manual implements Serializable {
    @Id
    @Column(name = "SEQ")
    Integer seq;

}
