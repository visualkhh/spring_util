package com.visualkhh.api.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class AppVersion {
    private String appUpdateAftrProgYn;
    private String verInfo;
    private String appDownUrl;
    private String osType;
    private List<DataVersion> dataLastVer;
}
