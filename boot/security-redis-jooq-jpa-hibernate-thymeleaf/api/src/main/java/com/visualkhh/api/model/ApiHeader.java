package com.visualkhh.api.model;

import com.visualkhh.api.validate.ValidatedApiHeadGroup;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

@Getter @Setter @NoArgsConstructor @EqualsAndHashCode(callSuper=false)
public class ApiHeader {
    @Builder
    public ApiHeader(String serialNo, String cponId, String os, String cponModel, String pkgNm, String pkgVersion, Integer userDvcSeq) {
        this.serialNo = serialNo;
        this.cponId = cponId;
        this.os = os;
        this.cponModel = cponModel;
        this.pkgNm = pkgNm;
        this.pkgVersion = pkgVersion;
        this.userDvcSeq = userDvcSeq;
    }

    @NotEmpty(groups = {ValidatedApiHeadGroup.ValidSerialNo.class, ValidatedApiHeadGroup.ValidSerialNoAndCponId.class})
	private String serialNo;        // 디바이스 시리얼 번호
    @NotEmpty(groups = {ValidatedApiHeadGroup.ValidCponId.class, ValidatedApiHeadGroup.ValidSerialNoAndCponId.class})
    private String cponId;          // 핸드폰 아이디 (UUID)
    private String os;              // 핸드폰 OS
    private String cponModel;       // 핸드폰 모델
    private String pkgNm;           // 앱 패키지명
    private String pkgVersion;      // 앱 패키지버전

    private Integer userDvcSeq;      //유저 DVC SEQ

}
