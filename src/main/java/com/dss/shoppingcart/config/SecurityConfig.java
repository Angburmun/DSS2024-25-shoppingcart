package com.dss.shoppingcart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests
        		.requestMatchers("/", "/login", "/products", "/cart/**", "/thank-you", "/h2-console/**").permitAll()
                .requestMatchers("/products/add", "/products/edit/**", "/products/delete/**").hasRole("ADMIN")
                .anyRequest().authenticated())
        	.formLogin(login -> login
        			.loginPage("/login")
        			.defaultSuccessUrl("/products", true)
        			.permitAll())
        	.logout(logout -> logout
        			.logoutUrl("/logout")
        			.logoutSuccessUrl("/login?logout")
        			.permitAll());

        return http.build();
    }
}
