package com.omnicns.medicine.domain.base;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter @Setter @MappedSuperclass @EqualsAndHashCode(callSuper=false) @NoArgsConstructor
public class UserDvcBase extends DomainBase implements Serializable {
    @Id
    @Column(name = "USER_DVC_SEQ")
    @JsonView(Views.ApiUserPut.class)
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer userDvcSeq;

    @Pattern(regexp="(AGC)\\d{3}?")
    @Column(name = "AGE_CD")
    private String ageCd;

    @Pattern(regexp="(GEC001|GEC002)?")
    @Column(name = "GEN_CD")
    private String genCd;

    @Column(name = "LST_LGIN_IP")
    private String lstLginIp;

    @Column(name = "USER_SEQ")
    private Integer userSeq;

    @Column(name = "DVC_SEQ")
    private Integer dvcSeq;

    @Column(name = "REG_DT")
    private ZonedDateTime regDt;


    @Builder
    public UserDvcBase(Integer userDvcSeq,String ageCd, String genCd, String lstLginIp, Integer userSeq, Integer dvcSeq, ZonedDateTime regDt) {
        this.userDvcSeq = userDvcSeq;
        this.ageCd = ageCd;
        this.genCd = genCd;
        this.lstLginIp = lstLginIp;
        this.userSeq = userSeq;
        this.dvcSeq = dvcSeq;
        this.regDt = regDt;
    }

    @PrePersist
    protected void onCreate() {
        if (regDt == null) { regDt = ZonedDateTime.now(); }
    }

    public static class Views{
       public interface ApiUserPut{}
    }
}
