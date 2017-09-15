package com.airshiplay.play.main.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.airlenet.plugin.core.Plugin;
import com.airlenet.plugin.core.PluginService;
import com.airshiplay.play.plugin.oauth.model.OauthPlugin;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airshiplay.play.main.api.LogService;
import com.airshiplay.play.main.api.LogService.LogLevel;
import com.airshiplay.play.main.api.LogService.OperateType;
import com.airshiplay.play.main.entity.AdminUserEntity;
import com.airshiplay.play.main.entity.MenuEntity;
import com.airshiplay.play.main.service.MenuEntityService;
import com.airshiplay.play.main.service.SettingEntityService;
//import com.airshiplay.play.plugin.oauth.model.OauthPlugin;
//import com.airshiplay.play.plugin.oauth.service.OauthPluginService;
import com.airlenet.repo.domain.Result;
import com.airlenet.repo.domain.Tree;
import com.airlenet.security.CurrentUser;
import com.airlenet.security.CustomUserDetails;
import com.airlenet.security.PlayPasswordService;
import com.airlenet.security.shiro.authc.AdminUserToken;
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
	PluginService pluginService;

	@Autowired
	private LogService logService;

	@Value("${path.admin}")
	private String adminPath;
	@RequestMapping(value = { "${path.admin}", "${path.admin}/", "${path.admin}/index" }, method = RequestMethod.GET)
	public String get(Model model, @CurrentUser CustomUserDetails<?, AdminUserEntity> user) {
		logService.addLog(OperateType.VIEW, LogLevel.INFO, "进入管理平台");
		AdminUserEntity en = user.getCustomUser();
		model.addAttribute("currentUser", en);
        model.addAttribute("adminPath",adminPath);
		model.addAttribute("setting", (settingEntityService.get()));
		Tree<MenuEntity> tree = menuEntityService.getMenuTreeByUserId(en.getId());
		tree.setIconClsProperty("iconCls");
		tree.setTextProperty("text");
		model.addAttribute("allMenuTree", tree.getRoots());
		return "classpath:/admin/index";
	}

	@RequestMapping(value = { "${path.admin}/login" }, method = RequestMethod.GET)
	public String getLogin(Model model, HttpServletRequest request) {
        try {
            Class<Plugin> oauthPluginCls =  ( Class<Plugin>) Class.forName("com.airshiplay.play.plugin.oauth.model.OauthPlugin");
            List<Plugin> oauthPlugins = pluginService.getEnabledPlugins(oauthPluginCls);
            List<String> oauthPluginIds = new ArrayList<String>();
            model.addAttribute("oauthPlugins", oauthPlugins);
        } catch (ClassNotFoundException e) {
            logger.warn("not support oauth login");
        }
		model.addAttribute("adminPath",adminPath);
		return "classpath:/admin/login";
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
			logService.addLog(OperateType.LOGIN, LogLevel.ERROR, "登录失败,用户名:" + username, e);
			return Result.validateError();
		} catch (Exception e) {
			logger.error("admin", e);
			logService.addLog(OperateType.LOGIN, LogLevel.ERROR, "登录失败,用户名:" + username, e);
			return Result.validateError();
		}
		logService.addLog(OperateType.LOGIN, LogLevel.INFO, "登录成功");
		return Result.success();

	}

	@RequestMapping(value = { "/center/logout" }, method = RequestMethod.GET)
	public String logout(HttpSession session) {
		// clear valorization
		SecurityUtils.getSubject().logout();
		// clear session
		// session.invalidate();
		return "redirect:classpath:/admin/login";
	}

	@RequestMapping(value = { "/center/home.view" }, method = RequestMethod.GET)
	public String getHome(Model model) {
		return "classpath:/admin/home";
	}

}
