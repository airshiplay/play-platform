package com.airshiplay.play.main.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airshiplay.play.main.entity.AdminUserEntity;
import com.airshiplay.play.main.service.UserEntityService;
import com.airlenet.repo.domain.Result;
import com.airlenet.security.PlayPasswordService;
import com.google.common.base.Strings;
import com.querydsl.core.types.Predicate;

@Controller
@RequestMapping("center/user")
public class UserController {

	@Autowired
	private PlayPasswordService passwordService;
	
	@Autowired
	private UserEntityService userEntityService;

	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String getUsrList(){
		return "classpath:/admin/user/list";
	}
	@RequestMapping(value="/add",method = RequestMethod.GET)
	public String getUsrAdd(){
		return "classpath:/admin/user/userForm";
	}
	@RequestMapping(value="/edit/{userId}",method = RequestMethod.GET)
	public String getUsrEdit(@PathVariable Long userId,Model model){
		AdminUserEntity userEntity= userEntityService.findOne(userId);
		model.addAttribute("user", userEntity);
		return "classpath:/admin/user/userForm";
	}


}
