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

import com.airshiplay.play.wechat.entity.WechatMessageEntity;
import com.airshiplay.play.wechat.service.WechatMessageEntityService;
import com.airshiplay.play.repo.domain.Result;
import com.querydsl.core.types.Predicate;
/**
 * 消息管理
 *
 * @author  lig
 * @version 
 */
@Controller
@RequestMapping("/wechat/wechatMessage")
public class WechatMessageController {

	@Autowired
	private LogService logService;
	
	@Autowired
	private WechatMessageEntityService wechatMessageEntityService;

	@RequiresRoles("admin")
	@RequestMapping(value = "/wechatMessageList.view", method = RequestMethod.GET)
	public String getList() {
		logService.addLog(OperateType.VIEW, LogLevel.INFO, "查询消息管理列表");
		return "/bootstrap/wechat/wechatMessage/wechatMessageList";
	}
	
	@RequiresRoles("admin")
	@RequestMapping(value = { "/create.view" }, method = RequestMethod.GET)
	public String create(Model model) {
		return "/bootstrap/wechat/wechatMessage/wechatMessageForm";
	}
	
	@RequiresRoles("admin")
	@RequestMapping(value = { "/edit/{id}.view" }, method = RequestMethod.GET)
	public String edit(Model model, @PathVariable Long id) {
		model.addAttribute("wechatMessage", wechatMessageEntityService.findOne(id));
		return "/bootstrap/wechat/wechatMessage/wechatMessageForm";
	}
	
	@RequiresRoles("admin")
	@RequestMapping(value = { "/view/{id}.view" }, method = RequestMethod.GET)
	public String view(Model model, @PathVariable Long id) {
		model.addAttribute("wechatMessage", wechatMessageEntityService.findOne(id));
		logService.addLog(OperateType.VIEW, LogLevel.INFO, "查询消息管理信息");
		return "/bootstrap/wechat/wechatMessage/wechatMessageView";
	}

	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public Page<WechatMessageEntity> doPage(Predicate predicate, Pageable pageable) {
		return wechatMessageEntityService.findAll(predicate, pageable);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result doSave(@ModelAttribute @Valid WechatMessageEntity job, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.validateError();
		}

		wechatMessageEntityService.save(job);

		if(job.isNew()){
			logService.addLog(OperateType.INSERT, LogLevel.INFO, "创建消息管理成功");
		}else{
			logService.addLog(OperateType.UPDATE, LogLevel.INFO, "更新消息管理成功");
		}
		
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "id")
	@ResponseBody
	public Result delete(@RequestParam(value = "id") WechatMessageEntity entity) {
		wechatMessageEntityService.delete(entity);
		logService.addLog(OperateType.DEL, LogLevel.INFO, "删除消息管理成功");
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "ids")
	@ResponseBody
	public Result batchDelete(@RequestParam(value = "ids") WechatMessageEntity[] entities) {
		for (WechatMessageEntity entity : entities) {
			wechatMessageEntityService.delete(entity);
		}
		logService.addLog(OperateType.DEL, LogLevel.INFO, "删除消息管理成功");
		return Result.success();
	}
}
