package com.airshiplay.play.wechat.controller;

import java.util.concurrent.Callable;

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
import com.airshiplay.play.wechat.entity.CardCouponEntity;
import com.airshiplay.play.wechat.entity.CardCouponMemberEntity;
import com.airshiplay.play.wechat.entity.CardCouponType;
import com.airshiplay.play.wechat.service.CardCouponEntityService;
import com.airshiplay.play.wechat.service.CardCouponMemberEntityService;
import com.airshiplay.play.wechat.service.WeixinCardService;
import com.querydsl.core.types.Predicate;

/**
 * 会员卡券
 *
 * @author lig
 * @version
 */
@Controller
@RequestMapping("/wechat/cardCouponMember")
public class CardCouponMemberController {

	@Autowired
	private LogService logService;

	@Autowired
	private CardCouponMemberEntityService cardCouponMemberEntityService;
	@Autowired
	private CardCouponEntityService cardCouponEntityService;

	@Autowired
	private WeixinCardService weixinCardService;

	@RequiresRoles("admin")
	@RequestMapping(value = "/cardCouponMemberList.view", method = RequestMethod.GET)
	public String getList() {
		logService.addLog(OperateType.VIEW, LogLevel.INFO, "查询会员卡券列表");
		return "/bootstrap/wechat/cardCouponMember/cardCouponMemberList";
	}

	@RequiresRoles("admin")
	@RequestMapping(value = { "/create.view" }, method = RequestMethod.GET)
	public String create(Model model) {
		return "/bootstrap/wechat/cardCouponMember/cardCouponMemberForm";
	}

	@RequiresRoles("admin")
	@RequestMapping(value = { "/edit/{id}.view" }, method = RequestMethod.GET)
	public String edit(Model model, @PathVariable Long id) {
		model.addAttribute("cardCouponMember", cardCouponMemberEntityService.findOne(id));
		return "/bootstrap/wechat/cardCouponMember/cardCouponMemberForm";
	}

	@RequiresRoles("admin")
	@RequestMapping(value = { "/view/{id}.view" }, method = RequestMethod.GET)
	public String view(Model model, @PathVariable Long id) {
		model.addAttribute("cardCouponMember", cardCouponMemberEntityService.findOne(id));
		logService.addLog(OperateType.VIEW, LogLevel.INFO, "查询会员卡券信息");
		return "/bootstrap/wechat/cardCouponMember/cardCouponMemberView";
	}

	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public Page<CardCouponMemberEntity> doPage(Predicate predicate, Pageable pageable) {
		return cardCouponMemberEntityService.findAll(predicate, pageable);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result doSave(@ModelAttribute @Valid CardCouponMemberEntity job, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.validateError();
		}
		boolean nw = job.isNew();
		CardCouponEntity entity = job.getCardCoupon();
		if (entity == null) {
			entity = cardCouponEntityService.newEntity();
		}
		entity.setCardType(CardCouponType.MEMBER_CARD);
		entity.setTitle(job.getTitle());
		cardCouponEntityService.save(entity);
		
		job.setCardCoupon(entity);
		cardCouponMemberEntityService.save(job);

		if (nw) {
			logService.addLog(OperateType.INSERT, LogLevel.INFO, "创建会员卡券成功");
		} else {
			logService.addLog(OperateType.UPDATE, LogLevel.INFO, "更新会员卡券成功");
		}

		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "id")
	@ResponseBody
	public Result delete(@RequestParam(value = "id") CardCouponMemberEntity entity) {
		cardCouponMemberEntityService.delete(entity);
		logService.addLog(OperateType.DEL, LogLevel.INFO, "删除会员卡券成功");
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "ids")
	@ResponseBody
	public Result batchDelete(@RequestParam(value = "ids") CardCouponMemberEntity[] entities) {
		for (CardCouponMemberEntity entity : entities) {
			cardCouponMemberEntityService.delete(entity);
		}
		logService.addLog(OperateType.DEL, LogLevel.INFO, "删除会员卡券成功");
		return Result.success();
	}


}
