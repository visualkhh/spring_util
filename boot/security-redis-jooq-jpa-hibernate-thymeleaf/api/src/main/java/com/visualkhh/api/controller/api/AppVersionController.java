package com.visualkhh.api.controller.api;

import com.visualkhh.api.config.VisualkhhMediaType;
import com.visualkhh.api.model.AppVersion;
import com.visualkhh.api.model.DataVersion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j @EnableCaching @RestController
@RequestMapping(AppVersionController.URI_PREFIX)
public class AppVersionController {
    public static final String URI_PREFIX 		= ApiController.URI_PREFIX+"/app_version";

    @GetMapping(value={"","/"}, produces= VisualkhhMediaType.VISUALKHH_V1_JSON_VALUE)
    public AppVersion getAppVersion(){
        AppVersion appVersion = new AppVersion();
        appVersion.setOsType("OST001");
        appVersion.setAppUpdateAftrProgYn("N");
        appVersion.setAppDownUrl("http://www.visualkhh.co.kr");
        appVersion.setVerInfo("0.0.1");

        List<DataVersion> dataVersions = new ArrayList<>();
        DataVersion dataVersion = new DataVersion();
        dataVersion.setDataType("DAT001");
        dataVersion.setUpdateDt(ZonedDateTime.parse("2017-12-15T17:30:00.000+09:00"));
        dataVersions.add(dataVersion);
        dataVersion.setDataType("DAT002");
        dataVersion.setUpdateDt(ZonedDateTime.parse("2017-12-15T17:30:00.000+09:00"));
        dataVersions.add(dataVersion);
        dataVersion.setDataType("DAT003");
        dataVersion.setUpdateDt(ZonedDateTime.parse("2017-12-15T17:30:00.000+09:00"));
        dataVersions.add(dataVersion);
        dataVersion.setDataType("DAT004");
        dataVersion.setUpdateDt(ZonedDateTime.parse("2017-12-15T17:30:00.000+09:00"));
        dataVersions.add(dataVersion);
        dataVersion.setDataType("DAT005");
        dataVersion.setUpdateDt(ZonedDateTime.parse("2017-12-15T17:30:00.000+09:00"));
        dataVersions.add(dataVersion);
        dataVersion.setDataType("DAT006");
        dataVersion.setUpdateDt(ZonedDateTime.parse("2017-12-15T17:30:00.000+09:00"));
        dataVersions.add(dataVersion);

        appVersion.setDataLastVer(dataVersions);
        return appVersion;
    }
}
