package com.airshiplay.play.main.controller;

import com.airshiplay.play.main.entity.AdminUserEntity;
import com.airshiplay.play.main.service.UserEntityService;
import com.airshiplay.play.repo.domain.Result;
import com.airshiplay.play.security.PlayPasswordService;
import com.google.common.base.Strings;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
@RequestMapping("center/user")
public class UserController {

	@Autowired
	private PlayPasswordService passwordService;
	
	@Autowired
	private UserEntityService userEntityService;

	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String getUsrList(){
		return "/bootstrap/admin/user/list";
	}
	@RequestMapping(value="/add",method = RequestMethod.GET)
	public String getUsrAdd(){
		return "/bootstrap/admin/user/userForm";
	}
	@RequestMapping(value="/edit/{userId}",method = RequestMethod.GET)
	public String getUsrEdit(@PathVariable Long userId,Model model){
		AdminUserEntity userEntity= userEntityService.findOne(userId);
		model.addAttribute("user", userEntity);
		return "/bootstrap/admin/user/userForm";
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
			String salt= StringUtils.isEmpty(user.getSalt())?passwordService.generatorSalt():user.getSalt();
			user.setSalt(salt);
			user.setPassword(passwordService.encryptPassword(newPassword, salt));
		}else{
			if(!Strings.isNullOrEmpty(newPassword)) {
				String salt= StringUtils.isEmpty(user.getSalt())?passwordService.generatorSalt():user.getSalt();
				user.setSalt(salt);
				user.setPassword(passwordService.encryptPassword(newPassword, salt));
			}
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
