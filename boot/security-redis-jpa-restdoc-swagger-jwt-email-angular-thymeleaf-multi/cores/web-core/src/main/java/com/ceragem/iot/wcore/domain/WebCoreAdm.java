package com.ceragem.iot.wcore.domain;

import com.ceragem.iot.core.domain.base.AdmBase;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@Entity
//@DynamicUpdate
//@DynamicInsert
@Table(name = "T_ADM")
public class WebCoreAdm extends AdmBase {
}
