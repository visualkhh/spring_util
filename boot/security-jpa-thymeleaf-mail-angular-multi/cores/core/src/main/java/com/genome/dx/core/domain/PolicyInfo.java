package com.genome.dx.core.domain;

import com.genome.dx.core.domain.base.InputInfoBase;
import com.genome.dx.core.domain.base.PolicyInfoBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "T_POLICY_INFO")
public class PolicyInfo extends PolicyInfoBase {

}
