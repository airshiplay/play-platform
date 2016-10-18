package com.airshiplay.play.main.controller;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.mfrey.jackson.antpathfilter.AntPathPropertyFilter;

import com.airshiplay.play.core.SpringContext;
import com.airshiplay.play.main.entity.MenuEntity;
import com.airshiplay.play.main.security.PasswordService;
import com.airshiplay.play.main.security.UserCredentialsDetailsService.EntityUserDetails;
import com.airshiplay.play.main.service.MenuEntityService;
import com.airshiplay.play.main.service.SettingEntityService;
import com.airshiplay.play.repo.domain.Result;
import com.airshiplay.play.repo.domain.Tree;
import com.airshiplay.play.security.CustomUserDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

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

	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	private PasswordService passwordService;

	@RequestMapping(value = { "${path.admin}", "${path.admin}/",
			"${path.admin}/index" }, method = RequestMethod.GET)
	public String get(Model model) {
		final Authentication authentication = SecurityContextHolder
				.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		if (principal instanceof CustomUserDetails) {
			try {
				model.addAttribute("currentUser", BeanUtils.getProperty(
						((CustomUserDetails<?, ?>) principal).getCustomUser(),
						"name"));
				model.addAttribute("user",
						((CustomUserDetails<?, ?>) principal).getCustomUser());
			} catch (IllegalAccessException | InvocationTargetException
					| NoSuchMethodException e) {
				e.printStackTrace();
			}
		} else {
//			model.addAttribute("currentUser", "游客");
			return "redirect:/admin/login";
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
			@RequestParam String username, @RequestParam String password) {

		UserDetails userDetails = null;
		try {
			userDetails = userDetailsService.loadUserByUsername(username);
		} catch (UsernameNotFoundException e) {
			logger.error(null, e);
			return Result.validateError();
		}

		if (passwordService.matches(password, userDetails.getPassword())) {

			// //根据userDetails构建新的Authentication,这里使用了
			// //PreAuthenticatedAuthenticationToken当然可以用其他token,如UsernamePasswordAuthenticationToken
			PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(
					userDetails, userDetails.getPassword(),
					userDetails.getAuthorities());
			// //设置authentication中details
			authentication.setDetails(new WebAuthenticationDetails(request));
			//
			// //存放authentication到SecurityContextHolder
			SecurityContextHolder.getContext()
					.setAuthentication(authentication);
			HttpSession session = request.getSession(true);
			// //在session中存放security context,方便同一个session中控制用户的其他操作
			session.setAttribute("SPRING_SECURITY_CONTEXT",
					SecurityContextHolder.getContext());

			return Result.success();
		} else {
			return Result.validateError();
		}

	}

	@RequestMapping(value = { "/center/logout" }, method = RequestMethod.GET)
	public String logout(HttpSession session) {
		// clear session
		session.invalidate();
		// clear valorization
		SecurityContextHolder.getContext().setAuthentication(null);
		SecurityContextHolder.clearContext();
		return "redirect:/admin/login";
	}

	@RequestMapping(value = { "/center/home" }, method = RequestMethod.GET)
	public String getHome(Model model) {
		return "/views/admin/home";
	}

}
