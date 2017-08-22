package com.airshiplay.play.main.security;

import com.airlenet.play.security.CustomUserDetails;
import com.airlenet.play.security.CustomUserDetails.Type;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.springframework.beans.factory.InitializingBean;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class AdminUserFilter extends UserFilter implements InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {

	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		if (isLoginRequest(request, response)) {
			return true;
		} else {
			Subject subject = getSubject(request, response);
			CustomUserDetails<?, ?> object = (CustomUserDetails<?, ?>) subject.getPrincipal();
			return object != null && (object.getType() == Type.Admin || object.getType() == Type.AdminOauth2 || object.getType() == Type.AdminThirdPartyOauth);
		}
	}
}
