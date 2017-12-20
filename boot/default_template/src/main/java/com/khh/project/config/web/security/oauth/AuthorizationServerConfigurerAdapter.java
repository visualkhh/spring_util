package com.khh.project.config.web.security.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

//http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-security.html

@Configuration
@Order(1)
public class AuthorizationServerConfigurerAdapter extends org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter {


	@Autowired
	private AuthenticationManager authenticationManager;

	//

	@Override
	public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
//		oauthServer.checkTokenAccess("hasRole('ROLE_TRUSTED_CLIENT')");
	}

	@Override
	public void configure(final ClientDetailsServiceConfigurer clients) throws Exception { // @formatter:off

		clients.inMemory()
				.withClient("client")
				.secret("secret")
				.authorizedGrantTypes("password", "client_credentials", "authorization_code", "refresh_token")
//				.authorizedGrantTypes("password")
				.redirectUris("http://localhost:8080")
//				.authorities("ROLE_AUTH")
				.scopes("foo", "read", "write")
				.accessTokenValiditySeconds(3600) // 1 hour
		;
	}


	@Override
	public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
		endpoints.tokenStore(tokenStore()).allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
				.accessTokenConverter(accessTokenConverter()).authenticationManager(authenticationManager);
		// @formatter:off
//		endpoints.authenticationManager(authenticationManager)
//				.pathMapping("/oauth/confirm_access", confirmPath)
//				.pathMapping("/oauth/token", tokenPath)
//				.pathMapping("/oauth/check_token", checkTokenPath)
//				.pathMapping("/oauth/token_key", tokenKeyPath)
//				.pathMapping("/oauth/authorize", authorizePath);
		// @formatter:on
	}

	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		final DefaultTokenServices tokenServices = new DefaultTokenServices();
		tokenServices.setTokenStore(tokenStore());
		return tokenServices;
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//		converter.setSigningKey("123");
		return converter;
	}
}
