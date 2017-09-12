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
import com.airshiplay.play.cms.entity.ArticleCategoryEntity;
import com.airshiplay.play.cms.service.ArticleCategoryEntityService;
import com.airlenet.repo.domain.Result;
import com.airlenet.repo.domain.Tree;

@Controller
@RequestMapping("/cms/articleCategory")
public class ArticleCategoryController {

	@Autowired
	private ArticleCategoryEntityService articleCategoryEntityService;
	
	@RequestMapping(value = "/articleCategoryList.view", method = RequestMethod.GET)
	public String getArticleCategoryList() {
		return "/bootstrap/cms/articleCategory/articleCategoryList";
	}
	
	@RequestMapping(value = {"/create.view"}, method = RequestMethod.GET)
	public String getArticleCategoryCreate(Model model) {
		return "/bootstrap/cms/articleCategory/articleCategoryForm";
	}
	
	@RequestMapping(value = {"/edit/{id}.view"}, method = RequestMethod.GET)
	public String editArticleCategory(Model model,@PathVariable Long id) {
		model.addAttribute("articleCategory", articleCategoryEntityService.findOne(id));
		return "/bootstrap/cms/articleCategory/articleCategoryForm";
	}
	
	@RequestMapping(value = "/tree", method = RequestMethod.GET)
	@ResponseBody
	public Tree<ArticleCategoryEntity> tree(Predicate predicate) {
		Tree<ArticleCategoryEntity> tree = articleCategoryEntityService.findTree(predicate);
		tree.setTextProperty("name");
		return tree;
	}

	@RequestMapping(value = "/page", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Page<ArticleCategoryEntity> page(Predicate predicate, Pageable pageable) {
		return articleCategoryEntityService.findAll(predicate, pageable);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result doSave(@ModelAttribute @Valid ArticleCategoryEntity articleCategory, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.validateError();
		}

		articleCategoryEntityService.save(articleCategory);

		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "id")
	@ResponseBody
	public Result delete(@RequestParam(value = "id") ArticleCategoryEntity articleCategory) {
		articleCategoryEntityService.delete(articleCategory);
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "ids")
	@ResponseBody
	public Result batchDelete(@RequestParam(value = "ids") ArticleCategoryEntity[] entities) {
		for (ArticleCategoryEntity entity : entities) {
			articleCategoryEntityService.delete(entity);
		}
		return Result.success();
	}
}
