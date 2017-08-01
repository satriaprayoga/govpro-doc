package com.govpro.config.security.token;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.govpro.config.GovproProperties;

@Component
public class TokenProvider {

	private final Logger logger=LoggerFactory.getLogger(TokenProvider.class);
	
	private static final String AUTHORITIES_KEY="auth";
	
	private String secretKey;
	
	private long tokenValidityInMilliseconds;
	
	private final GovproProperties govproProperties;
	
	public TokenProvider(GovproProperties govproProperties) {
		this.govproProperties=govproProperties;
	}
	
	
	@PostConstruct
	public void init(){
		this.secretKey=govproProperties.getSecurity().getAuthentication().getJwt().getSecret();
		this.tokenValidityInMilliseconds=govproProperties.getSecurity().getAuthentication().getJwt().getTokenValidityInSeconds();
	}
}
