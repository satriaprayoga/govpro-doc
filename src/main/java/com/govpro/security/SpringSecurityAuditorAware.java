package com.govpro.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class SpringSecurityAuditorAware implements AuditorAware<String>{

	@Override
	public String getCurrentAuditor() {
		String username=SecurityUtils.getCurrentUserLogin();
		return username!=null? username:AuthoritiesConstant.SYSTEM_ACCOUNT;
	}

}
