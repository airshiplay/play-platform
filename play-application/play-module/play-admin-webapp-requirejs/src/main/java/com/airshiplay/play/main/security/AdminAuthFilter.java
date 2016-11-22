package com.airshiplay.play.main.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.springframework.beans.factory.InitializingBean;

import com.airshiplay.play.main.entity.UserEntity;
import com.airshiplay.play.security.CustomUserDetails;

public class AdminAuthFilter extends UserFilter implements InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {

	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		if (isLoginRequest(request, response)) {
			return true;
		} else {
			Subject subject = getSubject(request, response);
			 @SuppressWarnings("unchecked")
			CustomUserDetails<?, UserEntity>  object=	( CustomUserDetails<?, UserEntity>)subject.getPrincipal();
			return subject.getPrincipal() != null;
		}
	}
}
