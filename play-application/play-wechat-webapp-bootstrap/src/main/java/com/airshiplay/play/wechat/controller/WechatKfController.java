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

import com.airshiplay.play.wechat.entity.WechatKfEntity;
import com.airshiplay.play.wechat.service.WechatKfEntityService;
import com.airshiplay.play.repo.domain.Result;
import com.querydsl.core.types.Predicate;
/**
 * 微信客服
 *
 * @author  lig
 * @version 
 */
@Controller
@RequestMapping("/wechat/wechatKf")
public class WechatKfController {

	@Autowired
	private LogService logService;
	
	@Autowired
	private WechatKfEntityService wechatKfEntityService;

	@RequiresRoles("admin")
	@RequestMapping(value = "/wechatKfList.view", method = RequestMethod.GET)
	public String getList() {
		logService.addLog(OperateType.VIEW, LogLevel.INFO, "查询微信客服列表");
		return "classpath:/wechat/wechatKf/wechatKfList";
	}
	
	@RequiresRoles("admin")
	@RequestMapping(value = { "/create.view" }, method = RequestMethod.GET)
	public String create(Model model) {
		return "classpath:/wechat/wechatKf/wechatKfForm";
	}
	
	@RequiresRoles("admin")
	@RequestMapping(value = { "/edit/{id}.view" }, method = RequestMethod.GET)
	public String edit(Model model, @PathVariable Long id) {
		model.addAttribute("wechatKf", wechatKfEntityService.findOne(id));
		return "classpath:/wechat/wechatKf/wechatKfForm";
	}
	
	@RequiresRoles("admin")
	@RequestMapping(value = { "/view/{id}.view" }, method = RequestMethod.GET)
	public String view(Model model, @PathVariable Long id) {
		model.addAttribute("wechatKf", wechatKfEntityService.findOne(id));
		logService.addLog(OperateType.VIEW, LogLevel.INFO, "查询微信客服信息");
		return "classpath:/wechat/wechatKf/wechatKfView";
	}

	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public Page<WechatKfEntity> doPage(Predicate predicate, Pageable pageable) {
		return wechatKfEntityService.findAll(predicate, pageable);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result doSave(@ModelAttribute @Valid WechatKfEntity job, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.validateError();
		}

		wechatKfEntityService.save(job);

		if(job.isNew()){
			logService.addLog(OperateType.INSERT, LogLevel.INFO, "创建微信客服成功");
		}else{
			logService.addLog(OperateType.UPDATE, LogLevel.INFO, "更新微信客服成功");
		}
		
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "id")
	@ResponseBody
	public Result delete(@RequestParam(value = "id") WechatKfEntity entity) {
		wechatKfEntityService.delete(entity);
		logService.addLog(OperateType.DEL, LogLevel.INFO, "删除微信客服成功");
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "ids")
	@ResponseBody
	public Result batchDelete(@RequestParam(value = "ids") WechatKfEntity[] entities) {
		for (WechatKfEntity entity : entities) {
			wechatKfEntityService.delete(entity);
		}
		logService.addLog(OperateType.DEL, LogLevel.INFO, "删除微信客服成功");
		return Result.success();
	}
}
