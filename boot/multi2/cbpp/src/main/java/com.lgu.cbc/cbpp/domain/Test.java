package com.lgu.cbc.cbpp.domain;

import com.lgu.cbc.common.domain.TestBase;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false) @Entity
@Table(name="TEST")

public class Test extends TestBase {
    @Builder
    public Test(Integer seq, String name) {
        setSeq(seq);
        setName(name);
    }

}