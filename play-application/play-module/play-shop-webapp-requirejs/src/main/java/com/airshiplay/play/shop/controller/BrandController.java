package com.airshiplay.play.shop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.querydsl.core.types.Predicate;
import com.airshiplay.play.repo.domain.Result;
import com.airshiplay.play.shop.entity.BrandEntity;
import com.airshiplay.play.shop.service.BrandEntityService;

@Controller
@RequestMapping("/brand")
public class BrandController {

	@Autowired
	private BrandEntityService brandEntityService;

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	@ResponseBody
	public Page<BrandEntity> doPage(Predicate predicate, Pageable pageable) {
		return brandEntityService.findAll(predicate, pageable);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result doSave(@ModelAttribute @Valid BrandEntity brand, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.validateError();
		}

		brandEntityService.save(brand);

		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "id")
	@ResponseBody
	public Result delete(@RequestParam(value = "id") BrandEntity entity) {
		brandEntityService.delete(entity);
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "ids")
	@ResponseBody
	public Result batchDelete(@RequestParam(value = "ids") BrandEntity[] entities) {
		for (BrandEntity entity : entities) {
			brandEntityService.delete(entity);
		}

		return Result.success();
	}
}
