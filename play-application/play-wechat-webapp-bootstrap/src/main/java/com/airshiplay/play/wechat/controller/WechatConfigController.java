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
import com.airshiplay.play.repo.domain.Result;
import com.airshiplay.play.wechat.entity.WechatConfigEntity;
import com.airshiplay.play.wechat.service.WechatConfigEntityService;
import com.querydsl.core.types.Predicate;

@Controller
@RequestMapping("/wechat/wechatConfig")
public class WechatConfigController {

	@Autowired
	private WechatConfigEntityService wechatConfigEntityService;
	@Autowired
	private LogService logService;

	@RequiresRoles("admin")
	@RequestMapping(value = "/wechatConfigList.view", method = RequestMethod.GET)
	public String getList() {
		logService.addLog(OperateType.VIEW, LogLevel.INFO, "查询公众号列表");
		return "/bootstrap/wechat/wechatConfig/wechatConfigList";
	}

	@RequestMapping(value = { "/create.view" }, method = RequestMethod.GET)
	public String create(Model model) {
		return "/bootstrap/wechat/wechatConfig/wechatConfigForm";
	}

	@RequestMapping(value = { "/edit/{id}.view" }, method = RequestMethod.GET)
	public String edit(Model model, @PathVariable Long id) {
		model.addAttribute("wechatConfig", wechatConfigEntityService.findOne(id));
		return "/bootstrap/wechat/wechatConfig/wechatConfigForm";
	}

	@RequestMapping(value = { "/view/{id}.view" }, method = RequestMethod.GET)
	public String view(Model model, @PathVariable Long id) {
		model.addAttribute("wechatConfig", wechatConfigEntityService.findOne(id));
		logService.addLog(OperateType.VIEW, LogLevel.INFO, "查询公众号");
		return "/bootstrap/wechat/wechatConfig/wechatConfigView";
	}

	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public Page<WechatConfigEntity> doPage(Predicate predicate, Pageable pageable) {
		return wechatConfigEntityService.findAll(predicate, pageable);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result doSave(@ModelAttribute @Valid WechatConfigEntity job, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.validateError();
		}
		if (job.isNew()&&wechatConfigEntityService.count() > 0) {
			return Result.error().message("只支持一个公众号");
		}

		wechatConfigEntityService.save(job);
		if (job.isNew()) {
			logService.addLog(OperateType.INSERT, LogLevel.INFO, "创建公众号成功");
		} else {
			logService.addLog(OperateType.UPDATE, LogLevel.INFO, "更新公众号成功");
		}
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "id")
	@ResponseBody
	public Result delete(@RequestParam(value = "id") WechatConfigEntity entity) {
		wechatConfigEntityService.delete(entity);
		logService.addLog(OperateType.DEL, LogLevel.INFO, "删除公众号成功");
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "ids")
	@ResponseBody
	public Result batchDelete(@RequestParam(value = "ids") WechatConfigEntity[] entities) {
		for (WechatConfigEntity entity : entities) {
			wechatConfigEntityService.delete(entity);
		}
		logService.addLog(OperateType.DEL, LogLevel.INFO, "删除公众号成功");
		return Result.success();
	}
}
