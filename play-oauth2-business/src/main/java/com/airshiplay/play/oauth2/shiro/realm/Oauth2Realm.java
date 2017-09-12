package com.airshiplay.play.oauth2.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.airshiplay.play.main.entity.AdminUserEntity;
import com.airshiplay.play.oauth2.entity.AccessTokenEntity;
import com.airshiplay.play.oauth2.service.AccessTokenEntityService;
import com.airshiplay.play.oauth2.shiro.Oauth2UserDetails;
import com.airshiplay.play.oauth2.shiro.authc.TokenExpiredAccountException;
import com.airshiplay.play.oauth2.shiro.authc.Oauth2AuthenticationInfo;
import com.airshiplay.play.oauth2.shiro.authc.Oauth2Token;

public class Oauth2Realm extends AuthorizingRealm {
	@Autowired
	AccessTokenEntityService accessTokenEntityService;

	public Oauth2Realm() {
		super();
		setAuthenticationTokenClass(Oauth2Token.class);
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		Oauth2Token oauth2Token = (Oauth2Token) token;
		AccessTokenEntity entity = accessTokenEntityService
				.findOneByTokenId(oauth2Token.getAccessToken());
		if(entity == null) {
            throw new UnknownAccountException();//没找到帐号
        }
		if(entity.tokenExpired()){
			 throw new TokenExpiredAccountException();//没找到帐号
		}
		AdminUserEntity userEntity =entity.getUser();
		if(userEntity==null){
			throw new UnknownAccountException();//没找到帐号
		}
		Oauth2UserDetails userDetails=	new Oauth2UserDetails(userEntity.getId(), userEntity);
		oauth2Token.setCustomUserDetails(userDetails);
   
        return  new Oauth2AuthenticationInfo();
	}

}
