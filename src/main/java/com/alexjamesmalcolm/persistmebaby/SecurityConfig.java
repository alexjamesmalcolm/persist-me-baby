package com.alexjamesmalcolm.persistmebaby;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests() //
				.antMatchers("/login/oauth2") //
				.permitAll() //
				.anyRequest() //
				.authenticated() //
				.and() //
				.oauth2Login() //
				.loginPage("/login/oauth2");
	}
}
