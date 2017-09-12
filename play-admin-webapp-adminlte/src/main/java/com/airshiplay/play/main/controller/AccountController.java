package com.airshiplay.play.main.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.validation.Valid;

//import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airshiplay.play.main.entity.AdminUserEntity;
import com.airshiplay.play.main.service.UserEntityService;
//import com.airshiplay.play.plugin.oauth.model.OauthPlugin;
//import com.airshiplay.play.plugin.oauth.model.OauthUserEntity;
//import com.airshiplay.play.plugin.oauth.service.OauthPluginService;
//import com.airshiplay.play.plugin.oauth.service.OauthUserService;
import com.airlenet.repo.domain.Result;
import com.airlenet.security.CurrentUser;
import com.airlenet.security.CustomUserDetails;
import com.airlenet.security.PlayPasswordService;
/**
 * @author airlenet
 * @version 2017-09-12
 */
@Controller
@RequestMapping("/center/account")
public class AccountController {

	@Autowired
	UserEntityService userEntityService;

//	@Autowired
//	OauthUserService oauthUserService;
//	@Autowired
//	OauthPluginService oauthPluginService;
	@Autowired
	PlayPasswordService passwordService;

	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String get(Model model) {
		Subject subject = SecurityUtils.getSubject();
		@SuppressWarnings("unchecked")
		AdminUserEntity user = ((CustomUserDetails<Long, AdminUserEntity>) subject.getPrincipal()).getCustomUser();
//		List<OauthUserEntity> oauthUsers = oauthUserService.findByAdminUserId(user.getId());
//		List<OauthPlugin> oauthPlugins = oauthPluginService.getAvailableOauthPlugins();
//		List<String> oauthUserPluginIds = new ArrayList<String>();
//		oauthPlugins.forEach(new Consumer<OauthPlugin>() {
//
//			@Override
//			public void accept(OauthPlugin t) {
//
//				oauthUsers.forEach(new Consumer<OauthUserEntity>() {
//
//					@Override
//					public void accept(OauthUserEntity u) {
//						if (u.getOauthPluginId().equals(t.getId())) {
//							oauthUserPluginIds.add(t.getId());
//						}
//					}
//				});
//			}
//		});
		model.addAttribute("user", user);
//		model.addAttribute("oauthUsers", oauthUsers);
//		model.addAttribute("oauthPlugins", oauthPlugins);
//		model.addAttribute("oauthUserPluginIds", oauthUserPluginIds);
		return "classpath:/admin/account/info";
	}

	@RequestMapping(value = "/password", method = RequestMethod.GET)
	public String getPassword(Model model) {
		return "classpath:/admin/account/password";
	}


}
