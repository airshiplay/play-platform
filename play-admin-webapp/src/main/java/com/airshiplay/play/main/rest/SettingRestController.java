package com.airshiplay.play.main.rest;

import com.airlenet.repo.domain.Result;
import com.airshiplay.play.main.entity.SettingEntity;
import com.airshiplay.play.main.service.SettingEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author airlenet
 * @version 2017-09-12
 */
@RestController
@RequestMapping("/center/setting")
public class SettingRestController {

    @Autowired
    private SettingEntityService settingEntityService;


     @RequestMapping(value = "/get", method = RequestMethod.GET)
     @ResponseBody
     public SettingEntity doGet() {
        return settingEntityService.get();
     }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result doSave(@ModelAttribute @Valid SettingEntity setting,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.validateError();
        }
        settingEntityService.save(setting);
        return Result.success();
    }
}
