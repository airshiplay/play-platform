package com.airshiplay.play.main.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.airshiplay.play.main.entity.MemberRankEntity;
import com.airshiplay.play.main.entity.MemberUserEntity;
import com.airshiplay.play.main.service.MemberUserEntityService;
import com.airshiplay.play.repo.domain.Result;
import com.airshiplay.play.security.PlayPasswordService;
import com.google.common.base.Strings;
import com.querydsl.core.types.Predicate;

/**
 * 会员
 * 
 * @author lig
 *
 */
@Controller
@RequestMapping("/center/member")
public class MemberController {
	@Autowired
	private PlayPasswordService passwordService;
	
	@Autowired
	private MemberUserEntityService userEntityService;

	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String getUsrList(){
		return "/bootstrap/admin/member/list";
	}
	@RequestMapping(value="/add",method = RequestMethod.GET)
	public String getUsrAdd(){
		return "/bootstrap/admin/member/memberForm";
	}
	@RequestMapping(value="/edit/{userId}",method = RequestMethod.GET)
	public String getUsrEdit(@PathVariable Long userId,Model model){
		MemberUserEntity userEntity= userEntityService.findOne(userId);
		model.addAttribute("member", userEntity);
		return "/bootstrap/admin/member/memberForm";
	}
	@RequestMapping(value="/{memberId}/rank/update",method = RequestMethod.GET)
	public String setMemberRank(Model model,@PathVariable Long memberId){
		model.addAttribute("memberId", memberId);
		return  "/bootstrap/admin/member/memberRankDialog";
	}
	
	@RequestMapping(value="/{memberId}/rank/update",method = RequestMethod.POST, params = "id")
	@ResponseBody
	public Result doSetRank(@PathVariable Long memberId,@RequestParam(value = "id") MemberRankEntity entity){
		MemberUserEntity userEntity= userEntityService.findOne(memberId);
		userEntity.setRank(entity);
		userEntityService.save(userEntity);
		return Result.success();
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public Page<MemberUserEntity> doPage(Predicate predicate, Pageable pageable) {
		return userEntityService.findAll(predicate, pageable);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result doSave(@ModelAttribute @Valid MemberUserEntity user, BindingResult bindingResult, String newPassword) {
		if (bindingResult.hasErrors()) {
			return Result.validateError();
		}

		if(user.isNew()) {
			if(Strings.isNullOrEmpty(newPassword)) {
				return Result.validateError();
			}
			String salt= StringUtils.isEmpty(user.getSalt())?passwordService.generatorSalt():user.getSalt();
			user.setPassword(passwordService.encryptPassword(newPassword, salt));
		}else{
			if(!Strings.isNullOrEmpty(newPassword)) {
				String salt= StringUtils.isEmpty(user.getSalt())?passwordService.generatorSalt():user.getSalt();
				user.setPassword(passwordService.encryptPassword(newPassword, salt));
			}
		}
		userEntityService.save(user);
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "id")
	@ResponseBody
	public Result delete(@RequestParam(value = "id") MemberUserEntity entity) {
		userEntityService.delete(entity);
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "ids")
	@ResponseBody
	public Result batchDelete(@RequestParam(value = "ids") MemberUserEntity[] entities) {
		for (MemberUserEntity entity : entities) {
			userEntityService.delete(entity);
		}
		return Result.success();
	}
}
