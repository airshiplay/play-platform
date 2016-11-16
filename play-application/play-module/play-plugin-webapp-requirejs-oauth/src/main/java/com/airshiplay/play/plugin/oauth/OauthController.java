package com.airshiplay.play.plugin.oauth;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.airshiplay.play.security.CustomUserDetails;
import com.airshiplay.play.security.PlayPasswordService;
import com.airshiplay.play.security.shiro.authc.PlayPluginOauthToken;

@Controller
@RequestMapping("/oauth")
public class OauthController {
	@Value("${path.admin?:/admin}")
	private String adminHomeUrl;
	@Autowired
	private OauthPluginService oauthService;

	@Autowired
	private OauthUserService oauthUserService;

	@Autowired
	private PlayPasswordService passwordService;
	
	@Autowired
	private UserEntityService userEntityService;

	@RequestMapping(value = "/authorization/{oauthPluginId}", method = RequestMethod.GET)
	public String redirectUsersToRequestAccess(
			@PathVariable("oauthPluginId") String oauthPluginId,
			RedirectAttributes redirectAttributes) {
		OauthPlugin oauthPlugin = oauthService.getOauthPlugin(oauthPluginId);
		redirectAttributes.addAllAttributes(oauthPlugin
				.getAuthorizationParameterMap());
		return "redirect:" + oauthPlugin.getAuthorizationUrl() +oauthPlugin.getAuthorizationFrag();
	}

	@RequestMapping(value = "/api/{oauthPluginId}", method = {
			RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
	public String getAccessTokenAndProcess(
			@PathVariable("oauthPluginId") String oauthPluginId, String code,
			HttpServletRequest request) {
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
			user.setSalt(passwordService.generatorSalt());
			user.setRealname("");
			userEntityService.save(user);
			oauthUser.setOwner(user);
			oauthUserService.save(oauthUser);

			login(oauthUser, request);
			return "redirect:" + adminHomeUrl + "#page/center/account/info";
		} else {
			login(oauthUser, request);
			return "redirect:" + adminHomeUrl;
		}
	}

	private void login(OauthUserEntity oauthUser, HttpServletRequest request) {
		UserEntity userEntity = oauthUser.getOwner();
		PlayPluginOauthToken<CustomUserDetails<?,?>> token = new PlayPluginOauthToken<CustomUserDetails<?,?>>(
				new OauthUserDetails(userEntity.getId(),
						userEntity.getUsername(),userEntity.getRealname(), userEntity.getPassword(),
						userEntity.getSalt(), userEntity.isEnabled(),
						!userEntity.isAccountExpired(),
						!userEntity.isCredentialsExpired(),
						!userEntity.isLocked()));
		Subject subject = SecurityUtils.getSubject();
		subject.login(token);
	}

	public class OauthUserDetails extends CustomUserDetails<Long, UserEntity> {
		
		public OauthUserDetails(Long id, String username,String realname, String password,
				String credentialsSalt, boolean enabled,
				boolean accountNonExpired, boolean credentialsNonExpired,
				boolean accountNonLocked) {
			super(id, username, realname,password, credentialsSalt, enabled,
					accountNonExpired, credentialsNonExpired, accountNonLocked);
		}

		private static final long serialVersionUID = 8220061317304759492L;

		@Override
		public UserEntity getCustomUser() {
			return userEntityService.findOne(getId());
		}
	}
}
