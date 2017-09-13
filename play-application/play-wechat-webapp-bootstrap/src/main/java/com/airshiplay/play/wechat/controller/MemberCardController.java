package com.airshiplay.play.wechat.controller;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.airshiplay.play.main.api.LogService;
import com.airshiplay.play.main.api.LogService.LogLevel;
import com.airshiplay.play.main.api.LogService.OperateType;

import com.airshiplay.play.wechat.entity.MemberCardEntity;
import com.airshiplay.play.wechat.service.MemberCardEntityService;
import com.airshiplay.play.repo.domain.Result;
import com.querydsl.core.types.Predicate;
/**
 * 会员卡
 *
 * @author  lig
 * @version 
 */
@Controller
@RequestMapping("/wechat/memberCard")
public class MemberCardController {

	@Autowired
	private LogService logService;
	
	@Autowired
	private MemberCardEntityService memberCardEntityService;

	@RequiresRoles("admin")
	@RequestMapping(value = "/memberCardList.view", method = RequestMethod.GET)
	public String getList() {
		logService.addLog(OperateType.VIEW, LogLevel.INFO, "查询会员卡列表");
		return "classpath:/wechat/memberCard/memberCardList";
	}
	
	@RequiresRoles("admin")
	@RequestMapping(value = { "/create.view" }, method = RequestMethod.GET)
	public String create(Model model) {
		return "classpath:/wechat/memberCard/memberCardForm";
	}
	
	@RequiresRoles("admin")
	@RequestMapping(value = { "/edit/{id}.view" }, method = RequestMethod.GET)
	public String edit(Model model, @PathVariable Long id) {
		model.addAttribute("memberCard", memberCardEntityService.findOne(id));
		return "classpath:/wechat/memberCard/memberCardForm";
	}
	
	@RequiresRoles("admin")
	@RequestMapping(value = { "/view/{id}.view" }, method = RequestMethod.GET)
	public String view(Model model, @PathVariable Long id) {
		model.addAttribute("memberCard", memberCardEntityService.findOne(id));
		logService.addLog(OperateType.VIEW, LogLevel.INFO, "查询会员卡信息");
		return "classpath:/wechat/memberCard/memberCardView";
	}

	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public Page<MemberCardEntity> doPage(Predicate predicate, Pageable pageable) {
		return memberCardEntityService.findAll(predicate, pageable);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result doSave(@ModelAttribute @Valid MemberCardEntity job, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.validateError();
		}

		memberCardEntityService.save(job);

		if(job.isNew()){
			logService.addLog(OperateType.INSERT, LogLevel.INFO, "创建会员卡成功");
		}else{
			logService.addLog(OperateType.UPDATE, LogLevel.INFO, "更新会员卡成功");
		}
		
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "id")
	@ResponseBody
	public Result delete(@RequestParam(value = "id") MemberCardEntity entity) {
		memberCardEntityService.delete(entity);
		logService.addLog(OperateType.DEL, LogLevel.INFO, "删除会员卡成功");
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "ids")
	@ResponseBody
	public Result batchDelete(@RequestParam(value = "ids") MemberCardEntity[] entities) {
		for (MemberCardEntity entity : entities) {
			memberCardEntityService.delete(entity);
		}
		logService.addLog(OperateType.DEL, LogLevel.INFO, "删除会员卡成功");
		return Result.success();
	}
}
