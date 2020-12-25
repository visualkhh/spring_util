package com.ceragem.iot.core.domain.base;

import com.fasterxml.jackson.annotation.JsonView;
import com.ceragem.iot.core.code.ActionCd;
import com.ceragem.iot.core.model.ModelBase;
import com.ceragem.iot.core.model.view.json.JsonViewFrontEnd;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
public class DeviceBase extends ModelBase implements Serializable {

    @Builder
    public DeviceBase(String id, String name, Long userNo, String osType, String pushKey, String accessToken, ZonedDateTime lastLogin, String language) {
        this.id = id;
        this.name = name;
        this.userNo = userNo;
        this.osType = osType;
        this.pushKey = pushKey;
        this.accessToken = accessToken;
        this.lastLogin = lastLogin;
        this.language = language;
    }

    @Id
    @Column(name = "id")
    String id;

    @Column(name = "name")
    String name;

    @Column(name = "user_no")
    Long userNo;

    @Column(name = "os_type")
    String osType;

    @Column(name = "push_key")
    String pushKey;

    @Column(name = "access_token")
    String accessToken;

    @Column(name = "last_login")
    ZonedDateTime lastLogin;

    @Column(name = "language")
    String language;


    @PrePersist
    protected void onCreate() {
//        if (regDt == null) {
//            regDt = ZonedDateTime.now();
//        }
    }

}
