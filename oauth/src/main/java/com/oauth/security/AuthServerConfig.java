package com.oauth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import com.oauth.service.CustomTokenEnricher;
import com.oauth.service.CustomUserDetailService;

@EnableAuthorizationServer
@Configuration
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	TokenStore tokenStore;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomTokenEnricher customTokenEnricher;

	@Autowired
	CustomUserDetailService userDetailsService;

	@Value("${oauth.redirectUrl}")
	String redirectUri;

	@Value("${oauth.redirectUrl}")
	String clientId;

	@Value("${oauth.scope}")
	String scope;

	@Value("${oauth.grantType}")
	String grantType;

	@Value("${oauth.accessTokenValidity.inSeconds}")
	String accessTokenValidityInSeconds;

	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		endpoints.tokenStore(tokenStore).authenticationManager(authenticationManager)
				.userDetailsService(userDetailsService).tokenEnhancer(customTokenEnricher);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

		security.tokenKeyAccess("isAnonymous() || hasRole('ROLE_USER')");

	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient(clientId).authorizedGrantTypes(grantType).scopes(scope).redirectUris(redirectUri)
				.accessTokenValiditySeconds(Integer.parseInt(accessTokenValidityInSeconds)).autoApprove(true);
	}

}
