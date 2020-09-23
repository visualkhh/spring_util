package com.genome.dx.wcore.config.properties;


import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Component
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "web-core")
public class WebCoreProperties {
    private int serviceExecuteTimeoutSecond = 30;
    private Map<String, String> properties = new HashMap<>();
    private Http11Protocol http11Protocol;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Http11Protocol {
        Integer maxKeepAliveRequests;
        Integer connectionTimeout;
        Integer keepAliveTimeout;
        Integer acceptorThreadCount;
        Boolean tcpNoDelay;

        @Builder
        public Http11Protocol(Integer maxKeepAliveRequests, Integer connectionTimeout, Integer keepAliveTimeout, Integer acceptorThreadCount, Boolean tcpNoDelay) {
            this.maxKeepAliveRequests = maxKeepAliveRequests;
            this.connectionTimeout = connectionTimeout;
            this.keepAliveTimeout = keepAliveTimeout;
            this.acceptorThreadCount = acceptorThreadCount;
            this.tcpNoDelay = tcpNoDelay;
        }
    }

}

