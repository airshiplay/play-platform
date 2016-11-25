package com.airshiplay.play.security;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.core.annotation.Order;
//import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.RequestContextFilter;

import com.airshiplay.play.core.PlayConstants;
import com.airshiplay.play.core.StaticConfigSupplier;
import com.airshiplay.play.security.captcha.CaptchaConfigBean;

@Order(1)
public class SecurityWebApplicationInitializer implements WebApplicationInitializer {


	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		String[] urlPatterns = StaticConfigSupplier.getConfiguration().getStringArray("captcha.urlPatterns");
		if (urlPatterns == null || urlPatterns.length == 0) {
			urlPatterns = new String[] { "/login", "/forgotPassword" };
		}
		DelegatingFilterProxy captchaFilter = new DelegatingFilterProxy(CaptchaConfigBean.CAPTCHA_FILTER_NAME);
		Dynamic captchaRegistration = servletContext.addFilter(CaptchaConfigBean.CAPTCHA_FILTER_NAME, captchaFilter);
		captchaRegistration.setAsyncSupported(isAsyncSecuritySupported());
		captchaRegistration.addMappingForUrlPatterns(getSecurityDispatcherTypes(), false, urlPatterns);

		Dynamic shiroRegistration=	servletContext.addFilter("shiroFilter", new DelegatingFilterProxy("shiroFilter"));
		shiroRegistration.setAsyncSupported(isAsyncSecuritySupported());
		shiroRegistration.addMappingForUrlPatterns(getSecurityDispatcherTypes(), false, "/*");
		shiroRegistration.setInitParameter("targetFilterLifecycle", "true");
		
		Dynamic holderRegistration = servletContext.addFilter("RequestContextFilter", new RequestContextFilter());
		holderRegistration.setAsyncSupported(isAsyncSecuritySupported());
		holderRegistration.addMappingForUrlPatterns(getSecurityDispatcherTypes(), false, "/*");

		Dynamic encodingRegistration = servletContext.addFilter("CharacterEncodingFilter", new CharacterEncodingFilter(PlayConstants.characterEncoding, true));
		encodingRegistration.setAsyncSupported(isAsyncSecuritySupported());
		encodingRegistration.addMappingForUrlPatterns(getSecurityDispatcherTypes(), false, "/*");
	}

	protected EnumSet<DispatcherType> getSecurityDispatcherTypes() {
		return EnumSet.of(DispatcherType.REQUEST, DispatcherType.ERROR,
				DispatcherType.ASYNC);
	}

	private boolean isAsyncSecuritySupported() {
		return true;
	}

}
