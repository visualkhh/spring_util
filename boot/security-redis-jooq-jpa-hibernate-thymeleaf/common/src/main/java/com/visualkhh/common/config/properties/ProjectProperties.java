package com.visualkhh.common.config.properties;


import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@EnableConfigurationProperties @Configuration @ConfigurationProperties(prefix = "project")
@Component @Getter @Setter @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode(callSuper = false)
public class ProjectProperties {
	private Map<String, String> properties = new HashMap<String, String>();
	private CacheConfig cache;


	@Getter@Setter
	public static class CacheConfig{
		Long defaultExpireTime;
		Map<String, Long> expireTime;
	}
}