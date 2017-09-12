package com.airshiplay.play.main.rest;

import com.airlenet.repo.domain.Result;
import com.airlenet.security.CurrentUser;
import com.airlenet.security.CustomUserDetails;
import com.airlenet.security.PlayPasswordService;
import com.airshiplay.play.main.entity.AdminUserEntity;
import com.airshiplay.play.main.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author airlenet
 * @version 2017-09-12
 */
@RestController
@RequestMapping("/center/account")
public class AccountRestController {

    @Autowired
    UserEntityService userEntityService;

    //	@Autowired
//	OauthUserService oauthUserService;
//	@Autowired
//	OauthPluginService oauthPluginService;
    @Autowired
    PlayPasswordService passwordService;
    @RequestMapping(value = "/oauth/unbind", method = RequestMethod.POST)
    @ResponseBody
    public Result unBind(@RequestParam String oauthPluginId, @CurrentUser CustomUserDetails<Long, AdminUserEntity> user) {
//		oauthUserService.unBindAdminUser(oauthPluginId, user.getId());
        return Result.success();
    }

    @RequestMapping(value = "/password", method = RequestMethod.POST)
    @ResponseBody
    public Result postPassword(@CurrentUser CustomUserDetails<Long, AdminUserEntity> customUser,@RequestParam String password,@RequestParam String newPassword) {
        AdminUserEntity adminUser =customUser.getCustomUser();
        if(StringUtils.isEmpty(password)&&!StringUtils.isEmpty(adminUser.getPassword())){
            return Result.validateError();
        }
        if(!StringUtils.isEmpty(password)&&!passwordService.passwordsMatch(password, adminUser.getSalt(), adminUser.getPassword())){
            return Result.validateError();
        }
        if(!StringUtils.isEmpty(newPassword)){
            adminUser.setPassword(passwordService.encryptPassword(newPassword, adminUser.getSalt()));
        }
        userEntityService.save(adminUser);
        return Result.success();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result postSave(@ModelAttribute @Valid AdminUserEntity user, BindingResult bindingResult, String avatar, String newPassword) {
        if (bindingResult.hasErrors()) {
            return Result.validateError();
        }
        user.setPhoto(avatar);
        if(!StringUtils.isEmpty(newPassword)){
            user.setPassword(passwordService.encryptPassword(newPassword, user.getSalt()));
        }
        user = userEntityService.save(user);
        return Result.success().addProperties("content", user);
    }
}

