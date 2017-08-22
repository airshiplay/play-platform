package com.airlenet.play.security;

import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContext;

import com.airlenet.play.security.captcha.CaptchaConfigBean;
import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.RequestContextFilter;

import com.airlenet.play.core.PlayConstants;

@Order(1)
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

	@Override
	protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
		super.beforeSpringSecurityFilterChain(servletContext);
		
		DelegatingFilterProxy captchaFilter = new DelegatingFilterProxy(CaptchaConfigBean.CAPTCHA_FILTER_NAME);
		Dynamic captchaRegistration = servletContext.addFilter(CaptchaConfigBean.CAPTCHA_FILTER_NAME, captchaFilter);
		captchaRegistration.setAsyncSupported(isAsyncSecuritySupported());
		captchaRegistration.addMappingForUrlPatterns(getSecurityDispatcherTypes(), false, "/login", "/forgotPassword");

		Dynamic holderRegistration = servletContext.addFilter("RequestContextFilter", new RequestContextFilter());
		holderRegistration.setAsyncSupported(isAsyncSecuritySupported());
		holderRegistration.addMappingForUrlPatterns(getSecurityDispatcherTypes(), false, "/*");

		Dynamic encodingRegistration = servletContext.addFilter("CharacterEncodingFilter", new CharacterEncodingFilter(PlayConstants.characterEncoding, true));
		encodingRegistration.setAsyncSupported(isAsyncSecuritySupported());
		encodingRegistration.addMappingForUrlPatterns(getSecurityDispatcherTypes(), false, "/*");
	}
	
	@Override
	protected void afterSpringSecurityFilterChain(ServletContext servletContext) {
		super.afterSpringSecurityFilterChain(servletContext);

//		DelegatingFilterProxy captchaFilter = new DelegatingFilterProxy(CaptchaConfigBean.CAPTCHA_FILTER_NAME);
//		Dynamic captchaRegistration = servletContext.addFilter(CaptchaConfigBean.CAPTCHA_FILTER_NAME, captchaFilter);
//		captchaRegistration.setAsyncSupported(isAsyncSecuritySupported());
//		captchaRegistration.addMappingForUrlPatterns(getSecurityDispatcherTypes(), false, "/login", "/forgotPassword");
//
//		Dynamic holderRegistration = servletContext.addFilter("RequestContextFilter", new RequestContextFilter());
//		holderRegistration.setAsyncSupported(isAsyncSecuritySupported());
//		holderRegistration.addMappingForUrlPatterns(getSecurityDispatcherTypes(), false, "/*");
//
//		Dynamic encodingRegistration = servletContext.addFilter("CharacterEncodingFilter", new CharacterEncodingFilter(PlayConstants.characterEncoding, true));
//		encodingRegistration.setAsyncSupported(isAsyncSecuritySupported());
//		encodingRegistration.addMappingForUrlPatterns(getSecurityDispatcherTypes(), false, "/*");

	}

}
