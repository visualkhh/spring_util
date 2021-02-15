package com.clone.chat.config.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;

public class SimpleGrantedAuthoritySerializer extends StdSerializer<SimpleGrantedAuthority> {

    public SimpleGrantedAuthoritySerializer() {
        super(SimpleGrantedAuthority.class);
    }

    @Override
    public void serialize(SimpleGrantedAuthority value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("authority", value.getAuthority());
        gen.writeEndObject();
    }

}
