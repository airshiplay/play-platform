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

import com.airshiplay.play.wechat.entity.TextTemplateEntity;
import com.airshiplay.play.wechat.service.TextTemplateEntityService;
import com.airshiplay.play.repo.domain.Result;
import com.querydsl.core.types.Predicate;

@Controller
@RequestMapping("/wechat/textTemplate")
public class TextTemplateController {

	@Autowired
	private LogService logService;
	
	@Autowired
	private TextTemplateEntityService textTemplateEntityService;

	@RequiresRoles("admin")
	@RequestMapping(value = "/textTemplateList.view", method = RequestMethod.GET)
	public String getList() {
		logService.addLog(OperateType.VIEW, LogLevel.INFO, "查询文本消息列表");
		return "classpath:/wechat/textTemplate/textTemplateList";
	}
	
	@RequiresRoles("admin")
	@RequestMapping(value = { "/create.view" }, method = RequestMethod.GET)
	public String create(Model model) {
		return "classpath:/wechat/textTemplate/textTemplateForm";
	}
	
	@RequiresRoles("admin")
	@RequestMapping(value = { "/edit/{id}.view" }, method = RequestMethod.GET)
	public String edit(Model model, @PathVariable Long id) {
		model.addAttribute("textTemplate", textTemplateEntityService.findOne(id));
		return "classpath:/wechat/textTemplate/textTemplateForm";
	}
	
	@RequiresRoles("admin")
	@RequestMapping(value = { "/view/{id}.view" }, method = RequestMethod.GET)
	public String view(Model model, @PathVariable Long id) {
		model.addAttribute("textTemplate", textTemplateEntityService.findOne(id));
		logService.addLog(OperateType.VIEW, LogLevel.INFO, "查询文本消息信息");
		return "classpath:/wechat/textTemplate/textTemplateView";
	}

	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public Page<TextTemplateEntity> doPage(Predicate predicate, Pageable pageable) {
		return textTemplateEntityService.findAll(predicate, pageable);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result doSave(@ModelAttribute @Valid TextTemplateEntity job, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.validateError();
		}

		textTemplateEntityService.save(job);

		if(job.isNew()){
			logService.addLog(OperateType.INSERT, LogLevel.INFO, "创建文本消息成功");
		}else{
			logService.addLog(OperateType.UPDATE, LogLevel.INFO, "更新文本消息成功");
		}
		
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "id")
	@ResponseBody
	public Result delete(@RequestParam(value = "id") TextTemplateEntity entity) {
		textTemplateEntityService.delete(entity);
		logService.addLog(OperateType.DEL, LogLevel.INFO, "删除文本消息成功");
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "ids")
	@ResponseBody
	public Result batchDelete(@RequestParam(value = "ids") TextTemplateEntity[] entities) {
		for (TextTemplateEntity entity : entities) {
			textTemplateEntityService.delete(entity);
		}
		logService.addLog(OperateType.DEL, LogLevel.INFO, "删除文本消息成功");
		return Result.success();
	}
}
