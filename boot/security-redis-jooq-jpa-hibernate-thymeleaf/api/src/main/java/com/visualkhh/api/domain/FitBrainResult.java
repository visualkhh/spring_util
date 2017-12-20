package com.visualkhh.api.domain;

import com.visualkhh.common.domain.FitBrainResultBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter @Setter @Entity @Table(name = "T_FIT_BRAIN_RESULT")
public class FitBrainResult extends FitBrainResultBase {
}
