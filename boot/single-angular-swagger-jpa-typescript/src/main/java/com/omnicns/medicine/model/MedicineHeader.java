package com.omnicns.medicine.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.omnicns.medicine.code.OsType;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MedicineHeader {

    /*
        //AllowableListValues osTypeAllowableList = new AllowableListValues(Stream.of(OsType.values()).map(it -> it.name()).collect(Collectors.toList()), "string");
        //Parameter cponId        = new Parameter("X-cponId",     "폰 아이디",       "test-cponId",           false, false, new ModelRef("string"), null, null, "header" , null, false, new ArrayList<>());
        //Parameter serialNo      = new Parameter("X-serialNo",   "디바이스 고유 번호",   "test-serialNo",      false, false, new ModelRef("string"), null, null, "header" , null, false, new ArrayList<>());
        //Parameter cponModel     = new Parameter("X-cponModel",  "폰 모델명",      "test-cponModel",         false, false, new ModelRef("string"), null, null, "header" , null, false, new ArrayList<>());
        //Parameter osTypeCd      = new Parameter("X-osTypeCd",   "폰 OS 타입",     "OST001",                false, false, new ModelRef("string"), null, osTypeAllowableList, "header" , null, false, new ArrayList<>());
        //Parameter osVersion     = new Parameter("X-osVer",       "폰 OS 버전",         "test-cponModel",   false, false, new ModelRef("string"), null, null, "header" , null, false, new ArrayList<>());
        //Parameter pkgNm         = new Parameter("X-pkgNm",      "어플 패키지명",       "test-pkgNm",         false, false, new ModelRef("string"), null, null, "header" , null, false, new ArrayList<>());
        //Parameter pkgVersion    = new Parameter("X-pkgVer",      "어플 VERSION",      "test-pkgVer",       false, false, new ModelRef("string"), null, null, "header" , null, false, new ArrayList<>());
        //`Parameter userSeq       = new Parameter("X-userSeq",    "서버 회원 고유 아이디", "1",                  false, false, new ModelRef("int"), null, null, "header" , null, false, new ArrayList<>());
     */
    public static final String HEADER_cponId = "X-cponId";
    public static final String HEADER_serialNo = "X-serialNo";
    public static final String HEADER_cponModel = "X-cponModel";
    public static final String HEADER_osTypeCd = "X-osTypeCd";
    public static final String HEADER_osVer = "X-osVer";
    public static final String HEADER_pkgNm = "X-pkgNm";
    public static final String HEADER_pkgVer = "X-pkgVer";
    public static final String HEADER_userSeq = "X-userSeq";

    //    @NotEmpty(groups = {ValidatedApiHeadGroup.ValidSerialNo.class, ValidatedApiHeadGroup.ValidSerialNoAndCponId.class, ValidatedApiHeadGroup.ValidSerialNoAndCponIdAndUserDvcSeq.class})
    private String serialNo;        // 디바이스 시리얼 번호
    //    @NotEmpty(groups = {ValidatedApiHeadGroup.ValidCponId.class, ValidatedApiHeadGroup.ValidSerialNoAndCponId.class, ValidatedApiHeadGroup.ValidOsTypeAndCponId.class, ValidatedApiHeadGroup.ValidSerialNoAndCponIdAndUserDvcSeq.class})
    private String cponId;          // 핸드폰 아이디 (UUID)
    //    @NotEmpty(groups = {ValidatedApiHeadGroup.ValidOsTypeAndCponId.class})
    private OsType osTypeCd;              // 핸드폰 OS (OST001: android / OST002 : ios)
    private String cponModel;       // 핸드폰 모델
    private String pkgNm;           // 앱 패키지명
    private String pkgVer;      // 앱 패키지버전
    private String osVer;        // OS별 버전정보
    //    @NotNull(groups = {ValidatedApiHeadGroup.ValidSerialNoAndCponIdAndUserDvcSeq.class})
    private Integer userSeq;      //유저 DVC SEQ
//    private String ptcpGrpCd;        // 참여그룹코드
    //    private String svcCd;           //서비스 타입 코드 , SVC001	청소년, SVC002	성인

    @Builder
    public MedicineHeader(String serialNo, String cponId, OsType osTypeCd, String cponModel, String pkgNm, String pkgVer, Integer userSeq, String osVer) {
        this.cponId = cponId;
        this.serialNo = serialNo;
        this.cponModel = cponModel;
        this.osTypeCd = osTypeCd;
        this.osVer = osVer;
        this.pkgNm = pkgNm;
        this.pkgVer = pkgVer;
        this.userSeq = userSeq;
    }

    public MedicineHeader(Map<String, String> headerMap) {
        this.cponId = headerMap.get(HEADER_cponId);
        this.serialNo = headerMap.get(HEADER_serialNo);
        this.cponModel = headerMap.get(HEADER_cponModel);
        this.osTypeCd = OsType.valueOf(headerMap.get(HEADER_osTypeCd));
        this.osVer = headerMap.get(HEADER_osVer);
        this.pkgNm = headerMap.get(HEADER_pkgNm);
        this.pkgVer = headerMap.get(HEADER_pkgVer);
        this.userSeq = Integer.parseInt(headerMap.get(HEADER_userSeq));
    }

//    public SvcTypeCode getSvcTypeCode(){
//        return getSvcTypeCode(null);
//    }
//    public SvcTypeCode getSvcTypeCode(SvcTypeCode defaultData){
//        SvcTypeCode r = defaultData;
//        try {r = SvcTypeCode.valueOf(this.svcCd);}catch (Exception e){};
//        return r;
//    }
}
