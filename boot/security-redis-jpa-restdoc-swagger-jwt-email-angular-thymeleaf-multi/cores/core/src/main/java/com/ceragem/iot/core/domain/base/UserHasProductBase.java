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
public class UserHasProductBase extends ModelBase implements Serializable {

    @Id
    @Column(name = "no")
    Long no;

    @Column(name = "user_no")
    Long user_no;

    @Column(name = "machine_serial_no")
    String machine_serial_no;

    @Column(name = "regist_date")
    ZonedDateTime regist_date;

    @Column(name = "name")
    String name;

    @Column(name = "screen_lock_sec")
    Long screen_lock_sec;

    @Column(name = "is_reservation_repeat")
    Long is_reservation_repeat;

    @Column(name = "is_reservation_power_on")
    Long is_reservation_power_on;


    @PrePersist
    protected void onCreate() {
    }
}
