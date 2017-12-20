package com.khh.project.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "project")
@Component
@Data
public class ProjectProperties {
	private Map<String, String> properties = new HashMap<String, String>();

	private LocaleChange localeChange;

	@Data
	public static class LocaleChange{
		String paramName;
	}
}

