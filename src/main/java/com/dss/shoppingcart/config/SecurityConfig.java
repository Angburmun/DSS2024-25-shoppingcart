package com.dss.shoppingcart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(authorize -> authorize
				.requestMatchers(new MvcRequestMatcher(null, "/")).permitAll()
				.requestMatchers(new MvcRequestMatcher(null, "/login")).permitAll()
				.requestMatchers(new MvcRequestMatcher(null, "/products")).permitAll()
				.requestMatchers(new MvcRequestMatcher(null, "/cart/**")).permitAll()
				.requestMatchers(new MvcRequestMatcher(null, "/admin/**")).hasRole("ADMIN")
				.anyRequest().authenticated()
			)
			.formLogin(Customizer.withDefaults())
			
			.httpBasic(Customizer.withDefaults())
			
			.logout(logout -> logout
				.logoutUrl("/logout")
				.logoutSuccessUrl("/")
			);
		http.csrf().disable();
		return http.build();
	}
}
