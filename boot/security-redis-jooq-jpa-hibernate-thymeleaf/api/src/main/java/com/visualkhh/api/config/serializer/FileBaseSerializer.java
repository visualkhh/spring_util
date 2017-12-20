package com.visualkhh.api.config.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.visualkhh.api.domain.File;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class FileBaseSerializer extends JsonSerializer<File> {
    @Value("${project.properties.resource.domain}")
    private String doamin;

    @Override
    public void serialize(File value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("fileSeq",value.getFileSeq());
        gen.writeStringField("fileNm",value.getFileNm());
        gen.writeNumberField("fileSize",value.getFileSize());
        gen.writeStringField("filePath",doamin+value.getFilePath());
        gen.writeNumberField("playTime",value.getFileSeq());
        gen.writeEndObject();
    }
}
