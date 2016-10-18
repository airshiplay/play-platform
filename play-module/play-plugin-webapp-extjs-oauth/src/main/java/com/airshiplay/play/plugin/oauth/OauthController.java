package com.airshiplay.play.plugin.oauth;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.airshiplay.play.main.entity.UserEntity;
import com.airshiplay.play.main.service.UserEntityService;
import com.airshiplay.play.plugin.oauth.model.OauthPlugin;
import com.airshiplay.play.plugin.oauth.model.OauthUserEntity;
import com.airshiplay.play.plugin.oauth.service.OauthPluginService;
import com.airshiplay.play.plugin.oauth.service.OauthUserService;

@Controller
@RequestMapping("/oauth")
public class OauthController {

	@Autowired
	private OauthPluginService oauthService;

	@Autowired
	private OauthUserService oauthUserService;

	@Autowired
	private UserEntityService userEntityService;

	@RequestMapping(value = "/authorization/{oauthPluginId}", method = RequestMethod.GET)
	public String redirectUsersToRequestAccess(@PathVariable("oauthPluginId") String oauthPluginId, RedirectAttributes redirectAttributes) {
		OauthPlugin oauthPlugin = oauthService.getOauthPlugin(oauthPluginId);
		redirectAttributes.addAllAttributes(oauthPlugin.getAuthorizationParameterMap());
		return "redirect:" + oauthPlugin.getAuthorizationUrl();
	}

	@RequestMapping(value = "/api/{oauthPluginId}", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
	public String getAccessTokenAndProcess(@PathVariable("oauthPluginId") String oauthPluginId, String code, HttpServletRequest request) {
		Assert.notNull(oauthPluginId);
		Assert.notNull(code);

		OauthPlugin oauthPlugin = oauthService.getOauthPlugin(oauthPluginId);
		String accessToken = oauthPlugin.getAccessToken(code);
		Assert.notNull(accessToken);

		OauthUserEntity oauthUser = oauthPlugin.getOauthUser(accessToken);
		Assert.notNull(oauthUser);

		if (oauthUser.isNew() || oauthUser.getOwner() == null) {
			UserEntity user = userEntityService.newEntity();
			user.setName(oauthUser.getName());
			user.setPhoto(oauthUser.getAvatarUrl());
			userEntityService.save(user);
			oauthUser.setOwner(user);
			oauthUserService.save(oauthUser);

			// authenticationManager.authenticate(authentication)

			// login(oauthUser, request);
			return "redirect:/center/user/account";
		} else {
			// login(oauthUser, request);
			return "redirect:/center";
		}
	}

	// private void login(OauthUser oauthUser, HttpServletRequest request) {
	// OauthUserToken token = new OauthUserToken(oauthUser,
	// request.getRemoteHost(), true);
	// Subject subject = SecurityUtils.getSubject();
	// subject.login(token);
	// }

}
