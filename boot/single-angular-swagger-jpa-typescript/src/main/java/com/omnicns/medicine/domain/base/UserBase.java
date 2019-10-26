package com.omnicns.medicine.domain.base;

import com.fasterxml.jackson.annotation.JsonView;
import com.omnicns.medicine.code.*;
import com.omnicns.medicine.domain.convert.PrivateConvert;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class UserBase extends DomainBase implements Serializable {
    @Id
    @Column(name = "USER_SEQ")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "시퀀스")
    @JsonView({Views.Api.class})
    protected Integer userSeq;

    @Column(name = "NM")
    @JsonView({Views.Api.class})
    @ApiModelProperty(notes = "이름")
    String nm;

    @Column(name = "GEN_CD")
    @Enumerated(EnumType.STRING)
    @JsonView({Views.Api.class})
    @ApiModelProperty(notes = "성별 코드")
    GenderCode genCd;

    @Convert(converter = PrivateConvert.class)
    @Column(name = "CPON")
    @ApiModelProperty(notes = "연락처")
    @JsonView({Views.Api.class})
    String cpon;

    @Column(name = "BIRDT")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonView({Views.Api.class})
    @ApiModelProperty(notes = "생년월일")
    ZonedDateTime birdt;

    @Column(name = "GAD_NM")
    @JsonView({Views.Api.class})
    @ApiModelProperty(notes = "보호자 이름")
    String gadNm;

    @Column(name = "GAD_RLTSP_CD")
    @Enumerated(EnumType.STRING)
    @JsonView({Views.Api.class})
    @ApiModelProperty(notes = "보호자 관계")
    ParentCd gadRltspCd;

    @Convert(converter = PrivateConvert.class)
    @Column(name = "GAD_CPON")
    @JsonView({Views.Api.class})
    @ApiModelProperty(notes = "보호자 연락처")
    String gadCpon;

    @Column(name = "GAD_EMAIL")
    @JsonView({Views.Api.class})
    @ApiModelProperty(notes = "보호자 이메일")
    String gadEmail;

    @Column(name = "PTCP_GRP_CD")
    @Enumerated(EnumType.STRING)
    @JsonView({Views.Api.class})
    @ApiModelProperty(notes = "참여 그룹 코드")
    PtcpGrpCd ptcpGrpCd;

    @Column(name = "PTCP_CD")
    @Enumerated(EnumType.STRING)
    @JsonView({Views.Api.class})
    @ApiModelProperty(notes = "참여 코드")
    PtcpCd ptcpCd;

    @Column(name = "PTCP_ST_DT")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonView({Views.Api.class})
    @ApiModelProperty(notes = "참여 시작 날짜")
    ZonedDateTime ptcpStDt;

    @Column(name = "PTCP_END_DT")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonView({Views.Api.class})
    @ApiModelProperty(notes = "참여 종료 날짜")
    ZonedDateTime ptcpEndDt;

    @Column(name = "USE_CD")
    @Enumerated(EnumType.STRING)
    @JsonView({Views.Api.class})
    @ApiModelProperty(notes = "사용 여부 코드")
    UseCd useCd;


    @Column(name = "CONT")
//    @ApiModelProperty(notes = "메모")
    String cont;

    @Column(name = "UPD_DT")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    ZonedDateTime updDt;

    @Column(name = "REG_DT")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    ZonedDateTime regDt;

    @Column(name = "LST_SERIAL_NO")
    String lstSerialNo;

    @Column(name = "LST_LGIN_DT")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    ZonedDateTime lstLginDt;


    @PrePersist
    protected void onCreate() {
        if (regDt == null) {
            regDt = ZonedDateTime.now();
        }
    }


    public static class Views {
        public interface Api {
        }
    }
}
