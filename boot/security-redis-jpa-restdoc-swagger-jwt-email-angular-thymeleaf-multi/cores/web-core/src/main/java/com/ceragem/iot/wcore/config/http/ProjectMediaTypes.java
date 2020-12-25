package com.ceragem.iot.wcore.config.http;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.http.HttpHeaders;

public enum ProjectMediaTypes {
    LIST_JSON(ProjectMediaType.LIST_JSON_VALUE),
    DATATABLE_PAGING_JSON(ProjectMediaType.DATATABLE_PAGING_JSON_VALUE),
    APPLICATION_JSON(ProjectMediaType.APPLICATION_JSON_VALUE);
    private final String value;

    ProjectMediaTypes(String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return value;
    }
}
