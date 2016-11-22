package com.airshiplay.play.main.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airshiplay.play.main.entity.AdminUserEntity;
import com.airshiplay.play.main.service.UserEntityService;
import com.airshiplay.play.repo.domain.Result;
import com.google.common.base.Strings;
import com.querydsl.core.types.Predicate;

@Controller
@RequestMapping("center/user")
public class UserController {

	@Autowired
	private UserEntityService userEntityService;
	
 

	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String getUsrList(){
		return "/views/freemarker/admin/user/list";
	}
	@RequestMapping(value="/add",method = RequestMethod.GET)
	public String getUsrAdd(){
		return "/views/freemarker/admin/user/add";
	}
	@RequestMapping(value="/edit/{userId}",method = RequestMethod.GET)
	public String getUsrEdit(@PathVariable Long userId,Model model){
		AdminUserEntity userEntity= userEntityService.findOne(userId);
		model.addAttribute("user", userEntity);
		return "/views/freemarker/admin/user/edit";
	}
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public Page<AdminUserEntity> doPage(Predicate predicate, Pageable pageable) {
		return userEntityService.findAll(predicate, pageable);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result doSave(@ModelAttribute @Valid AdminUserEntity user, BindingResult bindingResult, String newPassword) {
		if (bindingResult.hasErrors()) {
			return Result.validateError();
		}

		if(user.isNew()) {
			if(Strings.isNullOrEmpty(newPassword)) {
				return Result.validateError();
			}
			//user.setPassword(passwordEncoder.encode(newPassword));
		}
		userEntityService.save(user);
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "id")
	@ResponseBody
	public Result delete(@RequestParam(value = "id") AdminUserEntity entity) {
		userEntityService.delete(entity);
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "ids")
	@ResponseBody
	public Result batchDelete(@RequestParam(value = "ids") AdminUserEntity[] entities) {
		for (AdminUserEntity entity : entities) {
			userEntityService.delete(entity);
		}

		return Result.success();
	}

}
