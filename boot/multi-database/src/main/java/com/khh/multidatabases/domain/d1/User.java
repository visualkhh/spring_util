package com.khh.multidatabases.domain.d1;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "d1-User")
@Table(name = "T_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ")
    Long seq;

    @Column(name = "NAME")
    String name;

    @Column(name = "AGE")
    Long age;

    public User() {
    }

    @Builder
    public User(String name, Long age) {
        this.name = name;
        this.age = age;
    }
}
