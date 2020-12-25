package com.ceragem.iot.core.domain;

import com.ceragem.iot.core.domain.base.UrlBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false) @Entity
@Table(name="T_URL")
public class CoreUrl extends UrlBase {
}
