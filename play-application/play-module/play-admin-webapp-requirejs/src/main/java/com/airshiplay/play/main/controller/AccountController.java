package com.airshiplay.play.main.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airshiplay.play.main.entity.UserEntity;
import com.airshiplay.play.main.service.UserEntityService;
import com.airshiplay.play.repo.domain.Result;
import com.airshiplay.play.security.CustomUserDetails;
import com.airshiplay.play.security.PlayPasswordService;

@Controller
@RequestMapping("/center/account")
public class AccountController {

	@Autowired
	UserEntityService userEntityService;
	
	@Autowired
	PlayPasswordService password;
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String get(Model model) {
		Subject subject=	SecurityUtils.getSubject();;
		SecurityUtils.getSubject().getPrincipals();

		model.addAttribute("user", ((CustomUserDetails<?, ?>) subject
				.getPrincipal()).getCustomUser());
		return "/views/freemarker/admin/account/info";
	}

	@RequestMapping(value = "/password", method = RequestMethod.GET)
	public String getPassword(Model model) {
		return "/views/freemarker/admin/account/password";
	}

	@RequestMapping(value = "/password", method = RequestMethod.POST)
	@ResponseBody
	public Result postPassword(Model model) {
		
		//password.encryptPassword(plaintextPassword, salt)
		// {"success":false,"code":"failure","msg":"原密码错误"}
		// "{\"success\":true,\"code\":\"success\",\"msg\":\"操作成功\"}";
		return Result.success();
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result postSave(Model model, @RequestParam Long id,
			@RequestParam String name, @RequestParam String avatar,
			@RequestParam String birthday) {
		UserEntity user=userEntityService.getOne(id);
		user.setName(name);
		user.setPhoto(avatar);
		user =userEntityService.save(user);
		return Result.success();//.addProperties("content", user);
	}
}
