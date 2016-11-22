package com.khh.project.security.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
@Data
public class LoginAuthKey implements Serializable {
    private String id;
    private String auth;
}
