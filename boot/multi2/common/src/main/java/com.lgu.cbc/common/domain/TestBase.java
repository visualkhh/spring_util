package com.lgu.cbc.common.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper=false)
public class TestBase {


    public TestBase(Integer seq, String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "SEQ")
    private Integer seq;

    @Column(name = "NAME")
    private String name;
}
