package com.visualkhh.common.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Getter @Setter @MappedSuperclass @EqualsAndHashCode(callSuper=false)
public class EventImgBase extends DomainBase implements Serializable {
    @Id
    private Integer fileSeq;

    @Column
    private Integer eventSeq;
}
