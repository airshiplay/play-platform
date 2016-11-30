package com.airshiplay.play.plugin.oauth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.airshiplay.play.main.entity.AdminUserEntity;
import com.airshiplay.play.main.entity.MemberUserEntity;
import com.airshiplay.play.main.service.MemberUserEntityService;
import com.airshiplay.play.main.service.UserEntityService;
import com.airshiplay.play.plugin.oauth.model.OauthPlugin;
import com.airshiplay.play.plugin.oauth.model.OauthUserEntity;
import com.airshiplay.play.plugin.oauth.service.OauthPluginService;
import com.airshiplay.play.plugin.oauth.service.OauthUserService;
import com.airshiplay.play.security.CustomUserDetails;
import com.airshiplay.play.security.CustomUserDetails.Type;
import com.airshiplay.play.security.PlayPasswordService;
import com.airshiplay.play.security.shiro.authc.PlayPluginOauthToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	@Autowired
	private MemberUserEntityService memberUserEntityService;

	@Autowired
	private ObjectMapper objectMapper;
	
	@RequestMapping(value = "/authorization/{source}/{oauthPluginId}", method = RequestMethod.GET)
	public String redirectUsersToRequestAccess(@PathVariable("source") String source,
			@PathVariable("oauthPluginId") String oauthPluginId,
			RedirectAttributes redirectAttributes,@RequestParam(required=false) String oauthReturnUrl,HttpSession httpSession) {
		OauthPlugin oauthPlugin = oauthService.getOauthPlugin(oauthPluginId);
		redirectAttributes.addAllAttributes(oauthPlugin
				.getAuthorizationParameterMap(source));
		if(StringUtils.isNotEmpty(oauthReturnUrl)){
			httpSession.setAttribute("oauthReturnUrl", oauthReturnUrl);
		}
		return "redirect:" + oauthPlugin.getAuthorizationUrl() +oauthPlugin.getAuthorizationFrag();
	}

	@RequestMapping(value = "/api/{source}/{oauthPluginId}", method = {
			RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
	public String getAccessTokenAndProcess(@PathVariable("source") String source,
			@PathVariable("oauthPluginId") String oauthPluginId, String code,
			HttpServletRequest request,HttpSession session) {
		Assert.notNull(oauthPluginId);
		Assert.notNull(code);

		OauthPlugin oauthPlugin = oauthService.getOauthPlugin(oauthPluginId);
		String accessToken = oauthPlugin.getAccessToken(code,source);
		Assert.notNull(accessToken);

		OauthUserEntity oauthUser = oauthPlugin.getOauthUser(accessToken);
		Assert.notNull(oauthUser);

		if("admin".equals(source)){//管理员只能绑定到第三方账号后，才能使用第三方账号登录；否则不能登录。
			if (oauthUser.isNew() || oauthUser.getAdminOwner() == null) {
				Subject subject = SecurityUtils.getSubject();
				if(subject==null||subject.getPrincipal()==null||!(subject.getPrincipal() instanceof CustomUserDetails<?, ?>)){
					return "redirect:" + adminHomeUrl;
				}
				CustomUserDetails<?, ?> customUserDetails= (CustomUserDetails<?, ?>) subject.getPrincipal();
				
				if(customUserDetails.getType()!=Type.Admin &&customUserDetails.getType()!=Type.AdminOauth2){
					return "redirect:" + adminHomeUrl;
				}
				AdminUserEntity user = (AdminUserEntity) customUserDetails.getCustomUser();				
				if(StringUtils.isEmpty(user.getNickname()))
					user.setNickname(oauthUser.getName());
				if(StringUtils.isEmpty(user.getPhoto()))
					user.setPhoto(oauthUser.getAvatarUrl());				
				userEntityService.save(user);
				oauthUser.setAdminOwner(user);
				oauthUserService.save(oauthUser);
				return "redirect:" + adminHomeUrl + "#page/center/account/info";
			} else {
				loginAdmin(oauthUser, request);
				return "redirect:" + adminHomeUrl;
			}
		}else if("member".equals(source)){
			if (oauthUser.isNew() || oauthUser.getAdminOwner() == null) {
				MemberUserEntity user = memberUserEntityService.newEntity();
				user.setNickname(StringUtils.isEmpty(oauthUser.getName())?(oauthPluginId+RandomStringUtils.randomNumeric(4)):oauthUser.getName());
				user.setPhoto(oauthUser.getAvatarUrl());
				user.setSalt(passwordService.generatorSalt());
				memberUserEntityService.save(user);
				oauthUser.setMemberOwner(user);
				oauthUserService.save(oauthUser);
				loginMember(oauthUser, request);
				return "redirect:" + session.getAttribute("oauthReturnUrl");
			} else {
				loginMember(oauthUser, request);
				return "redirect:" + session.getAttribute("oauthReturnUrl");
			}
		}else{
			if(oauthUser.isNew() ){
				oauthUserService.save(oauthUser);
			}
			session.setAttribute("oauthUser", oauthUser);
			try {
				session.setAttribute("oauthUserJson", objectMapper.writeValueAsString(oauthUser));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return "redirect:"+session.getAttribute("oauthReturnUrl");
		}

	}

	private void loginAdmin(OauthUserEntity oauthUser, HttpServletRequest request) {
		AdminUserEntity userEntity = oauthUser.getAdminOwner();
		PlayPluginOauthToken<CustomUserDetails<?,?>> token = new PlayPluginOauthToken<CustomUserDetails<?,?>>(
				new AdminOauthUserDetails(userEntity.getId(),
						userEntity.getUsername(),userEntity.getNickname(), userEntity.getPassword(),
						userEntity.getSalt(), userEntity.isEnabled(),
						!userEntity.isAccountExpired(),
						!userEntity.isCredentialsExpired(),
						!userEntity.isLocked()),request.getRemoteHost());
		Subject subject = SecurityUtils.getSubject();
		subject.login(token);
	}
	private void loginMember(OauthUserEntity oauthUser, HttpServletRequest request) {
		MemberUserEntity userEntity = oauthUser.getMemberOwner();
		PlayPluginOauthToken<CustomUserDetails<?,?>> token = new PlayPluginOauthToken<CustomUserDetails<?,?>>(
				new AdminOauthUserDetails(userEntity.getId(),
						userEntity.getUsername(),userEntity.getNickname(), userEntity.getPassword(),
						userEntity.getSalt(), userEntity.isEnabled(),
						!userEntity.isAccountExpired(),
						!userEntity.isCredentialsExpired(),
						!userEntity.isLocked()),request.getRemoteHost());
		Subject subject = SecurityUtils.getSubject();
		subject.login(token);
	}
	public class AdminOauthUserDetails extends CustomUserDetails<Long, AdminUserEntity> {
		
		public AdminOauthUserDetails(Long id, String username,String realname, String password,
				String credentialsSalt, boolean enabled,
				boolean accountNonExpired, boolean credentialsNonExpired,
				boolean accountNonLocked) {
			super(id,Type.AdminThirdPartyOauth, username, realname,password, credentialsSalt, enabled,
					accountNonExpired, credentialsNonExpired, accountNonLocked);
		}

		private static final long serialVersionUID = 8220061317304759492L;

		@Override
		public AdminUserEntity getCustomUser() {
			return userEntityService.findOne(getId());
		}
	}
	
	public class MemberOauthUserDetails extends CustomUserDetails<Long, MemberUserEntity> {
		
		public MemberOauthUserDetails(Long id, String username,String realname, String password,
				String credentialsSalt, boolean enabled,
				boolean accountNonExpired, boolean credentialsNonExpired,
				boolean accountNonLocked) {
			super(id,Type.MemberThirdPartyOauth, username, realname,password, credentialsSalt, enabled,
					accountNonExpired, credentialsNonExpired, accountNonLocked);
		}

		private static final long serialVersionUID = 8220061317304759492L;

		@Override
		public MemberUserEntity getCustomUser() {
			return memberUserEntityService.findOne(getId());
		}
	}
}
