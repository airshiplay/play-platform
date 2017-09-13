package com.airshiplay.play.cms.controller;

import com.airshiplay.play.cms.service.ArticleCategoryEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/cms/articleCategory")
public class ArticleCategoryController {

	@Autowired
	private ArticleCategoryEntityService articleCategoryEntityService;
	
	@RequestMapping(value = "/articleCategoryList.view", method = RequestMethod.GET)
	public String getArticleCategoryList() {
		return "classpath:/cms/articleCategory/articleCategoryList";
	}
	
	@RequestMapping(value = {"/create.view"}, method = RequestMethod.GET)
	public String getArticleCategoryCreate(Model model) {
		return "classpath:/cms/articleCategory/articleCategoryForm";
	}
	
	@RequestMapping(value = {"/edit/{id}.view"}, method = RequestMethod.GET)
	public String editArticleCategory(Model model,@PathVariable Long id) {
		model.addAttribute("articleCategory", articleCategoryEntityService.findOne(id));
		return "classpath:/cms/articleCategory/articleCategoryForm";
	}

}
