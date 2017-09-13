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

import com.airshiplay.play.wechat.entity.ImageTextTemplateEntity;
import com.airshiplay.play.wechat.service.ImageTextTemplateEntityService;
import com.airshiplay.play.repo.domain.Result;
import com.querydsl.core.types.Predicate;

@Controller
@RequestMapping("/wechat/imageTextTemplate")
public class ImageTextTemplateController {

	@Autowired
	private LogService logService;
	
	@Autowired
	private ImageTextTemplateEntityService imageTextTemplateEntityService;

	@RequiresRoles("admin")
	@RequestMapping(value = "/imageTextTemplateList.view", method = RequestMethod.GET)
	public String getList() {
		logService.addLog(OperateType.VIEW, LogLevel.INFO, "查询图文消息列表");
		return "classpath:/wechat/imageTextTemplate/imageTextTemplateList";
	}
	
	@RequiresRoles("admin")
	@RequestMapping(value = { "/create.view" }, method = RequestMethod.GET)
	public String create(Model model) {
		return "classpath:/wechat/imageTextTemplate/imageTextTemplateForm";
	}
	
	@RequiresRoles("admin")
	@RequestMapping(value = { "/edit/{id}.view" }, method = RequestMethod.GET)
	public String edit(Model model, @PathVariable Long id) {
		model.addAttribute("imageTextTemplate", imageTextTemplateEntityService.findOne(id));
		return "classpath:/wechat/imageTextTemplate/imageTextTemplateForm";
	}
	
	@RequiresRoles("admin")
	@RequestMapping(value = { "/view/{id}.view" }, method = RequestMethod.GET)
	public String view(Model model, @PathVariable Long id) {
		model.addAttribute("imageTextTemplate", imageTextTemplateEntityService.findOne(id));
		logService.addLog(OperateType.VIEW, LogLevel.INFO, "查询图文消息信息");
		return "classpath:/wechat/imageTextTemplate/imageTextTemplateView";
	}

	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public Page<ImageTextTemplateEntity> doPage(Predicate predicate, Pageable pageable) {
		return imageTextTemplateEntityService.findAll(predicate, pageable);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result doSave(@ModelAttribute @Valid ImageTextTemplateEntity job, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.validateError();
		}

		imageTextTemplateEntityService.save(job);

		if(job.isNew()){
			logService.addLog(OperateType.INSERT, LogLevel.INFO, "创建图文消息成功");
		}else{
			logService.addLog(OperateType.UPDATE, LogLevel.INFO, "更新图文消息成功");
		}
		
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "id")
	@ResponseBody
	public Result delete(@RequestParam(value = "id") ImageTextTemplateEntity entity) {
		imageTextTemplateEntityService.delete(entity);
		logService.addLog(OperateType.DEL, LogLevel.INFO, "删除图文消息成功");
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "ids")
	@ResponseBody
	public Result batchDelete(@RequestParam(value = "ids") ImageTextTemplateEntity[] entities) {
		for (ImageTextTemplateEntity entity : entities) {
			imageTextTemplateEntityService.delete(entity);
		}
		logService.addLog(OperateType.DEL, LogLevel.INFO, "删除图文消息成功");
		return Result.success();
	}
}
