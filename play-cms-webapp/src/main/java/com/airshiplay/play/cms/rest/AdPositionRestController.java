package com.airshiplay.play.cms.rest;

import com.airlenet.repo.domain.Result;
import com.airshiplay.play.cms.entity.AdPositionEntity;
import com.airshiplay.play.cms.service.AdPositionEntityService;
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
@RequestMapping("/cms/adPosition")
public class AdPositionRestController {

    @Autowired
    private AdPositionEntityService adPositionEntityService;

    @RequestMapping(value = "/page", method ={ RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Page<AdPositionEntity> doPage(Predicate predicate, Pageable pageable) {
        return adPositionEntityService.findAll(predicate, pageable);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result doSave(@ModelAttribute @Valid AdPositionEntity adPosition, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.validateError();
        }

        adPositionEntityService.save(adPosition);

        return Result.success();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, params = "id")
    @ResponseBody
    public Result delete(@RequestParam(value = "id") AdPositionEntity entity) {
        adPositionEntityService.delete(entity);
        return Result.success();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, params = "ids")
    @ResponseBody
    public Result batchDelete(@RequestParam(value = "ids") AdPositionEntity[] entities) {
        for (AdPositionEntity entity : entities) {
            adPositionEntityService.delete(entity);
        }

        return Result.success();
    }
}
