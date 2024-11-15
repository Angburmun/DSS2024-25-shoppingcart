package com.dss.shoppingcart.config;
import java.util.Date;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
	@Bean
	Long getId() { return Long.valueOf(0); }

	@Bean
    @Qualifier("summary")
	String getSummary() { return "Spring: prueba de Inyeccion de Dependencias"; }

	@Bean
    @Qualifier("description")
	String getDescription() { return "Spring: prueba de Inyeccion de Dependencias y todo lo demas"; }

	@Bean
	Boolean isDone() { return Boolean.FALSE; }

	@Bean
	Date getDueDate() { return new Date(); }

}
