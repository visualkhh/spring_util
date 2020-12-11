package com.company.service.monitor.domain.mindcares.base;

import com.company.service.monitor.model.ModelBase;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
@IdClass(MeasureRelationshipBase.MeasureRelationshipId.class)
@EqualsAndHashCode(callSuper = false)
public class MeasureRelationshipBase extends ModelBase implements Serializable {

    @Getter
    @Setter
    @EqualsAndHashCode(callSuper = false)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MeasureRelationshipId implements Serializable {
        String svcId;
        String measureNo;
        String measureType;
    }

    @Column(name = "USER_ID")
    String userId;

    @Id
    @Column(name = "SVC_ID")
    String svcId;

    @Id
    @Column(name = "MEASURE_NO")
    String measureNo;

    @Id
    @Column(name = "MEASURE_TYPE")
    String measureType;

    @Column(name = "REG_DT")
    String regDt;

    @Column(name = "UPD_DT")
    String updDt;

    @Column(name = "ADMIN_LGIN_ID")
    String adminLginId;

    @Column(name = "AL_TYPE")
    String alType;

    @Column(name = "APP_PACKAGE_NM")
    String appPackageNm;

    @Column(name = "APP_VER")
    String appVer;

    @Column(name = "OS_VER")
    String osVer;

    @Column(name = "SVC_CTG_TYPE")
    String svcCtgType;

    @Column(name = "PHONE_MODEL")
    String phoneModel;

    @Column(name = "MEAS_DT")
    String measDt;

}
