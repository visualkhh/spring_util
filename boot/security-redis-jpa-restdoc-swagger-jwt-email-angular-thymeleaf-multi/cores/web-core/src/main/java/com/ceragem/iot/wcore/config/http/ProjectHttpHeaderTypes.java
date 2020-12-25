package com.ceragem.iot.wcore.config.http;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.http.HttpHeaders;

public enum ProjectHttpHeaderTypes {
    ACCEPT(HttpHeaders.ACCEPT);
    private final String value;

    ProjectHttpHeaderTypes(String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return value;
    }
}
