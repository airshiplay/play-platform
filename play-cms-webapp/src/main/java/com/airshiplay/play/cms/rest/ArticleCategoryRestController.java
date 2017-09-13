package com.airshiplay.play.cms.rest;

import com.airlenet.repo.domain.Result;
import com.airlenet.repo.domain.Tree;
import com.airshiplay.play.cms.entity.ArticleCategoryEntity;
import com.airshiplay.play.cms.service.ArticleCategoryEntityService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author airlenet
 * @version 2017-09-13
 */
@RestController
@RequestMapping("/cms/articleCategory")
public class ArticleCategoryRestController {

    @Autowired
    private ArticleCategoryEntityService articleCategoryEntityService;

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

