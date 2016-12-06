package com.khh.project.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.Properties;

@ConfigurationProperties(prefix = "hibernate")
@Component
@Data
public class HibernateProperties {

	String mappingLocations;
	String[] packagesToScan;
	String[] annotatedPackages;
	private Properties properties;
}
