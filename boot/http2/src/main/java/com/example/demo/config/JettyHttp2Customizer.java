//package com.example.demo.config;
//
//
//import org.eclipse.jetty.alpn.server.ALPNServerConnectionFactory;
//import org.eclipse.jetty.http2.HTTP2Cipher;
//import org.eclipse.jetty.http2.server.HTTP2ServerConnectionFactory;
//import org.eclipse.jetty.server.*;
//import org.eclipse.jetty.util.ssl.SslContextFactory;
//import org.springframework.boot.autoconfigure.web.ServerProperties;
//import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
//import org.springframework.boot.web.server.WebServerFactoryCustomizer;
//
///**
// * This {@code WebServerFactoryCustomizer} replaces the jetty connector with a new connector configured with HTTPS and
// * which accepts HTTP/2.
// *
// * @author Guillaume DROUET
// */
//public class JettyHttp2Customizer implements WebServerFactoryCustomizer<JettyServletWebServerFactory> {
//
//    private final ServerProperties serverProperties;
//
//    /**
//     * Builds a new instance.
//     *
//     * @param serverProperties the properties containing the SSL configurations
//     */
//    public JettyHttp2Customizer(final ServerProperties serverProperties) {
//        this.serverProperties = serverProperties;
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void customize(final JettyServletWebServerFactory factory) {
//        if (serverProperties.getSsl() != null && serverProperties.getSsl().isEnabled()) {
//            factory.addServerCustomizers(this::sslCustomizer);
//        }
//    }
//
//    private void sslCustomizer(final Server server) {
//        final ServerConnector existingConnector = ServerConnector.class.cast(server.getConnectors()[0]);
//        final SslContextFactory sslContextFactory = configureSslContextFactoryFrom(existingConnector);
//
//        final HttpConfiguration httpConfiguration = getHttpConfigurationFrom(existingConnector);
//        final ConnectionFactory[] connectionFactories = createConnectionFactories(sslContextFactory, httpConfiguration);
//        final ServerConnector newConnector = new ServerConnector(server, connectionFactories);
//        newConnector.setPort(existingConnector.getPort());
//
//        overrideExistingServerConnectors(server, newConnector);
//    }
//
//    private SslContextFactory configureSslContextFactoryFrom(final ServerConnector connector) {
//        final SslContextFactory sslContextFactory = getSslContextFactoryFrom(connector);
//        sslContextFactory.setCipherComparator(HTTP2Cipher.COMPARATOR);
//        sslContextFactory.setUseCipherSuitesOrder(true);
//        return sslContextFactory;
//    }
//
//    private SslContextFactory getSslContextFactoryFrom(final ServerConnector connector) {
//        return connector.getConnectionFactory(SslConnectionFactory.class).getSslContextFactory();
//    }
//
//    private HttpConfiguration getHttpConfigurationFrom(final ServerConnector connector) {
//        return connector.getConnectionFactory(HttpConnectionFactory.class).getHttpConfiguration();
//    }
//
//    private void overrideExistingServerConnectors(final Server server, final Connector... connectors) {
//        server.setConnectors(connectors);
//    }
//
//    private ConnectionFactory[] createConnectionFactories(final SslContextFactory sslContextFactory,
//                                                          final HttpConfiguration httpConfiguration) {
//        return new ConnectionFactory[] {
//                new SslConnectionFactory(sslContextFactory, "alpn"),
//                new ALPNServerConnectionFactory("h2", "h2-17", "h2-16", "h2-15", "h2-14"),
//                new HTTP2ServerConnectionFactory(httpConfiguration)
//        };
//    }
//}
