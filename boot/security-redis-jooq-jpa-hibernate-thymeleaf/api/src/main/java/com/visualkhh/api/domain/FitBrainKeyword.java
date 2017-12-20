package com.visualkhh.api.domain;

import com.visualkhh.common.domain.FitBrainKeywordBase;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter @EqualsAndHashCode(callSuper=false) @Entity @Table(name = "T_FIT_BRAIN_KEYWORD") @AllArgsConstructor
public class FitBrainKeyword extends FitBrainKeywordBase {
}