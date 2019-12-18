package com.example.demo.config;

import io.undertow.UndertowOptions;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.apache.coyote.http2.Http2Protocol;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.embedded.jetty.JettyServerCustomizer;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

@Configuration
public class Config {

//    @Bean
//    UndertowEmbeddedServletContainerFactory embeddedServletContainerFactory() {
//        UndertowEmbeddedServletContainerFactory factory = new UndertowEmbeddedServletContainerFactory();
//        factory.addBuilderCustomizers(
//                builder -> builder.setServerOption(UndertowOptions.ENABLE_HTTP2, true));
//        return factory;
//    }

//    @Bean
//    public WebServerFactoryCustomizer<UndertowServletWebServerFactory> containerCustomizer() {
//        return (WebServerFactoryCustomizer) factory -> {
//            UndertowServletWebServerFactory undertowFactory = (UndertowServletWebServerFactory) factory;
//            undertowFactory.getBuilderCustomizers().add(builder -> {
//                builder.addHttpListener(httpPort, httpInterface);
//            });
//        };
//    }

    @Bean
    public UndertowServletWebServerFactory servletWebServerFactory() {
        UndertowServletWebServerFactory factory = new UndertowServletWebServerFactory();
        factory.addBuilderCustomizers(builder -> builder.setServerOption(UndertowOptions.ENABLE_HTTP2, true));
        //        factory.addDeploymentInfoCustomizers(deploymentInfo -> deploymentInfo.addOuterHandlerChainWrapper(gracefulShutdownHandlerWrapper));

        return factory;
    }

    //    @Bean
//    public ServletWebServerFactory serveltContainer() {
//        UndertowServletWebServerFactory factory = new UndertowServletWebServerFactory();
//        factory.addBuilderCustomizers(builder -> builder.setServerOption(UndertowOptions.ENABLE_HTTP2, true));
//        //        factory.addDeploymentInfoCustomizers(deploymentInfo -> deploymentInfo.addOuterHandlerChainWrapper(gracefulShutdownHandlerWrapper));
//
//        return factory;
//    }
//    @Bean
//    public JettyHttp2Customizer customizer(final ServerProperties serverProperties) {
//        return new JettyHttp2Customizer(serverProperties);
//    }


//
//    @Bean
//    public TomcatServletWebServerFactory servletWebServerFactory() {
//        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
//        factory.addConnectorCustomizers((connector -> {
//            connector.addUpgradeProtocol(new Http2Protocol());
//        }));
//        return factory;
//    }


//    @Bean
//    UndertowEmbeddedServletContainerFactory embeddedServletContainerFactory() {
//        UndertowEmbeddedServletContainerFactory factory = new UndertowEmbeddedServletContainerFactory();
//        factory.addBuilderCustomizers(
//                builder -> builder.setServerOption(UndertowOptions.ENABLE_HTTP2, true));
//        return factory;
//    }
//
//    @Bean
//    public ServletWebServerFactory serveltContainer(){
//        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
////        tomcat.addAdditionalTomcatConnectors(createStandardConnector());
////        tomcat.addAdditionalTomcatConnectors(createSslConnector());
//        tomcat.addConnectorCustomizers((connector -> {
//            connector.addUpgradeProtocol(new Http2Protocol());
//        }));
//        return tomcat;
//    }
//    private Connector createSslConnector() {
//        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
//        try {
//            File keystore = new ClassPathResource("keystore").getFile();
//            File truststore = new ClassPathResource("keystore").getFile();
//            connector.setScheme("https");
//            connector.setSecure(true);
//            connector.setPort(8070);
//            protocol.setSSLEnabled(true);
//            protocol.setKeystoreFile(keystore.getAbsolutePath());
//            protocol.setKeystorePass("changeit");
//            protocol.setTruststoreFile(truststore.getAbsolutePath());
//            protocol.setTruststorePass("changeit");
//            protocol.setKeyAlias("apitester");
//            return connector;
//        }
//        catch (Exception ex) {
//            throw new IllegalStateException("can't access keystore: [" + "keystore"
//                    + "] or truststore: [" + "keystore" + "]", ex);
//        }
//    }
//    private Connector createStandardConnector(){
//        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        connector.setPort(8070);
//        return connector;
//    }

//    @Bean
//    public TomcatServletWebServerFactory tomcatCustomizer() {
//        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
//        factory.addConnectorCustomizers((connector -> {
//            connector.addUpgradeProtocol(new Http2Protocol());
//        }));
//        return factory;
//    }
//    @Bean
//    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> cookieProcessorCustomizer() {
//        return (serverFactory) -> serverFactory.addContextCustomizers(
//                (context) -> context.setCookieProcessor(new LegacyCookieProcessor()));
//    }

//    @Bean
//    public ConfigurableServletWebServerFactory tomcatCustomizer() {
//        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
//        factory.addConnectorCustomizers(connector -> connector.addUpgradeProtocol(new Http2Protocol()));
//        return factory;
//    }

//    @Bean
//    public WebServerFactoryCustomizer tomcatCustomizer() {
//        return (container) -> {
//            if (container instanceof TomcatServletWebServerFactory) {
//                ((TomcatEmbeddedServletContainerFactory) container)
//                        .addConnectorCustomizers((connector) -> {
//                            connector.addUpgradeProtocol(new Http2Protocol());
//                        });
//            }
//        };
//    }
}
