package com.airshiplay.play.oauth2.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.oltu.oauth2.common.OAuth;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.beans.factory.InitializingBean;

import com.airshiplay.play.oauth2.shiro.authc.Oauth2Token;

public class OAuth2Filter extends AuthenticatingFilter implements
		InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {

	}

	@Override
	protected AuthenticationToken createToken(ServletRequest request,
			ServletResponse response) throws Exception {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		final String accessToken = getAccessToken(httpRequest);
		Oauth2Token token = new Oauth2Token(accessToken);
		return token;
	}

	private String getAccessToken(HttpServletRequest httpRequest) {
		final String authorization = httpRequest.getHeader("Authorization");
		if (authorization != null) {
			// bearer ab1ade69-d122-4844-ab23-7b109ad977f0
			return authorization.substring(6).trim();
		}
		return httpRequest.getParameter(OAuth.OAUTH_ACCESS_TOKEN);
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) {
		return super.isAccessAllowed(request, response, mappedValue);
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		return executeLogin(request, response);
	}

}
