package com.airshiplay.play.cms.rest;

import com.airlenet.repo.domain.Result;
import com.airshiplay.play.cms.entity.FeedbackEntity;
import com.airshiplay.play.cms.service.FeedbackEntityService;
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
@RequestMapping("/cms/feedback")
public class FeedbackRestController {

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
