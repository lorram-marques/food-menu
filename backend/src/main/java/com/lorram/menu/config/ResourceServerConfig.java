package com.lorram.menu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Autowired
	private JwtTokenStore tokenStore;
	
	private static final String[] PUBLIC_AUTH = { "/oauth/token", "/h2-console/**" };
	
	private static final String[] PUBLIC_GET = { "/menu", "/menu/**/reviews" };
	
	private static final String[] OPERATOR = { "/menu/**/reviews" };
	
	private static final String[] ADMIN = { "/users/**", "/menu/reviews", "/menu/**" };	
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests(requests -> requests
                .antMatchers(PUBLIC_AUTH).permitAll()
                .antMatchers(HttpMethod.GET, PUBLIC_GET).permitAll()
                .antMatchers(HttpMethod.POST, OPERATOR).hasRole("OPERATOR")
                .antMatchers(ADMIN).hasRole("ADMIN")
                .anyRequest().authenticated());
	}	
}