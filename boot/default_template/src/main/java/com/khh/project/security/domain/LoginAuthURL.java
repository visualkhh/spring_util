package com.khh.project.security.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Slf4j
@Entity
@Table(name = "AUTH_URL")
@Data
public class LoginAuthURL {
    @Id
    private String auth ;
    private String url_pattern;
}
