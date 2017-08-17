package com.airshiplay.play.main.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airshiplay.play.main.entity.AdminUserEntity;
import com.airshiplay.play.main.service.UserEntityService;
import com.airshiplay.play.plugin.oauth.model.OauthPlugin;
import com.airshiplay.play.plugin.oauth.model.OauthUserEntity;
import com.airshiplay.play.plugin.oauth.service.OauthPluginService;
import com.airshiplay.play.plugin.oauth.service.OauthUserService;
import com.airshiplay.play.repo.domain.Result;
import com.airshiplay.play.security.CurrentUser;
import com.airshiplay.play.security.CustomUserDetails;
import com.airshiplay.play.security.PlayPasswordService;

@Controller
@RequestMapping("/center/account")
public class AccountController {

	@Autowired
	UserEntityService userEntityService;

	@Autowired
	OauthUserService oauthUserService;
	@Autowired
	OauthPluginService oauthPluginService;
	@Autowired
	PlayPasswordService passwordService;

	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String get(Model model) {
		Subject subject = SecurityUtils.getSubject();
		@SuppressWarnings("unchecked")
		AdminUserEntity user = ((CustomUserDetails<Long, AdminUserEntity>) subject.getPrincipal()).getCustomUser();
		List<OauthUserEntity> oauthUsers = oauthUserService.findByAdminUserId(user.getId());
		List<OauthPlugin> oauthPlugins = oauthPluginService.getAvailableOauthPlugins();
		List<String> oauthUserPluginIds = new ArrayList<String>();
		oauthPlugins.forEach(new Consumer<OauthPlugin>() {

			@Override
			public void accept(OauthPlugin t) {

				oauthUsers.forEach(new Consumer<OauthUserEntity>() {

					@Override
					public void accept(OauthUserEntity u) {
						if (u.getOauthPluginId().equals(t.getId())) {
							oauthUserPluginIds.add(t.getId());
						}
					}
				});
			}
		});
		model.addAttribute("user", user);
		model.addAttribute("oauthUsers", oauthUsers);
		model.addAttribute("oauthPlugins", oauthPlugins);
		model.addAttribute("oauthUserPluginIds", oauthUserPluginIds);
		return "/bootstrap/admin/account/info";
	}

	@RequestMapping(value = "/password", method = RequestMethod.GET)
	public String getPassword(Model model) {
		return "/bootstrap/admin/account/password";
	}

	@RequestMapping(value = "/oauth/unbind", method = RequestMethod.POST)
	@ResponseBody
	public Result unBind(@RequestParam String oauthPluginId, @CurrentUser CustomUserDetails<Long, AdminUserEntity> user) {
		oauthUserService.unBindAdminUser(oauthPluginId, user.getId());
		return Result.success();
	}

	@RequestMapping(value = "/password", method = RequestMethod.POST)
	@ResponseBody
	public Result postPassword(@CurrentUser CustomUserDetails<Long, AdminUserEntity> customUser,@RequestParam String password,@RequestParam String newPassword) {
		AdminUserEntity adminUser =customUser.getCustomUser();
		if(StringUtils.isEmpty(password)&&StringUtils.isNotEmpty(adminUser.getPassword())){
			return Result.validateError();
		}
		if(StringUtils.isNotEmpty(password)&&!passwordService.passwordsMatch(password, adminUser.getSalt(), adminUser.getPassword())){
			return Result.validateError();
		}
		if(StringUtils.isNotEmpty(newPassword)){
			adminUser.setPassword(passwordService.encryptPassword(newPassword, adminUser.getSalt()));
		}
		userEntityService.save(adminUser);
		return Result.success();
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result postSave(@ModelAttribute @Valid AdminUserEntity user, BindingResult bindingResult, String avatar, String newPassword) {
		if (bindingResult.hasErrors()) {
			return Result.validateError();
		}
		user.setPhoto(avatar);
		if(StringUtils.isNotEmpty(newPassword)){
			user.setPassword(passwordService.encryptPassword(newPassword, user.getSalt()));
		}
		user = userEntityService.save(user);
		return Result.success();// .addProperties("content", user);
	}
}
