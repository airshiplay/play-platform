package com.airshiplay.play.cms.controller;

import com.airshiplay.play.cms.entity.ArticleEntity;
import com.airshiplay.play.cms.service.ArticleEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/cms/article")
public class ArticleController {

	@Autowired
	private ArticleEntityService articleEntityService;
	
	@RequestMapping(value = "/articleList.view", method = RequestMethod.GET)
	public String getArticleCategoryList() {
		return "classpath:/cms/article/articleList";
	}
	
	@RequestMapping(value = {"/create.view"}, method = RequestMethod.GET)
	public String createArticleCategoryCreate(Model model) {
		return "classpath:/cms/article/articleForm";
	}
	
	@RequestMapping(value = {"/edit/{id}"}, method = RequestMethod.GET)
	public String editArticleCategoryCreate(Model model,@PathVariable Long id) {
		ArticleEntity article=articleEntityService.findOne(id);
		model.addAttribute("article", article);
		return "classpath:/cms/article/articleForm";
	}

}
