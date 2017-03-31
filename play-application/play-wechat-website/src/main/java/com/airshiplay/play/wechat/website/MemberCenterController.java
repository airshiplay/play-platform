package com.airshiplay.play.wechat.website;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airshiplay.play.repo.domain.Result;
import com.airshiplay.play.security.shiro.authc.MemberUserToken;

@Controller
@RequestMapping("/member")
public class MemberCenterController {

	@RequiresUser
	@RequiresRoles("user")
	@RequestMapping("/center")
	public String memberCenter() {
		return "member/center";
	}

	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String memberLogin() {
		return "member/login";
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Result postMemberLogin(Model model, HttpServletRequest request, @RequestParam String username, @RequestParam String password,
			@RequestParam(defaultValue = "false", required = false) Boolean remember) {
		try {
			MemberUserToken token = new MemberUserToken(username, "", "", password, remember, request.getRemoteHost());

			SecurityUtils.getSubject().login(token);
		} catch (AuthenticationException e) {
			return Result.validateError();
		} catch (Exception e) {
			return Result.validateError();
		}
		return Result.success();
	}

	@RequestMapping("/order")
	public String memberOrder() {
		return "member/orderlist";
	}

	@RequestMapping("/order/page")
	@ResponseBody
	public Page<Object> memberOrderPage() {
		return new PageImpl<Object>(new ArrayList<Object>());
	}
}
