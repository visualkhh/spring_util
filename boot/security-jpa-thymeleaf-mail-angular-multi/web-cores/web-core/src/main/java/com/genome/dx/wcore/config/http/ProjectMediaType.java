package com.genome.dx.wcore.config.http;

import org.springframework.http.MediaType;

import java.nio.charset.Charset;
import java.util.Map;

public class ProjectMediaType extends MediaType {
	public static final MediaType LIST_JSON;
	public static final String LIST_JSON_VALUE = "application/list+json";
	public static final MediaType DATATABLE_PAGING_JSON;
	public static final String DATATABLE_PAGING_JSON_JSON_VALUE = "application/datatable-paging+json";
	static {
		LIST_JSON = valueOf(LIST_JSON_VALUE);
		DATATABLE_PAGING_JSON = valueOf(DATATABLE_PAGING_JSON_JSON_VALUE);
	}

	public ProjectMediaType(String type) {
		super(type);
	}

	public ProjectMediaType(String type, String subtype) {
		super(type, subtype);
	}

	public ProjectMediaType(String type, String subtype, Charset charset) {
		super(type, subtype, charset);
	}

	public ProjectMediaType(String type, String subtype, double qualityValue) {
		super(type, subtype, qualityValue);
	}

	public ProjectMediaType(MediaType other, Charset charset) {
		super(other, charset);
	}

	public ProjectMediaType(MediaType other, Map<String, String> parameters) {
		super(other, parameters);
	}

	public ProjectMediaType(String type, String subtype, Map<String, String> parameters) {
		super(type, subtype, parameters);
	}
}
