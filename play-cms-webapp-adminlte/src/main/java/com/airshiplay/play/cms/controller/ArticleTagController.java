package com.airshiplay.play.cms.controller;

import com.airshiplay.play.cms.service.ArticleTagEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/cms/articleTag")
public class ArticleTagController {

	@Autowired
	private ArticleTagEntityService articleTagEntityService;

	@RequestMapping(value = "/articleTagList.view", method = RequestMethod.GET)
	public String getArticleCategoryList() {
		return "classpath:/cms/articleTag/articleTagList";
	}
	
	@RequestMapping(value = {"/create.view"}, method = RequestMethod.GET)
	public String createArticleTag(Model model) {
		return "classpath:/cms/articleTag/articleTagForm";
	}
	@RequestMapping(value = {"/edit/{id}.view"}, method = RequestMethod.GET)
	public String getArticleCategoryCreate(Model model,@PathVariable Long id) {
		model.addAttribute("articleTag", articleTagEntityService.findOne(id));
		return "classpath:/cms/articleTag/articleTagForm";
	}

}
