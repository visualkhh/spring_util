package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Entity
@Table(name = "datedemo")
@Getter @Setter
public class DateDemo {
    @Id
    @Column(name = "mydatetime")
    ZonedDateTime mydatetime;

    @Column(name = "mytimestamp")
    ZonedDateTime mytimestamp;

    @Column(name = "name")
    String name;

    @Column(name = "type")
    Integer type;
}
