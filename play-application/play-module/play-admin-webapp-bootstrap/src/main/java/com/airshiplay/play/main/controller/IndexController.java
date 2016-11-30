package com.airshiplay.play.main.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airshiplay.play.main.entity.AdminUserEntity;
import com.airshiplay.play.main.entity.MenuEntity;
import com.airshiplay.play.main.service.MenuEntityService;
import com.airshiplay.play.main.service.SettingEntityService;
import com.airshiplay.play.plugin.oauth.model.OauthPlugin;
import com.airshiplay.play.plugin.oauth.service.OauthPluginService;
import com.airshiplay.play.repo.domain.Result;
import com.airshiplay.play.repo.domain.Tree;
import com.airshiplay.play.security.CurrentUser;
import com.airshiplay.play.security.CustomUserDetails;
import com.airshiplay.play.security.PlayPasswordService;
import com.airshiplay.play.security.shiro.authc.AdminUserToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.octo.captcha.service.CaptchaService;

@Controller
@RequestMapping
public class IndexController {
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	@Autowired
	private SettingEntityService settingEntityService;

	@Autowired
	private MenuEntityService menuEntityService;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private PlayPasswordService passwordService;
	@Autowired
	private CaptchaService captchaService;
	@Autowired
	OauthPluginService oauthPluginService;
	@RequestMapping(value = { "${path.admin}", "${path.admin}/", "${path.admin}/index" }, method = RequestMethod.GET)
	public String get(Model model, @CurrentUser CustomUserDetails<?, AdminUserEntity> user) {
		AdminUserEntity en = user.getCustomUser();
		model.addAttribute("currentUser", en);

		model.addAttribute("setting", (settingEntityService.get()));
		Tree<MenuEntity> tree = menuEntityService.getAllMenuTree();// menuEntityService.getMenuTreeByUserId(en.getId());
		tree.setIconClsProperty("iconCls");
		tree.setTextProperty("text");
		model.addAttribute("allMenuTree", tree.getRoots());	
		return "/bootstrap/admin/index";
	}

	@RequestMapping(value = { "${path.admin}/login" }, method = RequestMethod.GET)
	public String getLogin(Model model, HttpServletRequest request) {
		List<OauthPlugin> oauthPlugins = oauthPluginService.getAvailableOauthPlugins();
		List<String> oauthPluginIds = new ArrayList<String>();
		oauthPlugins.forEach((node) -> { 
			oauthPluginIds.add(node.getId());
		});
		model.addAttribute("oauthPluginIds", oauthPluginIds);
		return "/bootstrap/admin/login";
	}

	@RequestMapping(value = { "${path.admin}/login" }, method = RequestMethod.POST)
	@ResponseBody
	public Result postLogin(Model model, HttpServletRequest request, @RequestParam String username, @RequestParam String password,
			@RequestParam(defaultValue = "false", required = false) Boolean remember) {
		try {
			AuthenticationToken token = new AdminUserToken(username, "", "", password, remember, request.getRemoteHost());
			SecurityUtils.getSubject().login(token);
		} catch (AuthenticationException e) {
			logger.error("admin", e);
			return Result.validateError();
		} catch (Exception e) {
			logger.error("admin", e);
			return Result.validateError();
		}
		return Result.success();

	}

	@RequestMapping(value = { "/center/logout" }, method = RequestMethod.GET)
	public String logout(HttpSession session) {
		// clear valorization
		SecurityUtils.getSubject().logout();
		// clear session
		// session.invalidate();
		return "redirect:/admin/login";
	}

	@RequestMapping(value = { "/center/home.view" }, method = RequestMethod.GET)
	public String getHome(Model model) {
		return "/bootstrap/admin/home";
	}

}
