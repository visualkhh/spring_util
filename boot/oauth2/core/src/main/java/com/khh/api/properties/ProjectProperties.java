package com.khh.api.properties;

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

	public ProjectProperties(){
//		this.localeChange=new LocaleChange();
//		this.localeChange.paramName="lang";
	}

	@Data
	public static class LocaleChange{
		String paramName;
	}
}

