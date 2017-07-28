package com.govpro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;

@Configuration
public class JsonConfiguration {

	@Bean
	public Hibernate5Module hibernate5Module(){
		return new Hibernate5Module();
	}
	
	@Bean
	public AfterburnerModule afterburnerModule(){
		return new AfterburnerModule();
	}
}
