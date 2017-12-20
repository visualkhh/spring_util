package com.visualkhh.api.config;

import org.springframework.http.MediaType;

import java.nio.charset.Charset;
import java.util.Map;

public class VisualkhhMediaType extends MediaType{
	public final static MediaType VISUALKHH_V1_JSON;
	public final static String VISUALKHH_V1_JSON_VALUE = "application/com.visualkhh-v1+json";
	public final static MediaType VISUALKHH_V2_JSON;
	public final static String VISUALKHH_V2_JSON_VALUE = "application/com.visualkhh-v2+json";
	static {
		VISUALKHH_V1_JSON = valueOf(VISUALKHH_V1_JSON_VALUE);
		VISUALKHH_V2_JSON = valueOf(VISUALKHH_V2_JSON_VALUE);
	}

	public VisualkhhMediaType(String type) {
		super(type);
	}

	public VisualkhhMediaType(String type, String subtype) {
		super(type, subtype);
	}

	public VisualkhhMediaType(String type, String subtype, Charset charset) {
		super(type, subtype, charset);
	}

	public VisualkhhMediaType(String type, String subtype, double qualityValue) {
		super(type, subtype, qualityValue);
	}

	public VisualkhhMediaType(MediaType other, Charset charset) {
		super(other, charset);
	}

	public VisualkhhMediaType(MediaType other, Map<String, String> parameters) {
		super(other, parameters);
	}

	public VisualkhhMediaType(String type, String subtype, Map<String, String> parameters) {
		super(type, subtype, parameters);
	}
}
