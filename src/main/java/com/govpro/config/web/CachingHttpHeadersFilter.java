package com.govpro.config.web;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import com.govpro.config.GovproProperties;

public class CachingHttpHeadersFilter implements Filter{
	
	private final static long LAST_MODIFIED=System.currentTimeMillis();
	
	private long cacheTimeToLive=TimeUnit.DAYS.toMillis(1461L);
	
	private GovproProperties govproProperties;
	
	public CachingHttpHeadersFilter(GovproProperties govproProperties) {
		this.govproProperties=govproProperties;
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse httpServletResponse=(HttpServletResponse)response;
		httpServletResponse.setHeader("Cache-Control", "max-age="+cacheTimeToLive+", public");
		httpServletResponse.setHeader("Pragma", "cache");
		httpServletResponse.setDateHeader("Expires", cacheTimeToLive+System.currentTimeMillis());
		httpServletResponse.setDateHeader("Last-Modified", LAST_MODIFIED);
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		cacheTimeToLive=TimeUnit.DAYS.toMillis(govproProperties.getHttp().getCache().getTimeToLiveInDays());
	}

}
