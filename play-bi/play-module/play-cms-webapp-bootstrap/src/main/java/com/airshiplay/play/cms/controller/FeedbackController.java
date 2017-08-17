package com.airshiplay.play.cms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.querydsl.core.types.Predicate;
import com.airshiplay.play.cms.entity.FeedbackEntity;
import com.airshiplay.play.cms.service.FeedbackEntityService;
import com.airshiplay.play.repo.domain.Result;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {

	@Autowired
	private FeedbackEntityService feedbackEntityService;

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	@ResponseBody
	public Page<FeedbackEntity> doPage(Predicate predicate, Pageable pageable) {
		return feedbackEntityService.findAll(predicate, pageable);
	}

	@CrossOrigin
	@RequestMapping(value="/submit", method=RequestMethod.POST)
	@ResponseBody
	public Result submit(@ModelAttribute @Valid FeedbackEntity feedback, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.validateError();
		}
		feedbackEntityService.save(feedback);
		
		return Result.success();
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "id")
	@ResponseBody
	public Result delete(@RequestParam(value = "id") FeedbackEntity entity) {
		feedbackEntityService.delete(entity);
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "ids")
	@ResponseBody
	public Result batchDelete(@RequestParam(value = "ids") FeedbackEntity[] entities) {
		for (FeedbackEntity entity : entities) {
			feedbackEntityService.delete(entity);
		}

		return Result.success();
	}
}
