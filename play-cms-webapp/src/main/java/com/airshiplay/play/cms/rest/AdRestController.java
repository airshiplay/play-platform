package com.airshiplay.play.cms.rest;

import com.airlenet.repo.domain.Result;
import com.airshiplay.play.cms.entity.AdEntity;
import com.airshiplay.play.cms.service.AdEntityService;
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
@RequestMapping("/cms/ad")
public class AdRestController {
    @Autowired
    private AdEntityService adEntityService;

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    public Page<AdEntity> doPage(Predicate predicate, Pageable pageable) {
        return adEntityService.findAll(predicate, pageable);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result doSave(@ModelAttribute @Valid AdEntity ad,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.validateError();
        }

        adEntityService.save(ad);

        return Result.success();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, params = "id")
    @ResponseBody
    public Result delete(@RequestParam(value = "id") AdEntity entity) {
        adEntityService.delete(entity);
        return Result.success();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, params = "ids")
    @ResponseBody
    public Result batchDelete(@RequestParam(value = "ids") AdEntity[] entities) {
        for (AdEntity entity : entities) {
            adEntityService.delete(entity);
        }

        return Result.success();
    }
}
