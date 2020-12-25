package com.ceragem.iot.core.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.ceragem.iot.core.domain.base.AuthBase;
import com.ceragem.iot.core.model.view.json.JsonViewFrontEnd;
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
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "T_AUTH")
public class CoreAuth extends AuthBase {

	@OneToMany
	@JoinColumn(name="AUTH_ID" , referencedColumnName  = "AUTH_ID",   insertable = false, updatable = false)
	@JsonView({JsonViewFrontEnd.class})
	private List<CoreAuthUrl> authUrls;
}
