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

import com.airshiplay.play.wechat.entity.ImageTextItemEntity;
import com.airshiplay.play.wechat.service.ImageTextItemEntityService;
import com.airshiplay.play.repo.domain.Result;
import com.querydsl.core.types.Predicate;

@Controller
@RequestMapping("/wechat/imageTextItem")
public class ImageTextItemController {

	@Autowired
	private LogService logService;
	
	@Autowired
	private ImageTextItemEntityService imageTextItemEntityService;

	@RequiresRoles("admin")
	@RequestMapping(value = "/imageTextItemList.view", method = RequestMethod.GET)
	public String getList() {
		logService.addLog(OperateType.VIEW, LogLevel.INFO, "查询图文子集列表");
		return "/bootstrap/wechat/imageTextItem/imageTextItemList";
	}
	
	@RequiresRoles("admin")
	@RequestMapping(value = { "/create.view" }, method = RequestMethod.GET)
	public String create(Model model) {
		return "/bootstrap/wechat/imageTextItem/imageTextItemForm";
	}
	
	@RequiresRoles("admin")
	@RequestMapping(value = { "/edit/{id}.view" }, method = RequestMethod.GET)
	public String edit(Model model, @PathVariable Long id) {
		model.addAttribute("imageTextItem", imageTextItemEntityService.findOne(id));
		return "/bootstrap/wechat/imageTextItem/imageTextItemForm";
	}
	
	@RequiresRoles("admin")
	@RequestMapping(value = { "/view/{id}.view" }, method = RequestMethod.GET)
	public String view(Model model, @PathVariable Long id) {
		model.addAttribute("imageTextItem", imageTextItemEntityService.findOne(id));
		logService.addLog(OperateType.VIEW, LogLevel.INFO, "查询图文子集信息");
		return "/bootstrap/wechat/imageTextItem/imageTextItemView";
	}

	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public Page<ImageTextItemEntity> doPage(Predicate predicate, Pageable pageable) {
		return imageTextItemEntityService.findAll(predicate, pageable);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result doSave(@ModelAttribute @Valid ImageTextItemEntity job, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.validateError();
		}

		imageTextItemEntityService.save(job);

		if(job.isNew()){
			logService.addLog(OperateType.INSERT, LogLevel.INFO, "创建图文子集成功");
		}else{
			logService.addLog(OperateType.UPDATE, LogLevel.INFO, "更新图文子集成功");
		}
		
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "id")
	@ResponseBody
	public Result delete(@RequestParam(value = "id") ImageTextItemEntity entity) {
		imageTextItemEntityService.delete(entity);
		logService.addLog(OperateType.DEL, LogLevel.INFO, "删除图文子集成功");
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "ids")
	@ResponseBody
	public Result batchDelete(@RequestParam(value = "ids") ImageTextItemEntity[] entities) {
		for (ImageTextItemEntity entity : entities) {
			imageTextItemEntityService.delete(entity);
		}
		logService.addLog(OperateType.DEL, LogLevel.INFO, "删除图文子集成功");
		return Result.success();
	}
}
