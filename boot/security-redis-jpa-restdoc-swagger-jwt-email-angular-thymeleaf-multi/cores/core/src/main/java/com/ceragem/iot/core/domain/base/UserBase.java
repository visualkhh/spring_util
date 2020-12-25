package com.ceragem.iot.core.domain.base;

import com.ceragem.iot.core.model.ModelBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
public class UserBase extends ModelBase implements Serializable {
    @Id
    @Column(name = "no")
    Long no;

    @Column(name = "login_type")
    String login_type;

    @Column(name = "id")
    String id;

    @Column(name = "email")
    String email;

    @Column(name = "name")
    String name;

    @Column(name = "image")
    String image;

    @Column(name = "phone")
    String phone;

    @Column(name = "join_channel")
    String joinChannel;

    @Column(name = "birth")
    ZonedDateTime birth;

    @Column(name = "join_date")
    ZonedDateTime joinDate;

    @Column(name = "last_login_date")
    ZonedDateTime lastLoginDate;

    @Column(name = "is_push")
    Long isPush;

    @Column(name = "machine_add_count")
    Long machineAddCount;

    @Column(name = "machine_use_count")
    Long machineUseCount;

    @Column(name = "is_machine_add")
    Long isMachineAdd;

    @Column(name = "machine_req_reg_date")
    ZonedDateTime machineReqRegDate;

    @Column(name = "total_mileage_point")
    Long totalMileagePoint;

    @Column(name = "total_use_second")
    Long totalUseSecond;


    @PrePersist
    protected void onCreate() {
    }
}
