package com.airshiplay.play.main.rest;

import com.airlenet.repo.domain.Result;
import com.airlenet.repo.domain.Tree;
import com.airshiplay.play.main.entity.AdminUserEntity;
import com.airshiplay.play.main.entity.AuthorityEntity;
import com.airshiplay.play.main.entity.MenuEntity;
import com.airshiplay.play.main.entity.RoleEntity;
import com.airshiplay.play.main.service.AuthorityEntityService;
import com.airshiplay.play.main.service.MenuEntityService;
import com.airshiplay.play.main.service.RoleEntityService;
import com.airshiplay.play.main.service.UserEntityService;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.querydsl.core.types.Predicate;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author airlenet
 * @version 2017-09-12
 */
@RestController
@RequestMapping("/center/role")
public class RoleRestController {
    @Autowired
    private RoleEntityService roleEntityService;

    @Autowired
    private MenuEntityService menuEntityService;

    @Autowired
    private AuthorityEntityService authorityEntityService;

    @Autowired
    private UserEntityService userEntityService;
    /**
     * 角色中不存在的用户列表
     *
     * @param predicate
     * @param pageable
     * @return
     */
    @RequiresPermissions(value = { "page:sys:role:read", "data:sys:role:read" }, logical = Logical.OR)
    @RequestMapping(value = "/{roleId}/user/unexist/page", method = RequestMethod.POST)
    @ResponseBody
    public Page<AdminUserEntity> doRoleUnExistUserPage(Predicate predicate, Pageable pageable, @PathVariable Long roleId) {
        return roleEntityService.findUnExistUserPageByRoleId(roleId, predicate, pageable);
    }

    /**
     * 角色存在的用户列表
     *
     * @param pageable
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/{roleId}/user/page", method = RequestMethod.POST)
    @ResponseBody
    public Page<AdminUserEntity> doRoleUserPage(Predicate predicate, Pageable pageable, @PathVariable Long roleId) {
        return roleEntityService.findUserPageByRoleId(predicate,roleId, pageable);
    }

    /**
     * 角色添加用户
     *
     * @param pageable
     * @param roleId
     * @param entities
     * @return
     */
    @RequestMapping(value = "/{roleId}/user/add", method = RequestMethod.POST)
    @ResponseBody
    public Result roleAddUser(Pageable pageable, @PathVariable Long roleId, @RequestParam(value = "ids") AdminUserEntity[] entities) {
        RoleEntity roleEntity = roleEntityService.findOne(roleId);

        for (AdminUserEntity userEntity : entities) {
            userEntity.getRoles().add(roleEntity);
        }
        userEntityService.save(Sets.newHashSet(entities));
        return Result.success();
    }

    @RequestMapping(value = "/{roleId}/user/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result roleRemoveUser(Pageable pageable, @PathVariable Long roleId, @RequestParam(value = "ids") AdminUserEntity[] entities) {
        RoleEntity roleEntity = roleEntityService.findOne(roleId);
        for (AdminUserEntity userEntity : entities) {
            userEntity.getRoles().remove(roleEntity);
        }
        userEntityService.save(Sets.newHashSet(entities));
        return Result.success();
    }

    @RequiresPermissions(value = { "page:sys:role:read", "data:sys:role:read" }, logical = Logical.OR)
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    public Page<RoleEntity> doPage(Predicate predicate, Pageable pageable) {
        return roleEntityService.findAll(predicate, pageable);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result doSave(@ModelAttribute @Valid RoleEntity manager, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.validateError();
        }
        roleEntityService.save(manager);
        return Result.success();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, params = "id")
    @ResponseBody
    public Result delete(@RequestParam(value = "id") RoleEntity entity) {
        roleEntityService.delete(entity);
        return Result.success();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, params = "ids")
    @ResponseBody
    public Result batchDelete(@RequestParam(value = "ids") RoleEntity[] entities) {
        for (RoleEntity entity : entities) {
            roleEntityService.delete(entity);
        }
        return Result.success();
    }

    @RequestMapping(value = "/{roleId}/menu/tree", method = RequestMethod.GET)
    @ResponseBody
    public Tree<MenuEntity> tree(Predicate predicate, @PathVariable Long roleId) {
        Tree<MenuEntity> tree = menuEntityService.findTree(predicate);
        tree.setIconClsProperty("iconCls");
        tree.setTextProperty("text");
        tree.setChecked(roleEntityService.getOne(roleId).getMenus());
        tree.makeCheckable();
        return tree;
    }

    @RequiresPermissions(value = { "data:sys:role:menu:upate", "data:sys:role:menu:create" }, logical = Logical.OR)
    @RequestMapping(value = "/menu/save", method = RequestMethod.POST, params = "ids")
    @ResponseBody
    public Result doRoleMenuSave(@RequestParam(value = "ids") MenuEntity[] entities, @RequestParam(value = "roleId") Long roleId) {
        RoleEntity roleEntity = roleEntityService.findOne(roleId);
        roleEntity.setMenus(Sets.newHashSet(entities));
        roleEntityService.save(roleEntity);
        return Result.success();
    }

    // //////////////////
    /**
     * 菜单对应的权限列表
     *
     * @param roleId
     * @param menuId
     * @param type
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @RequestMapping(value = "/{roleId}/menu/{menuId}/auth/{type}", method = RequestMethod.GET)
    @ResponseBody
    public Result getRoleMenuAuthority(@PathVariable Long roleId, @PathVariable Long menuId, @PathVariable AuthorityEntity.PermissionType type) {
        Iterator<AuthorityEntity> itr = authorityEntityService.findAuthoritiesByMenuIdAndType(menuId, type).iterator();
        List<AuthorityEntity> checkedList = authorityEntityService.findAuthoritiesByRoleId(roleId);
//		 List<Authority> content = ConvertUtil.map(itr,
//		 new Converter<AuthorityEntity, Authority>() {
//		 @Override
//		 public Authority convert(AuthorityEntity source) {
//		 Authority auth = new Authority(source);
//		 if (checkedList.indexOf(source) != -1) {
//		 auth.setChecked(true);
//		 }
//		 return auth;
//		 }
//		 });
        return Result.success();
    }

    /**
     * 角色权限保存
     *
     * @param entities
     * @param roleId
     * @param menuId
     * @param type
     * @return
     */
    @RequestMapping(value = "/auth/{type}/save", method = RequestMethod.POST)
    @ResponseBody
    public Result doRoleAuthSave(@RequestParam(value = "authIds", required = false) AuthorityEntity[] entities, @RequestParam(value = "roleId") Long roleId,
                                 @RequestParam(value = "menuId") Long menuId, AuthorityEntity.PermissionType type) {
        // roleEntityService.deleteAuthoritiesByRoleIdAndMenuId(roleId, menuId);
        RoleEntity roleEntity = roleEntityService.findOne(roleId);

        List<AuthorityEntity> authorites = (entities == null ? new ArrayList<AuthorityEntity>() : Lists.newArrayList(entities));
        if (type == AuthorityEntity.PermissionType.page) {
            for (AuthorityEntity auth : roleEntity.getAuthorities()) {
                if (auth.getType() == AuthorityEntity.PermissionType.data && auth.getMenu().getId() != menuId) {
                    authorites.add(auth);
                }
            }
        } else {
            for (AuthorityEntity auth : roleEntity.getAuthorities()) {
                if (auth.getType() == AuthorityEntity.PermissionType.page && auth.getMenu().getId() != menuId) {
                    authorites.add(auth);
                }
            }
        }
        roleEntity.setAuthorities(authorites);
        roleEntityService.save(roleEntity);
        return Result.success();
    }
}
