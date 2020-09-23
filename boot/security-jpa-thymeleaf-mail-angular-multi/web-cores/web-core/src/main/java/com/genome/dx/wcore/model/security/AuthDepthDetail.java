package com.genome.dx.wcore.model.security;

import com.fasterxml.jackson.annotation.JsonView;
import com.genome.dx.core.model.view.json.JsonViewFrontEnd;
import com.genome.dx.wcore.domain.security.AuthDetail;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AuthDepthDetail extends AuthDetail {
    @JsonView({JsonViewFrontEnd.class})
    List<AuthDepthDetail> childs;
}
