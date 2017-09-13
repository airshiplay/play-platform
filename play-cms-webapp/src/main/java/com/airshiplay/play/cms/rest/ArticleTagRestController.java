package com.airshiplay.play.cms.rest;

import com.airlenet.repo.domain.Result;
import com.airshiplay.play.cms.entity.ArticleTagEntity;
import com.airshiplay.play.cms.service.ArticleTagEntityService;
import com.google.common.collect.Lists;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author airlenet
 * @version 2017-09-13
 */
@RestController
@RequestMapping("/cms/articleTag")
public class ArticleTagRestController {
    @Autowired
    private ArticleTagEntityService articleTagEntityService;
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Result doList(Predicate predicate) {
        List<ArticleTagEntity> result = Lists
                .newArrayList(articleTagEntityService.findAll(predicate));
        return Result.success().addProperties("content",result);
    }

    @RequestMapping(value = "/page", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Page<ArticleTagEntity> doPage(Predicate predicate, Pageable pageable) {
        return articleTagEntityService.findAll(predicate, pageable);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result doSave(@ModelAttribute @Valid ArticleTagEntity articleTag,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.validateError();
        }

        articleTagEntityService.save(articleTag);

        return Result.success();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, params = "id")
    @ResponseBody
    public Result delete(@RequestParam(value = "id") ArticleTagEntity entity) {
        articleTagEntityService.delete(entity);
        return Result.success();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, params = "ids")
    @ResponseBody
    public Result batchDelete(
            @RequestParam(value = "ids") ArticleTagEntity[] entities) {
        for (ArticleTagEntity entity : entities) {
            articleTagEntityService.delete(entity);
        }

        return Result.success();
    }
}
