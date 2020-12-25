package com.ceragem.iot.wcore.model.security;

import com.ceragem.iot.wcore.domain.security.AuthDetail;
import com.fasterxml.jackson.annotation.JsonView;
import com.ceragem.iot.core.model.view.json.JsonViewFrontEnd;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AuthDepthDetail extends AuthDetail {
    @JsonView({JsonViewFrontEnd.class})
    List<AuthDepthDetail> childs;
}
