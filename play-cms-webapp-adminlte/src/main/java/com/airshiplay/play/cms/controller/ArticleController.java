package com.airshiplay.play.cms.controller;

import javax.validation.Valid;

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

import com.querydsl.core.types.Predicate;
import com.airshiplay.play.cms.entity.ArticleEntity;
import com.airshiplay.play.cms.service.ArticleEntityService;
import com.airlenet.repo.domain.Result;

@Controller
@RequestMapping("/cms/article")
public class ArticleController {

	@Autowired
	private ArticleEntityService articleEntityService;
	
	@RequestMapping(value = "/articleList.view", method = RequestMethod.GET)
	public String getArticleCategoryList() {
		return "/bootstrap/cms/article/articleList";
	}
	
	@RequestMapping(value = {"/create.view"}, method = RequestMethod.GET)
	public String createArticleCategoryCreate(Model model) {
		return "/bootstrap/cms/article/articleForm";
	}
	
	@RequestMapping(value = {"/edit/{id}"}, method = RequestMethod.GET)
	public String editArticleCategoryCreate(Model model,@PathVariable Long id) {
		ArticleEntity article=articleEntityService.findOne(id);
		model.addAttribute("article", article);
		return "/bootstrap/cms/article/articleForm";
	}
	
	@RequestMapping(value = "/page", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Page<ArticleEntity> doPage(Predicate predicate, Pageable pageable) {
		return articleEntityService.findAll(predicate, pageable);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result doSave(@ModelAttribute @Valid ArticleEntity article, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.validateError();
		}

		articleEntityService.save(article);

		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "id")
	@ResponseBody
	public Result delete(@RequestParam(value = "id") ArticleEntity entity) {
		articleEntityService.delete(entity);
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "ids")
	@ResponseBody
	public Result batchDelete(@RequestParam(value = "ids") ArticleEntity[] entities) {
		for (ArticleEntity entity : entities) {
			articleEntityService.delete(entity);
		}
		return Result.success();
	}
}
