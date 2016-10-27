package com.airshiplay.play.main.controller;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.web.authentication.WebAuthenticationDetails;
//import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airshiplay.play.main.entity.MenuEntity;
import com.airshiplay.play.main.entity.UserEntity;
import com.airshiplay.play.main.security.PasswordService;
//import com.airshiplay.play.main.security.UserCredentialsDetailsService.EntityUserDetails;
import com.airshiplay.play.main.service.MenuEntityService;
import com.airshiplay.play.main.service.SettingEntityService;
import com.airshiplay.play.repo.domain.Result;
import com.airshiplay.play.repo.domain.Tree;
import com.airshiplay.play.security.CurrentUser;
import com.airshiplay.play.security.CustomUserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.octo.captcha.service.CaptchaService;
//import com.airshiplay.play.security.CustomUserDetails;

@Controller
@RequestMapping
public class IndexController {
	private static final Logger logger = LoggerFactory
			.getLogger(IndexController.class);
	@Autowired
	private SettingEntityService settingEntityService;

	@Autowired
	private MenuEntityService menuEntityService;

	@Autowired
	private ObjectMapper objectMapper;

//	@Autowired
//	UserDetailsService userDetailsService;
	@Autowired
	private PasswordService passwordService;
	@Autowired
	private  CaptchaService captchaService;
	
	@RequiresAuthentication
	@RequestMapping(value = { "${path.admin}", "${path.admin}/",
			"${path.admin}/index" }, method = RequestMethod.GET)
	public String get(Model model,@CurrentUser CustomUserDetails<?,UserEntity> user) {
		try {
		UserEntity en=	user.getCustomUser();
			model.addAttribute("currentUser",
					BeanUtils.getProperty((user).getCustomUser(), "name"));
			model.addAttribute("user", (user).getCustomUser());
		} catch (IllegalAccessException | InvocationTargetException
				| NoSuchMethodException e) {
			e.printStackTrace();
		}
//		ObjectWriter objectWriter = objectMapper
//				.writer(new SimpleFilterProvider().addFilter("antPathFilter",
//						new AntPathPropertyFilter(new String[] { "*", "*.id",
//								"*.name" })));
		model.addAttribute("setting", (settingEntityService.get()));
		Tree<MenuEntity> tree = menuEntityService.findTree(null);
		tree.setIconClsProperty("iconCls");
		tree.setTextProperty("text");
		model.addAttribute("allMenuTree", tree.getRoots());	
		return "/views/admin/index";
	}

	@RequestMapping(value = { "${path.admin}/login" }, method = RequestMethod.GET)
	public String getLogin(Model model) {
		return "/views/admin/login";
	}

	@RequestMapping(value = { "${path.admin}/login" }, method = RequestMethod.POST)
	@ResponseBody
	public Result postLogin(Model model, HttpServletRequest request,
			@RequestParam String username, @RequestParam String password,@RequestParam String captcha) {


		if(!captchaService.validateResponseForID(request.getSession().getId(), captcha)){;
			return Result.captchaError();
		}
		
		try {
			AuthenticationToken token = new UsernamePasswordToken(username, password, true, request.getRemoteHost());
			SecurityUtils.getSubject().login(token);
		} catch (AuthenticationException e) {
			logger.error("", e);
			return Result.validateError();
		} catch (Exception e) {
			logger.error("", e);
			return Result.validateError();
		}
		return Result.success();

	}

	@RequestMapping(value = { "/center/logout" }, method = RequestMethod.GET)
	public String logout(HttpSession session) {		
		// clear valorization
		SecurityUtils.getSubject().logout();
		// clear session
		//session.invalidate();
		return "redirect:/admin/login";
	}

	@RequestMapping(value = { "/center/home" }, method = RequestMethod.GET)
	public String getHome(Model model) {
		return "/views/admin/home";
	}

}
