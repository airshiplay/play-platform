package com.airshiplay.play.main.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airshiplay.play.repo.domain.Result;
import com.airshiplay.play.security.CustomUserDetails;

@Controller
@RequestMapping("/center/account")
public class AccountController {

	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String get(Model model) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		model.addAttribute("user", ((CustomUserDetails<?, ?>) authentication
				.getPrincipal()).getCustomUser());
		return "/views/admin/account/info";
	}

	@RequestMapping(value = "/password", method = RequestMethod.GET)
	public String getPassword(Model model) {
		return "/views/admin/account/password";
	}

	@RequestMapping(value = "/password", method = RequestMethod.POST)
	@ResponseBody
	public Result postPassword(Model model) {
		// {"success":false,"code":"failure","msg":"原密码错误"}
		// "{\"success\":true,\"code\":\"success\",\"msg\":\"操作成功\"}";
		return Result.success();
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result postSave(Model model, @RequestParam Long id,
			@RequestParam String name, @RequestParam String avatar,
			@RequestParam String birthday) {
		return Result.success();
	}
}
