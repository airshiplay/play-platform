package com.airshiplay.play.main.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;

import com.airshiplay.play.security.ResultAuthenticationFailureHanlder;
import com.airshiplay.play.security.ResultAuthenticationSuccessHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MainWebSecurityConfigBean extends WebSecurityConfigurerAdapter {

	@Value("${security.skip_auth_urls?:/assets/**,/extjs/**,/requirejs/**,/,/index,/app_info,/captcha**}")
	private String[] skipAuthUrls;

	@Autowired
	private ObjectFactory<ObjectMapper> objectMapper;
	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	private ObjectFactory<MessageSourceAccessor> messageSourceAccessor;

	public MainWebSecurityConfigBean() {
		super(true);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().and().addFilter(new WebAsyncManagerIntegrationFilter()).exceptionHandling().and().headers().and().sessionManagement().and().securityContext().and().requestCache().and().anonymous()
				.and().servletApi().and().logout();

		http.getSharedObject(AuthenticationManagerBuilder.class)//.authenticationProvider(null)
				.authenticationEventPublisher(defaultAuthenticationEventPublisher());

		http.headers().frameOptions().sameOrigin().and().csrf().disable().formLogin().successHandler(resultAuthenticationSuccessHandler()).failureHandler(resultAuthenticationFailureHandler())
				.permitAll().and().authorizeRequests().antMatchers(skipAuthUrls).permitAll().anyRequest().authenticated().and().exceptionHandling()
				.authenticationEntryPoint(new Http403ForbiddenEntryPoint()).and().logout().logoutSuccessUrl("/").permitAll();
	}

@Override
protected void configure(AuthenticationManagerBuilder auth)
		throws Exception {
	auth.userDetailsService(userDetailsService);
	super.configure(auth);
}
	@Bean
	public ResultAuthenticationSuccessHandler resultAuthenticationSuccessHandler() {
		return new ResultAuthenticationSuccessHandler(objectMapper.getObject());
	}

	@Bean
	public ResultAuthenticationFailureHanlder resultAuthenticationFailureHandler() {
		return new ResultAuthenticationFailureHanlder(objectMapper.getObject(), messageSourceAccessor.getObject());
	}

	@Bean
	public DefaultAuthenticationEventPublisher defaultAuthenticationEventPublisher() {
		return new DefaultAuthenticationEventPublisher();
	}
//	@Bean
//	SampleAuthenticationManager SampleAuthenticationManager(){
//		return new SampleAuthenticationManager();
//	}
	
}

class SampleAuthenticationManager implements AuthenticationManager {
	  static final List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();

	  static {
//		AUTHORITIES.add(new GrantedAuthorityImpl("ROLE_USER"));
	  }

	  public Authentication authenticate(Authentication auth) throws AuthenticationException {
	    if (auth.getName().equals(auth.getCredentials())) {
	      return new UsernamePasswordAuthenticationToken(auth.getName(),
	        auth.getCredentials(), AUTHORITIES);
	      }
	      throw new BadCredentialsException("Bad Credentials");
	  }
	}
