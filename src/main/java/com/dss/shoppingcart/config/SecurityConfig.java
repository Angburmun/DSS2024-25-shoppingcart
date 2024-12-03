package com.dss.shoppingcart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests
        		.requestMatchers("/**").permitAll()
        		);
                
        // MIRAR MÉTODO SETFILTERCHAIN
        return http.build();
    }
    
    @Bean
    WebSecurityCustomizer ignoringCustomizer() { return (web) -> web.ignoring().requestMatchers("/h2-console/**"); }
}
