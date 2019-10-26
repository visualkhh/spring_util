package com.omnicns.medicine.domain.base;

import com.omnicns.medicine.code.OsType;
import com.omnicns.medicine.code.GameSetCd;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter @Setter @MappedSuperclass @EqualsAndHashCode(callSuper=false)
public class GameSetRstBase extends DomainBase implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "SET_RST_SEQ")
    private Integer setRstSeq;

    @Column(name = "SET_MSMT_NO")
    private String setMsmtNo;

    @Column(name = "SET_CD")
    @Enumerated(EnumType.STRING)
    private GameSetCd setCd;

    @Column(name = "MSMT_ST_DT")
    private ZonedDateTime msmtStDt;

    @Column(name = "MSMT_END_DT")
    private ZonedDateTime msmtEndDt;

    @Column(name = "CENT")
    private Double cent;

    @Column(name = "REG_DT")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime regDt;

    // header
    @Column(name = "CPON_ID")
    private String cponId;

    @Column(name = "SERIAL_NO")
    private String serialNo;

    @Column(name = "CPON_MODEL")
    private String cponModel;

    @Column(name = "OS_TYPE_CD")
    @Enumerated(EnumType.STRING)
    private OsType osTypeCd;

    @Column(name = "OS_VER")
    private String osVer;

    @Column(name = "PKG_NM")
    private String pkgNm;

    @Column(name = "PKG_VER")
    private String pkgVer;

    @Column(name = "USER_SEQ")
    private Integer userSeq;


    @PrePersist
    protected void onCreate() {
        if (regDt == null) { regDt = ZonedDateTime.now(); }
    }

    public static class Views {
        public interface Details {
        }
    }
}
