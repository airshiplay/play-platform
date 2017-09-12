package com.airshiplay.play.main.rest;

import com.airlenet.repo.domain.Tree;
import com.airshiplay.play.main.entity.MenuEntity;
import com.airshiplay.play.main.service.MenuEntityService;
import com.airshiplay.play.main.service.RoleEntityService;
import com.querydsl.core.types.Predicate;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author airlenet
 * @version 2017-09-12
 */
@RestController
@RequestMapping("/center/menu")
public class MenuRestController {


    @Autowired
    private MenuEntityService menuEntityService;

    @Autowired
    private RoleEntityService roleEntityService;
    @RequiresUser
    @RequiresPermissions("data:sys:menu:read")
    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    @ResponseBody
    public Tree<MenuEntity> tree(Predicate predicate) {
        Tree<MenuEntity> tree = menuEntityService.findTree(predicate);
        tree.setIconClsProperty("iconCls");
        tree.setTextProperty("text");
        tree.makeExpandAll();
        return tree;
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    public Page<MenuEntity> page(Predicate predicate, Pageable pageable) {

        return menuEntityService.findAll(predicate, pageable);
    }
}
