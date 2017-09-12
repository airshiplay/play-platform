package com.airshiplay.play.oauth2.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 供授权调用接口
 * 
 * @author lig
 *
 */
@Controller
@RequestMapping("/authz/oauth2")
public class OauthTokenController {

	@RequestMapping("token")
	public void authorize(HttpServletRequest request,
			HttpServletResponse response) throws OAuthSystemException {
		
	}
}
