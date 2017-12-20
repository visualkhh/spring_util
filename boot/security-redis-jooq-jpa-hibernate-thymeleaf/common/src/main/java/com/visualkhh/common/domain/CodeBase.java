package com.visualkhh.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter @Data @Setter @MappedSuperclass @EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CodeBase extends  DomainBase implements Serializable {
    @Id
    @Column(name = "CD")
    private String cd;

    @Column(name = "CD_NM")
    private String cdNm;

    @Column(name = "CD_NM_EN")
    private String cdNmEn;

    @Column(name = "CD_XP_LN")
    private String cdXpln;

    @Column(name = "CD_ORD")
    private String cdOrd;

    @Column(name = "MAPPING_CD")
    private String mappingCd;

    @Column(name = "PRNT_CD", updatable = false, insertable = false)
    private String prntCd;

    @Column(name = "API_USE_YN")
    @JsonIgnore
    private String apiUseYn;

    @Column(name = "REG_DT")
    @JsonIgnore
    private ZonedDateTime regDt;

    @PrePersist
    protected void onCreate() {
        if (regDt == null) { regDt = ZonedDateTime.now(); }
    }
}
