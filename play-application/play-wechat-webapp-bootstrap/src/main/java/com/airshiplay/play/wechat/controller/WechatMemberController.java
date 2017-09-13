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

import com.airshiplay.play.wechat.entity.WechatMemberEntity;
import com.airshiplay.play.wechat.service.WechatMemberEntityService;
import com.airshiplay.play.repo.domain.Result;
import com.querydsl.core.types.Predicate;
/**
 * 微信会员
 *
 * @author  lig
 * @version 
 */
@Controller
@RequestMapping("/wechat/wechatMember")
public class WechatMemberController {

	@Autowired
	private LogService logService;
	
	@Autowired
	private WechatMemberEntityService wechatMemberEntityService;

	@RequiresRoles("admin")
	@RequestMapping(value = "/wechatMemberList.view", method = RequestMethod.GET)
	public String getList() {
		logService.addLog(OperateType.VIEW, LogLevel.INFO, "查询微信会员列表");
		return "classpath:/wechat/wechatMember/wechatMemberList";
	}
	
	@RequiresRoles("admin")
	@RequestMapping(value = { "/create.view" }, method = RequestMethod.GET)
	public String create(Model model) {
		return "classpath:/wechat/wechatMember/wechatMemberForm";
	}
	
	@RequiresRoles("admin")
	@RequestMapping(value = { "/edit/{id}.view" }, method = RequestMethod.GET)
	public String edit(Model model, @PathVariable Long id) {
		model.addAttribute("wechatMember", wechatMemberEntityService.findOne(id));
		return "classpath:/wechat/wechatMember/wechatMemberForm";
	}
	
	@RequiresRoles("admin")
	@RequestMapping(value = { "/view/{id}.view" }, method = RequestMethod.GET)
	public String view(Model model, @PathVariable Long id) {
		model.addAttribute("wechatMember", wechatMemberEntityService.findOne(id));
		logService.addLog(OperateType.VIEW, LogLevel.INFO, "查询微信会员信息");
		return "classpath:/wechat/wechatMember/wechatMemberView";
	}

	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public Page<WechatMemberEntity> doPage(Predicate predicate, Pageable pageable) {
		return wechatMemberEntityService.findAll(predicate, pageable);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result doSave(@ModelAttribute @Valid WechatMemberEntity job, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.validateError();
		}

		wechatMemberEntityService.save(job);

		if(job.isNew()){
			logService.addLog(OperateType.INSERT, LogLevel.INFO, "创建微信会员成功");
		}else{
			logService.addLog(OperateType.UPDATE, LogLevel.INFO, "更新微信会员成功");
		}
		
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "id")
	@ResponseBody
	public Result delete(@RequestParam(value = "id") WechatMemberEntity entity) {
		wechatMemberEntityService.delete(entity);
		logService.addLog(OperateType.DEL, LogLevel.INFO, "删除微信会员成功");
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "ids")
	@ResponseBody
	public Result batchDelete(@RequestParam(value = "ids") WechatMemberEntity[] entities) {
		for (WechatMemberEntity entity : entities) {
			wechatMemberEntityService.delete(entity);
		}
		logService.addLog(OperateType.DEL, LogLevel.INFO, "删除微信会员成功");
		return Result.success();
	}
}
