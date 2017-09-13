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

import com.airshiplay.play.wechat.entity.AttentionUserSubscribeEntity;
import com.airshiplay.play.wechat.service.AttentionUserSubscribeEntityService;
import com.airshiplay.play.repo.domain.Result;
import com.querydsl.core.types.Predicate;
/**
 * 关注用户 时间
 *
 * @author  lig
 * @version 
 */
@Controller
@RequestMapping("/wechat/attentionUserSubscribe")
public class AttentionUserSubscribeController {

	@Autowired
	private LogService logService;
	
	@Autowired
	private AttentionUserSubscribeEntityService attentionUserSubscribeEntityService;

	@RequiresRoles("admin")
	@RequestMapping(value = "/attentionUserSubscribeList.view", method = RequestMethod.GET)
	public String getList() {
		logService.addLog(OperateType.VIEW, LogLevel.INFO, "查询关注用户 时间列表");
		return "classpath:/wechat/attentionUserSubscribe/attentionUserSubscribeList";
	}
	
	@RequiresRoles("admin")
	@RequestMapping(value = { "/create.view" }, method = RequestMethod.GET)
	public String create(Model model) {
		return "classpath:/wechat/attentionUserSubscribe/attentionUserSubscribeForm";
	}
	
	@RequiresRoles("admin")
	@RequestMapping(value = { "/edit/{id}.view" }, method = RequestMethod.GET)
	public String edit(Model model, @PathVariable Long id) {
		model.addAttribute("attentionUserSubscribe", attentionUserSubscribeEntityService.findOne(id));
		return "classpath:/wechat/attentionUserSubscribe/attentionUserSubscribeForm";
	}
	
	@RequiresRoles("admin")
	@RequestMapping(value = { "/view/{id}.view" }, method = RequestMethod.GET)
	public String view(Model model, @PathVariable Long id) {
		model.addAttribute("attentionUserSubscribe", attentionUserSubscribeEntityService.findOne(id));
		logService.addLog(OperateType.VIEW, LogLevel.INFO, "查询关注用户 时间信息");
		return "classpath:/wechat/attentionUserSubscribe/attentionUserSubscribeView";
	}

	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public Page<AttentionUserSubscribeEntity> doPage(Predicate predicate, Pageable pageable) {
		return attentionUserSubscribeEntityService.findAll(predicate, pageable);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result doSave(@ModelAttribute @Valid AttentionUserSubscribeEntity job, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.validateError();
		}

		attentionUserSubscribeEntityService.save(job);

		if(job.isNew()){
			logService.addLog(OperateType.INSERT, LogLevel.INFO, "创建关注用户 时间成功");
		}else{
			logService.addLog(OperateType.UPDATE, LogLevel.INFO, "更新关注用户 时间成功");
		}
		
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "id")
	@ResponseBody
	public Result delete(@RequestParam(value = "id") AttentionUserSubscribeEntity entity) {
		attentionUserSubscribeEntityService.delete(entity);
		logService.addLog(OperateType.DEL, LogLevel.INFO, "删除关注用户 时间成功");
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "ids")
	@ResponseBody
	public Result batchDelete(@RequestParam(value = "ids") AttentionUserSubscribeEntity[] entities) {
		for (AttentionUserSubscribeEntity entity : entities) {
			attentionUserSubscribeEntityService.delete(entity);
		}
		logService.addLog(OperateType.DEL, LogLevel.INFO, "删除关注用户 时间成功");
		return Result.success();
	}
}
