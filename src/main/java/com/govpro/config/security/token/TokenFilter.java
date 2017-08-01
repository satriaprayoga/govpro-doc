package com.govpro.config.security.token;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import com.govpro.security.AuthoritiesConstant;

public class TokenFilter extends GenericFilterBean{
	
	private TokenProvider tokenProvider;
	
	public TokenFilter(TokenProvider tokenProvider) {
		this.tokenProvider=tokenProvider;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest=(HttpServletRequest)request;
		String token=resolveToken(httpServletRequest);
		if(StringUtils.hasText(token) && this.tokenProvider.validateToken(token)){
			Authentication authentication=this.tokenProvider.getAuthentication(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		chain.doFilter(request, response);
	}
	
	private String resolveToken(HttpServletRequest request){
		String bearerToken=request.getHeader(AuthoritiesConstant.AUTHORIZATION_HEADER);
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

}
