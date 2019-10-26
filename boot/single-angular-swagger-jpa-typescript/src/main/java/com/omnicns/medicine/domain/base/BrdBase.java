package com.omnicns.medicine.domain.base;

import com.fasterxml.jackson.annotation.JsonView;
import com.omnicns.medicine.code.BrdCateCd;
import com.omnicns.medicine.code.UseCd;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter @Setter @MappedSuperclass @EqualsAndHashCode(callSuper = false)
@ApiModel
public class BrdBase extends DomainBase implements Serializable {

    @Id
    @JsonView(Views.Brd.class)
    @Column(name = "BRD_SEQ")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer brdSeq;

    @Column(name = "CATE_CD")
    @Enumerated(EnumType.STRING)
    private BrdCateCd cateCd;

    @JsonView(Views.Brd.class)
    @Column(name = "TITL")
    private String titl;


    @JsonView(Views.Brd.class)
    @Column(name = "CONT")
    private String cont;


    @Column(name = "USE_CD")
    @Enumerated(EnumType.STRING)
    private UseCd useCd;

    @JsonView(Views.Brd.class)
    @Column(name = "REG_DT")
    private ZonedDateTime regDt;

    @Column(name = "ADM_SEQ")
    private Integer admSeq;

    @PrePersist
    protected void onCreate() {
        if (regDt == null) { regDt = ZonedDateTime.now(); }
    }

    public static class Views{
        public interface Brd {}
    }
}
