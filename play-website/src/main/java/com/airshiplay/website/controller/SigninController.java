package com.airshiplay.website.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airlenet.repo.domain.Result;
import com.airlenet.security.shiro.authc.MemberUserToken;

@Controller
public class SigninController extends BaseController {
	@RequestMapping("/signin")
	public String login() {
		return "/signin";
	}

	@RequestMapping(value = { "/signin" }, method = RequestMethod.POST)
	@ResponseBody
	public Result postLogin(Model model, HttpServletRequest request, @RequestParam String username, @RequestParam String password,
			@RequestParam(defaultValue = "false", required = false) Boolean remember) {
		try {
			AuthenticationToken token = new MemberUserToken(username, "", "", password, remember, request.getRemoteHost());
			SecurityUtils.getSubject().login(token);
		} catch (AuthenticationException e) {

			return Result.validateError();
		} catch (Exception e) {

			return Result.validateError();
		}
		return Result.success();

	}
}
