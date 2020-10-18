package com.today.house.convert;

import com.omnicns.java.security.AES256Coder;
import com.today.house.config.properties.ProjectProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;

@Component
@Slf4j
public class PrivateConvert implements AttributeConverter<String, String> {

    private static ProjectProperties projectProperties;

    @Autowired
    public void setObjectMapper(ProjectProperties projectProperties) {
        this.projectProperties = projectProperties;
    }

    @Override
    public String convertToDatabaseColumn(String attribute) {
        if (StringUtils.isEmpty(attribute)){
            return null;
        }
        try {
            String privateKey = projectProperties.getProperties().get("private-key");
            return AES256Coder.encode(privateKey, attribute);
//            if (StringUtils.isBlank(attribute)) {
//                return attribute;
//            } else {
//            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        if (StringUtils.isEmpty(dbData)){
            return null;
        }
        try {
            String privateKey = projectProperties.getProperties().get("private-key");
            return AES256Coder.decode(privateKey, dbData);
//            if (StringUtils.isEmpty(dbData)) {
//                return dbData;
//            } else {
//            }
        } catch (Exception e) {
            return null;
        }
    }
}
