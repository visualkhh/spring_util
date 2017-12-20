package com.khh.api.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;
import java.security.KeyPair;

//http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-security.html

@Configuration
public class AuthorizationServerConfigurerAdapter extends org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter {


	@Autowired
	DataSource dataSource;

	@Autowired//http://stackoverflow.com/questions/28254519/spring-oauth2-authorization-server
	private AuthenticationManager authenticationManager;    // authorizedGrantTypes Password방식으로 쓰기위해서.

	@Override
	public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {

		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
//		oauthServer.checkTokenAccess("hasRole('ROLE_TRUSTED_CLIENT')");
	}

	@Override
	public void configure(final ClientDetailsServiceConfigurer clients) throws Exception { // @formatter:off
//		clients.inMemory()
//				.withClient("client")
//				.secret("secret")
//				.authorizedGrantTypes("password", "client_credentials", "authorization_code", "refresh_token")
////				.authorizedGrantTypes("password")
//				.redirectUris("http://localhost:8080")
////				.authorities("ROLE_AUTH")
//				.scopes("foo", "read", "write")
//				.accessTokenValiditySeconds(3600) // 1 hour
//		;
		clients.inMemory()
				.withClient("rpwd")
				.secret("pwd")
				.authorizedGrantTypes("password", "refresh_token")
//				.authorizedGrantTypes("password")
//				.authorities("ROLE_AUTH")
				.scopes("foo", "read", "write")
				.accessTokenValiditySeconds(60 * 60) // 1 hour (초 단위)
		;
	}


	@Override
	public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
		//jdbc
//		endpoints.tokenStore(jdbcTokenStore(dataSource)).allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST).authenticationManager(authenticationManager);

		//jwt
		endpoints.tokenStore(tokenStore()).allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
				.accessTokenConverter(accessTokenConverter()).authenticationManager(authenticationManager);
//

		// @formatter:off
//		endpoints.authenticationManager(authenticationManager)
//				.pathMapping("/oauth/confirm_access", confirmPath)
//				.pathMapping("/oauth/token", tokenPath)
//				.pathMapping("/oauth/check_token", checkTokenPath)
//				.pathMapping("/oauth/token_key", tokenKeyPath)
//				.pathMapping("/oauth/authorize", authorizePath);
		// @formatter:on
	}

//	@Bean
//	@Primary
//	public DefaultTokenServices tokenServices() {
//		final DefaultTokenServices tokenServices = new DefaultTokenServices();
//		tokenServices.setTokenStore(tokenStore());
//		return tokenServices;
//	}


	//JdbcTokenSotre
//	@Bean
//	public TokenStore jdbcTokenStore(DataSource dataSource) {
//		return new JdbcTokenStore(dataSource);
//	}

	//jwtTokenSotre
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
//		final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();		converter.setSigningKey("123");
//		return converter;
		/*http://peyton.tk/index.php/post/980
		 */
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		KeyStoreKeyFactory ksf = new KeyStoreKeyFactory(new ClassPathResource("server.jks"), "qweqwe".toCharArray());
		KeyPair keyPair = ksf.getKeyPair("hello", "zaqwsx".toCharArray());
		converter.setKeyPair(keyPair);
		return converter;
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		return new AuthenticationProvider();
	}

}
