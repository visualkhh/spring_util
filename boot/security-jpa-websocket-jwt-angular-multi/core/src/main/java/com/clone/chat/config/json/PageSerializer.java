package com.clone.chat.config.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.data.domain.PageImpl;

import java.io.IOException;

public class PageSerializer extends StdSerializer<PageImpl> {

    public PageSerializer() {
        super(PageImpl.class);
    }

    @Override
    public void serialize(PageImpl value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("number", value.getNumber());
        gen.writeNumberField("numberOfElements", value.getNumberOfElements());
        gen.writeNumberField("totalElements", value.getTotalElements());
        gen.writeNumberField("totalPages", value.getTotalPages());
        gen.writeNumberField("size", value.getSize());
        gen.writeFieldName("content");
        provider.defaultSerializeValue(value.getContent(), gen);
        gen.writeEndObject();



//        ObjectMapper om = new ObjectMapper().disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
//        jsonGen.writeStartObject();
//        jsonGen.writeFieldName("size");
//        jsonGen.writeNumber(page.getSize());
//        jsonGen.writeFieldName("number");
//        jsonGen.writeNumber(page.getNumber());
//        jsonGen.writeFieldName("totalElements");
//        jsonGen.writeNumber(page.getTotalElements());
//        jsonGen.writeFieldName("last");
//        jsonGen.writeBoolean(page.isLast());
//        jsonGen.writeFieldName("totalPages");
//        jsonGen.writeNumber(page.getTotalPages());
//        jsonGen.writeObjectField("sort", page.getSort());
//        jsonGen.writeFieldName("first");
//        jsonGen.writeBoolean(page.isFirst());
//        jsonGen.writeFieldName("numberOfElements");
//        jsonGen.writeNumber(page.getNumberOfElements());
//        jsonGen.writeFieldName("content");
//        jsonGen.writeRawValue(
//                om.writerWithView(serializerProvider.getActiveView()).writeValueAsString(page.getContent()));
//        jsonGen.writeEndObject();
    }

}
