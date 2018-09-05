package com.airlenet.admin;

import com.airlenet.admin.entity.MenuEntity;
import com.airlenet.admin.entity.UserEntity;
import com.airlenet.admin.entity.OauthClientDetailsEntity;
import com.airlenet.admin.entity.RoleEntity;
import com.airlenet.admin.service.MenuService;
import com.airlenet.admin.service.OauthService;
import com.airlenet.admin.service.UserService;
import com.airlenet.web.ApplicationInitializer;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PlayAdminProviderInitializer extends ApplicationInitializer {

    @Autowired
    OauthService oauthService;

    @Autowired
    UserService userService;

    @Autowired
    MenuService menuService;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public void onServletContextRefreshed() {
        Optional<OauthClientDetailsEntity> client = oauthService.findOauthClientDetailsById("unity-client");
        if(!client.isPresent()){
            OauthClientDetailsEntity clientDetails = new OauthClientDetailsEntity();
            clientDetails.setClientId("unity-client");
            clientDetails.setClientSecret(passwordEncoder.encode("unity"));
            clientDetails.setResourceIds("auth-resource,sso-resource");
            clientDetails.setScope("read,write");
            clientDetails.setAuthorizedGrantTypes("authorization_code,refresh_token,implicit");
            clientDetails.setAuthorities("ROLE_CLIENT");
            oauthService.saveOauthClientDetails(clientDetails);
        }
        Optional<OauthClientDetailsEntity> mobileClient = oauthService.findOauthClientDetailsById("mobile-client");
        if(!mobileClient.isPresent()){
            OauthClientDetailsEntity clientDetails = new OauthClientDetailsEntity();
            clientDetails.setClientId("mobile-client");
            clientDetails.setClientSecret(passwordEncoder.encode("mobile"));
            clientDetails.setResourceIds("auth-resource,sso-resource");
            clientDetails.setScope("read,write");
            clientDetails.setAuthorizedGrantTypes("password,refresh_token");
            clientDetails.setAuthorities("ROLE_CLIENT");
            oauthService.saveOauthClientDetails(clientDetails);
        }
        Optional<OauthClientDetailsEntity> curlClient = oauthService.findOauthClientDetailsById("curl-client");
        if(!curlClient.isPresent()){
            OauthClientDetailsEntity clientDetails = new OauthClientDetailsEntity();
            clientDetails.setClientId("curl-client");
            clientDetails.setClientSecret(passwordEncoder.encode("client-secret"));
            clientDetails.setResourceIds("auth-resource,sso-resource");
            clientDetails.setScope("sso-resource");
            clientDetails.setAuthorizedGrantTypes("authorization_code,password,implicit,client_credentials,refresh_token");
            clientDetails.setAuthorities("ROLE_CLIENT");
            oauthService.saveOauthClientDetails(clientDetails);
        }
        Optional<OauthClientDetailsEntity> acmeClient = oauthService.findOauthClientDetailsById("acme");
        if(!acmeClient.isPresent()){
            OauthClientDetailsEntity clientDetails = new OauthClientDetailsEntity();
            clientDetails.setClientId("acme");
            clientDetails.setClientSecret(passwordEncoder.encode("acmesecret"));
//            clientDetails.setResourceIds("auth-resource,sso-resource");
            clientDetails.setScope("openid");
            clientDetails.setAuthorizedGrantTypes("authorization_code,password,refresh_token");
            clientDetails.setAutoApprove("true");
//            clientDetails.setAuthorities("ROLE_CLIENT");
            oauthService.saveOauthClientDetails(clientDetails);
        }
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setCode("ADMIN");
        roleEntity.setName("administrator");
        roleEntity.setLocked(true);
        UserEntity userEntity = new UserEntity();
        userEntity.setLocked(true);
        userEntity.setNickname("admin");
        userEntity.setUsername("admin");
        userEntity.setMobile("13655177723");
        userEntity.setPassword(passwordEncoder.encode("123456"));
        userEntity.setRoles(Sets.newHashSet(roleEntity));
        userService.saveUserAndRole(userEntity);

        MenuEntity sysMenuEntity =MenuEntity.builder().parent(null).text("系统管理").type(MenuEntity.MenuType.menu).code("sys-manage").iconCls("").config("").view("").build();
        MenuEntity userMenuEntity =MenuEntity.builder().parent(sysMenuEntity).text("用户管理").type(MenuEntity.MenuType.menu).code("user-manage").iconCls("").config("").view("").build();
        MenuEntity.builder().parent(userMenuEntity).text("用户查看").type(MenuEntity.MenuType.button).code("user-view").iconCls("").config("").view("").build();
        MenuEntity.builder().parent(userMenuEntity).text("用户新增").type(MenuEntity.MenuType.button).code("user-add").iconCls("").config("").view("").build();
        MenuEntity.builder().parent(userMenuEntity).text("用户修改").type(MenuEntity.MenuType.button).code("user-modify").iconCls("").config("").view("").build();
        MenuEntity.builder().parent(userMenuEntity).text("用户删除").type(MenuEntity.MenuType.button).code("user-delete").iconCls("").config("").view("").build();

        MenuEntity menuEntity =MenuEntity.builder().parent(sysMenuEntity).text("菜单管理").type(MenuEntity.MenuType.menu).code("").iconCls("").config("").view("").build();
        MenuEntity.builder().parent(menuEntity).text("菜单查看").type(MenuEntity.MenuType.button).code("").iconCls("").config("").view("").build();
        MenuEntity.builder().parent(menuEntity).text("菜单新增").type(MenuEntity.MenuType.button).code("").iconCls("").config("").view("").build();
        MenuEntity.builder().parent(menuEntity).text("菜单修改").type(MenuEntity.MenuType.button).code("").iconCls("").config("").view("").build();
        MenuEntity.builder().parent(menuEntity).text("菜单删除").type(MenuEntity.MenuType.button).code("").iconCls("").config("").view("").build();


        MenuEntity roleMenuEntity =MenuEntity.builder().parent(sysMenuEntity).text("角色管理").type(MenuEntity.MenuType.menu).code("").iconCls("").config("").view("").build();
        MenuEntity.builder().parent(roleMenuEntity).text("角色查看").type(MenuEntity.MenuType.button).code("").iconCls("").config("").view("").build();
        MenuEntity.builder().parent(roleMenuEntity).text("角色新增").type(MenuEntity.MenuType.button).code("").iconCls("").config("").view("").build();
        MenuEntity.builder().parent(roleMenuEntity).text("角色修改").type(MenuEntity.MenuType.button).code("").iconCls("").config("").view("").build();
        MenuEntity.builder().parent(roleMenuEntity).text("角色删除").type(MenuEntity.MenuType.button).code("").iconCls("").config("").view("").build();
        MenuEntity.builder().parent(roleMenuEntity).text("分配权限").type(MenuEntity.MenuType.button).code("").iconCls("").config("").view("").build();

        MenuEntity logMenuEntity =MenuEntity.builder().parent(sysMenuEntity).text("日志管理").type(MenuEntity.MenuType.menu).code("").iconCls("").config("").view("").build();
        MenuEntity.builder().parent(logMenuEntity).text("日志查看").type(MenuEntity.MenuType.button).code("").iconCls("").config("").view("").build();
        MenuEntity.builder().parent(logMenuEntity).text("日志删除").type(MenuEntity.MenuType.button).code("").iconCls("").config("").view("").build();

        MenuEntity dictMenuEntity =MenuEntity.builder().parent(sysMenuEntity).text("字典管理").type(MenuEntity.MenuType.menu).code("").iconCls("").config("").view("").build();
        MenuEntity.builder().parent(dictMenuEntity).text("字典查看").type(MenuEntity.MenuType.button).code("").iconCls("").config("").view("").build();
        MenuEntity.builder().parent(dictMenuEntity).text("字典删除").type(MenuEntity.MenuType.button).code("").iconCls("").config("").view("").build();
        MenuEntity.builder().parent(dictMenuEntity).text("字典新增").type(MenuEntity.MenuType.button).code("").iconCls("").config("").view("").build();
        MenuEntity.builder().parent(dictMenuEntity).text("字典修改").type(MenuEntity.MenuType.button).code("").iconCls("").config("").view("").build();

        MenuEntity deptMenuEntity =MenuEntity.builder().parent(sysMenuEntity).text("组织管理").type(MenuEntity.MenuType.menu).code("").iconCls("").config("").view("").build();
        MenuEntity.builder().parent(deptMenuEntity).text("组织查看").type(MenuEntity.MenuType.button).code("").iconCls("").config("").view("").build();
        MenuEntity.builder().parent(deptMenuEntity).text("组织删除").type(MenuEntity.MenuType.button).code("").iconCls("").config("").view("").build();
        MenuEntity.builder().parent(deptMenuEntity).text("组织新增").type(MenuEntity.MenuType.button).code("").iconCls("").config("").view("").build();
        MenuEntity.builder().parent(deptMenuEntity).text("组织修改").type(MenuEntity.MenuType.button).code("").iconCls("").config("").view("").build();

        MenuEntity clientMenuEntity =MenuEntity.builder().parent(sysMenuEntity).text("客户端管理").type(MenuEntity.MenuType.menu).code("").iconCls("").config("").view("").build();
        MenuEntity.builder().parent(clientMenuEntity).text("客户端查看").type(MenuEntity.MenuType.button).code("").iconCls("").config("").view("").build();
        MenuEntity.builder().parent(clientMenuEntity).text("客户端删除").type(MenuEntity.MenuType.button).code("").iconCls("").config("").view("").build();
        MenuEntity.builder().parent(clientMenuEntity).text("客户端新增").type(MenuEntity.MenuType.button).code("").iconCls("").config("").view("").build();
        MenuEntity.builder().parent(clientMenuEntity).text("客户端修改").type(MenuEntity.MenuType.button).code("").iconCls("").config("").view("").build();

        MenuEntity routeMenuEntity =MenuEntity.builder().parent(sysMenuEntity).text("路由管理").type(MenuEntity.MenuType.menu).code("").iconCls("").config("").view("").build();
        MenuEntity.builder().parent(routeMenuEntity).text("路由查看").type(MenuEntity.MenuType.button).code("").iconCls("").config("").view("").build();
        MenuEntity.builder().parent(routeMenuEntity).text("路由删除").type(MenuEntity.MenuType.button).code("").iconCls("").config("").view("").build();
        MenuEntity.builder().parent(routeMenuEntity).text("路由新增").type(MenuEntity.MenuType.button).code("").iconCls("").config("").view("").build();
        MenuEntity.builder().parent(routeMenuEntity).text("路由修改").type(MenuEntity.MenuType.button).code("").iconCls("").config("").view("").build();

        MenuEntity monitorMenuEntity =MenuEntity.builder().parent(null).text("系统监控").type(MenuEntity.MenuType.menu).code("").iconCls("").config("").view("").build();


        menuService.save(sysMenuEntity);

    }

    @Override
    public void onRootContextRefreshed() {

    }
}
