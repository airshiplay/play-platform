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
import com.airshiplay.play.wechat.entity.CardCouponEntity;
import com.airshiplay.play.wechat.entity.CardCouponType;
import com.airshiplay.play.wechat.service.CardCouponEntityService;
import com.airshiplay.play.wechat.service.WeixinCardService;
import com.airshiplay.play.repo.domain.Result;
import com.querydsl.core.types.Predicate;

/**
 * 卡券管理
 *
 * @author lig
 * @version
 */
@Controller
@RequestMapping("/wechat/cardCoupon")
public class CardCouponController {

	@Autowired
	private LogService logService;

	@Autowired
	private CardCouponEntityService cardCouponEntityService;
	@Autowired
	private WeixinCardService weixinCardService;

	@RequiresRoles("admin")
	@RequestMapping(value = "/cardCouponList.view", method = RequestMethod.GET)
	public String getList() {
		logService.addLog(OperateType.VIEW, LogLevel.INFO, "查询卡券管理列表");
		return "/bootstrap/wechat/cardCoupon/cardCouponList";
	}

	@RequiresRoles("admin")
	@RequestMapping(value = { "/create.view" }, method = RequestMethod.GET)
	public String create(Model model) {
		return "/bootstrap/wechat/cardCoupon/cardCouponForm";
	}

	@RequiresRoles("admin")
	@RequestMapping(value = { "/edit/{id}.view" }, method = RequestMethod.GET)
	public String edit(Model model, @PathVariable Long id) {
		model.addAttribute("cardCoupon", cardCouponEntityService.findOne(id));
		return "/bootstrap/wechat/cardCoupon/cardCouponForm";
	}

	@RequiresRoles("admin")
	@RequestMapping(value = { "/view/{id}.view" }, method = RequestMethod.GET)
	public String view(Model model, @PathVariable Long id) {
		CardCouponEntity cardCoupon = cardCouponEntityService.findOne(id);
		model.addAttribute("cardCoupon", cardCoupon);
		if (cardCoupon.getCardType() == CardCouponType.MEMBER_CARD) {
			model.addAttribute("cardCouponMember", cardCoupon.getMemberCard());
			return "/bootstrap/wechat/cardCouponMember/cardCouponMemberView";
		}
		logService.addLog(OperateType.VIEW, LogLevel.INFO, "查询卡券管理信息");
		return "/bootstrap/wechat/cardCoupon/cardCouponView";
	}

	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public Page<CardCouponEntity> doPage(Predicate predicate, Pageable pageable) {
		return cardCouponEntityService.findAll(predicate, pageable);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result doSave(@ModelAttribute @Valid CardCouponEntity job, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.validateError();
		}

		cardCouponEntityService.save(job);

		if (job.isNew()) {
			logService.addLog(OperateType.INSERT, LogLevel.INFO, "创建卡券管理成功");
		} else {
			logService.addLog(OperateType.UPDATE, LogLevel.INFO, "更新卡券管理成功");
		}

		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "id")
	@ResponseBody
	public Result delete(@RequestParam(value = "id") CardCouponEntity entity) {
		cardCouponEntityService.delete(entity);
		logService.addLog(OperateType.DEL, LogLevel.INFO, "删除卡券管理成功");
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "ids")
	@ResponseBody
	public Result batchDelete(@RequestParam(value = "ids") CardCouponEntity[] entities) {
		for (CardCouponEntity entity : entities) {
			cardCouponEntityService.delete(entity);
		}
		logService.addLog(OperateType.DEL, LogLevel.INFO, "删除卡券管理成功");
		return Result.success();
	}

	@RequestMapping(value = "/asyncCard", method = RequestMethod.POST)
	@ResponseBody
	public Callable<Result> asyncCard(@RequestParam(value = "id") Long id) {
		return new Callable<Result>() {

			@Override
			public Result call() throws Exception {
				weixinCardService.getCardList(id);
				return Result.success();
			}
		};
	}

	@RequestMapping(value = "/weixin/createCard", method = RequestMethod.POST)
	@ResponseBody
	public Callable<Result> createWechatCard(@RequestParam(value = "configId") Long configId, @RequestParam(value = "id") Long cardCouponId) {
		return new Callable<Result>() {
			@Override
			public Result call() throws Exception {
				weixinCardService.createCard(configId, cardCouponId);
				return Result.success();
			}
		};
	}

	@RequestMapping(value = "/weixin/getCard", method = RequestMethod.POST)
	@ResponseBody
	public Callable<Result> getWechatCard(@RequestParam(value = "configId") Long configId, @RequestParam(value = "id") Long cardCouponId) {
		return new Callable<Result>() {
			@Override
			public Result call() throws Exception {
				weixinCardService.getCard(configId, cardCouponId);
				return Result.success();
			}
		};
	}
}
