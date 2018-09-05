package com.airlenet.admin.resource;

//import com.airlenet.auth.model.PlayAdminUser;
import com.airlenet.admin.entity.UserEntity;
import com.airlenet.admin.model.PlayAdminUser;
import com.airlenet.admin.service.UserRemoteService;
import com.airlenet.admin.service.UserService;
import com.airlenet.authorization.core.CurrentUser;
import com.airlenet.data.domain.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@Api(value = "用户管理")
public class UserResource implements UserRemoteService{
    @Autowired
    private UserService userService;

    @GetMapping("")
    @ResponseBody
    @ApiOperation(value = "用户认证信息")
    public Principal user(Principal user){
        return user;
    }

    @GetMapping("/info")
    @ResponseBody
    @ApiOperation(value = "当前用户认证信息")
    public PlayAdminUser userInfo(@CurrentUser PlayAdminUser playAdminUser){
        return playAdminUser;
    }

    @PreAuthorize("#userId == authentication.principal.userId or hasAuthority('ADMIN')")
    void changePassword(@P("userId") long userId ){

    }
    @GetMapping("/ping")
    @ResponseBody
    public RestResult ping(){
        return RestResult.success();
    }

    @Override
    public UserEntity getUser(@PathVariable("id") long id) {
        return userService.findOne(id).get();
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        Optional<UserEntity> optional = userService.findByUsername(username);
        return optional;
    }
}
