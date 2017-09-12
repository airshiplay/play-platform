package com.airshiplay.play.main.rest;

import com.airlenet.repo.domain.Result;
import com.airshiplay.play.main.entity.MemberRankEntity;
import com.airshiplay.play.main.entity.MemberUserEntity;
import com.airshiplay.play.main.service.MemberRankEntityService;
import com.airshiplay.play.main.service.MemberUserEntityService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author airlenet
 * @version 2017-09-12
 */
@RestController
@RequestMapping("/center/memberRank")
public class MemberRankRestController {

    @Autowired
    private MemberRankEntityService memberRankEntityService;

    @Autowired
    private MemberUserEntityService memberUserEntityService;

    @RequestMapping(value = "/{id}/member/page", method = RequestMethod.POST)
    @ResponseBody
    public Page<MemberUserEntity> doMemberPage(@PathVariable Long id, Predicate predicate, Pageable pageable) {
        return memberUserEntityService.findByRankId(id,predicate,pageable);
    }
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    public Page<MemberRankEntity> doPage(Predicate predicate, Pageable pageable) {
        return memberRankEntityService.findAll(predicate, pageable);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result doSave(@ModelAttribute @Valid MemberRankEntity job, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.validateError();
        }

        memberRankEntityService.save(job);

        return Result.success();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, params = "id")
    @ResponseBody
    public Result delete(@RequestParam(value = "id") MemberRankEntity entity) {
        memberRankEntityService.delete(entity);
        return Result.success();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, params = "ids")
    @ResponseBody
    public Result batchDelete(@RequestParam(value = "ids") MemberRankEntity[] entities) {
        for (MemberRankEntity entity : entities) {
            memberRankEntityService.delete(entity);
        }

        return Result.success();
    }
}
