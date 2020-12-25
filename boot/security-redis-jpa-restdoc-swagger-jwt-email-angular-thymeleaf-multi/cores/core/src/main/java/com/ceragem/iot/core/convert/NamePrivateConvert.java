package com.ceragem.iot.core.convert;

import com.ceragem.iot.core.config.properties.ProjectProperties;
import com.omnicns.java.security.AES256Coder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;

@Component
@Slf4j
public class NamePrivateConvert implements AttributeConverter<String, String> {

    private static ProjectProperties projectProperties;

    @Autowired
    public void setObjectMapper(ProjectProperties projectProperties) {
        this.projectProperties = projectProperties;
    }

    @Override
    public String convertToDatabaseColumn(String attribute) {
        try {
            String privateKey = projectProperties.getProperties().get("private-key");
            return AES256Coder.encode(privateKey, attribute);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        try {
            String privateKey = projectProperties.getProperties().get("private-key");
            return AES256Coder.decode(privateKey, dbData).replaceAll(".$", "*");
        } catch (Exception e) {
            return null;
        }
    }
}
