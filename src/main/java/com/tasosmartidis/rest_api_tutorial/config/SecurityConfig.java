package com.tasosmartidis.rest_api_tutorial.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration 
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {		
		auth
			.inMemoryAuthentication()
				.withUser("tasos").password("tasos").roles("USER","ADMIN")
				.and()
				.withUser("guest").password("guest").roles("USER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {		
		http
			.httpBasic()
			.and()
			.authorizeRequests() 
			.antMatchers(HttpMethod.DELETE).hasRole("ADMIN")
			.antMatchers(HttpMethod.GET).hasAnyRole("USER","ADMIN")
			.antMatchers(HttpMethod.POST).hasAnyRole("USER","ADMIN")
			.antMatchers(HttpMethod.PUT).hasAnyRole("USER","ADMIN")
			.and()
			.requiresChannel()
			.antMatchers("/service").requiresSecure()
			.and()
			.csrf().disable();
			
	}
}
