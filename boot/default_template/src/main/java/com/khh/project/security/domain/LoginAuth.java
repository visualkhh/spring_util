package com.khh.project.security.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Slf4j
@Entity
@Table(name = "AUTH")
@Data
public class LoginAuth {
    @EmbeddedId
    private LoginAuthKey key;
    private Date MODIFY_DATE;
}
