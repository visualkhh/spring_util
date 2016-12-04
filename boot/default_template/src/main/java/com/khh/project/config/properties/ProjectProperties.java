package com.khh.project.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "khh.project")
@Data
public class ProjectProperties {

//	@Data
	public static class Config{
		String welcom_title;

	public String getWelcom_title() {
		return welcom_title;
	}

	public void setWelcom_title(String welcom_title) {
		this.welcom_title = welcom_title;
	}
}
}
