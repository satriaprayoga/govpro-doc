package com.govpro.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public final class SecurityUtils {

	private SecurityUtils(){}
	

	public static final String getCurrentUserLogin(){
		SecurityContext context=SecurityContextHolder.getContext();
		Authentication authentication=context.getAuthentication();
		String username=null;
		if(authentication!=null){
			if(authentication.getPrincipal() instanceof UserDetails){
				UserDetails userDetails=(UserDetails)authentication.getPrincipal();
				username=userDetails.getUsername();
			}else if(authentication.getPrincipal() instanceof String){
				username=(String)authentication.getPrincipal();
			}
		}
		return username;
	}
	
	public static final String getCurrentCredentials(){
		SecurityContext context=SecurityContextHolder.getContext();
		Authentication authentication=context.getAuthentication();
		if(authentication!=null && authentication.getCredentials() instanceof String){
			return (String)authentication.getCredentials();
		}
		return null;
	}
	
	public static boolean isAuthenticated(){
		SecurityContext context=SecurityContextHolder.getContext();
		Authentication authentication=context.getAuthentication();
		if(authentication!=null){
			 return authentication.getAuthorities().stream().noneMatch(
						grantedAuthority->grantedAuthority.getAuthority().equals(AuthoritiesConstant.ANONYMOUS)
					);
		}
		return false;
	}
	
	public static boolean isUserInRole(String authority){
		SecurityContext context=SecurityContextHolder.getContext();
		Authentication authentication=context.getAuthentication();
		if(authentication!=null){
			 return authentication.getAuthorities().stream().anyMatch(
						grantedAuthority->grantedAuthority.getAuthority().equals(authority)
					);
		}
		return false;
	}
	
}
