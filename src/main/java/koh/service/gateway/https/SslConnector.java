package koh.service.gateway.https;

import org.eclipse.jetty.server.*;
import org.eclipse.jetty.util.ssl.SslContextFactory;

import java.util.List;

public class SslConnector extends ServerConnector {
    SslConnector(Server server, int port, String keyStoreType, String keyStorePath, String keyStorePassword) {
        super(server);

        // Make an SslContextFactory with PKCS12 keystore configuration
        SslContextFactory.Server sslContextFactory = new SslContextFactory.Server();
        sslContextFactory.setKeyStoreType(keyStoreType);
        sslContextFactory.setKeyStorePath(keyStorePath);
        sslContextFactory.setKeyStorePassword(keyStorePassword);

        // Disable SNI checks for easier testing (replicates Jetty 9.x behavior)
        HttpConfiguration httpsConfig = new HttpConfiguration();
        SecureRequestCustomizer customizer = new SecureRequestCustomizer();
        customizer.setSniHostCheck(false);
        httpsConfig.addCustomizer(customizer);

        HttpConnectionFactory httpContextFactory = new HttpConnectionFactory(httpsConfig);

        ConnectionFactory[] connectionFactories = AbstractConnectionFactory.getFactories(
                sslContextFactory,
                httpContextFactory
        );

        this.setPort(port);
        this.setConnectionFactories(List.of(connectionFactories));
    }
}
