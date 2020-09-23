package com.genome.dx.core.domain;

import com.genome.dx.core.domain.base.CodeBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "T_CODE")
public class CoreCode extends CodeBase {
}
