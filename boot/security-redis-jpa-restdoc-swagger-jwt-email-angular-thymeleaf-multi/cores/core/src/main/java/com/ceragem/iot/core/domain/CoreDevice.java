package com.ceragem.iot.core.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.ceragem.iot.core.domain.base.DeviceBase;
import com.ceragem.iot.core.model.view.json.JsonViewFrontEnd;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "device")
@NoArgsConstructor
public class CoreDevice extends DeviceBase {

}
