package com.today.house.model.security;

import com.fasterxml.jackson.annotation.JsonView;
import com.today.house.domain.security.AuthDetail;
import com.today.house.model.view.json.JsonViewFrontEnd;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AuthDepthDetail extends AuthDetail {
    @JsonView({JsonViewFrontEnd.class})
    List<AuthDepthDetail> childs;
}
