package com.govpro.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class UnauthorizedEntryPoint implements AuthenticationEntryPoint{
	
	private final Logger log=LoggerFactory.getLogger(UnauthorizedEntryPoint.class);

	@Override
	public void commence(HttpServletRequest arg0, HttpServletResponse arg1, AuthenticationException arg2)
			throws IOException, ServletException {
		log.debug("Pre-authentication access denied");
		arg1.sendError(HttpServletResponse.SC_UNAUTHORIZED);
	}

}
