package com.omnicns.medicine.config.message;

import org.springframework.util.DefaultPropertiesPersister;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CustomPropertiesPersistor  extends DefaultPropertiesPersister {

	private final Map<Object, Object> data = new HashMap<>();

	@Override
	public void load(Properties props, InputStream is) throws IOException {
		super.load(props, is);
		data.putAll(props);
	}

	@Override
	public void load(Properties props, Reader reader) throws IOException {
		super.load(props, reader);
		data.putAll(props);
	}

	public Map<Object, Object> getData() {
		return Collections.unmodifiableMap(data);
	}
}
