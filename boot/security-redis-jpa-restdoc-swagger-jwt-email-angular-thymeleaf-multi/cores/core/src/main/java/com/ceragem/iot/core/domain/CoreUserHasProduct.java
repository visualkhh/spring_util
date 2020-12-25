package com.ceragem.iot.core.domain;

import com.ceragem.iot.core.domain.base.UrlBase;
import com.ceragem.iot.core.domain.base.UserHasProductBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false) @Entity
@Table(name="user_has_product")
public class CoreUserHasProduct extends UserHasProductBase {
}
