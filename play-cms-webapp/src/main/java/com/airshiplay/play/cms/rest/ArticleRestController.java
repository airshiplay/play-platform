package com.airshiplay.play.cms.rest;

import com.airlenet.repo.domain.Result;
import com.airshiplay.play.cms.entity.ArticleEntity;
import com.airshiplay.play.cms.service.ArticleEntityService;
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
@RequestMapping("/cms/article")
public class ArticleRestController {

    @Autowired
    private ArticleEntityService articleEntityService;

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
