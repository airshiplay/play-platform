package com.airshiplay.play.cms.rest;

import com.airlenet.repo.domain.Result;
import com.airshiplay.play.cms.entity.SubscribeEntity;
import com.airshiplay.play.cms.service.SubscribeEntityService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author airlenet
 * @version 2017-09-13
 */
@Controller
@RequestMapping("/cms/subscribe")
public class SubscribeRestController {

	@Autowired
	private SubscribeEntityService subscribeEntityService;

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	@ResponseBody
	public Page<SubscribeEntity> doPage(Predicate predicate, Pageable pageable) {
		return subscribeEntityService.findAll(predicate, pageable);
	}

	@CrossOrigin
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	@ResponseBody
	public Result submit(@ModelAttribute @Valid SubscribeEntity subscribe, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.validateError();
		}
		subscribeEntityService.save(subscribe);

		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "id")
	@ResponseBody
	public Result delete(@RequestParam(value = "id") SubscribeEntity entity) {
		subscribeEntityService.delete(entity);
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "ids")
	@ResponseBody
	public Result batchDelete(@RequestParam(value = "ids") SubscribeEntity[] entities) {
		for (SubscribeEntity entity : entities) {
			subscribeEntityService.delete(entity);
		}

		return Result.success();
	}
}
