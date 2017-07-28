package com.govpro.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.core.env.Environment;

public class GovproAppProfile {

	private static final String SPRING_DEFAULT="spring.profiles.default";
	
	private GovproAppProfile(){}
	
	public static void addDefaultProvile(SpringApplication app){
		Map<String, Object> profiles=new HashMap<>();
		
		profiles.put(SPRING_DEFAULT, GovproConstant.PROFILE_DEVELOPMENT);
		app.setDefaultProperties(profiles);
	}
	
	public static String [] getActiveProfiles(Environment env){
		String [] profiles=env.getActiveProfiles();
		if(profiles.length==0){
			return env.getDefaultProfiles();
		}
		return profiles;
	}
}
