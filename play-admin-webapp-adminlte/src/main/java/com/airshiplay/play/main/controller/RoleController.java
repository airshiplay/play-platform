package com.airshiplay.play.main.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import com.airshiplay.play.main.domain.Authority;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airshiplay.play.main.entity.AdminUserEntity;
//import com.airshiplay.play.main.api.Authority;
import com.airshiplay.play.main.entity.AuthorityEntity;
import com.airshiplay.play.main.entity.AuthorityEntity.PermissionType;
import com.airshiplay.play.main.entity.MenuEntity;
import com.airshiplay.play.main.entity.RoleEntity;
import com.airshiplay.play.main.service.AuthorityEntityService;
import com.airshiplay.play.main.service.MenuEntityService;
import com.airshiplay.play.main.service.RoleEntityService;
import com.airshiplay.play.main.service.UserEntityService;
import com.airlenet.repo.domain.Result;
import com.airlenet.repo.domain.Tree;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.querydsl.core.types.Predicate;

@Controller
@RequestMapping("/center/role")
public class RoleController {

	@Autowired
	private RoleEntityService roleEntityService;

	@Autowired
	private MenuEntityService menuEntityService;

	@RequiresPermissions(value = { "page:sys:role:read" })
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList() {
		return "classpath:/admin/role/list";
	}

	@RequiresPermissions(value = { "page:sys:role:create" })
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAdd() {
		return "classpath:/admin/role/roleForm";
	}

	@RequiresPermissions({ "page:sys:role:upate" })
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String getEdit(Model model, @PathVariable Long id) {
		model.addAttribute("role", roleEntityService.findOne(id));
		return "classpath:/admin/role/roleForm";
	}
	
	@RequestMapping(value = "/{roleId}/roleUserList", method = RequestMethod.GET)
	public String getRoleUserListView(Model model, @PathVariable Long roleId){
		model.addAttribute("roleId", roleId);
		return "classpath:/admin/role/roleUserListDialog";
	}

	@RequestMapping(value = "/{roleId}/roleAddUserList", method = RequestMethod.GET)
	public String getRoleUnUserListView(Model model, @PathVariable Long roleId){
		model.addAttribute("roleId", roleId);
		return "classpath:/admin/role/roleAddUserListDialog";
	}
	
	@RequestMapping(value = "/{roleId}/authList", method = RequestMethod.GET)
	public String getRoleUnAuthListView(Model model, @PathVariable Long roleId){
		model.addAttribute("roleId", roleId);
		return "classpath:/admin/role/authListDialog";
	}
	
	/**
	 * 角色用户列表
	 * 
	 * @param model
	 * @param roleId
	 * @return
	 */
	@RequiresPermissions("page:sys:role:read")
	@RequestMapping(value = "/{roleId}/user/list.view", method = RequestMethod.GET)
	public String getRoleUserList(Model model, @PathVariable Long roleId) {
		model.addAttribute("role", roleEntityService.findOne(roleId));
		return "classpath:/admin/role/user/list";
	}

	@RequiresPermissions("page:sys:role:read")
	@RequestMapping(value = "/{roleId}/user/add", method = RequestMethod.GET)
	public String doAddRoleUserList(Model model, @PathVariable Long roleId) {
		model.addAttribute("role", roleEntityService.findOne(roleId));

		return "classpath:/admin/role/user/add";
	}

	/**
	 * 以下菜单相关
	 * 
	 * @param model
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/{id}/menu/edit", method = RequestMethod.GET)
	public String getEditMenu(Model model, @PathVariable Long id) {
		RoleEntity role = roleEntityService.findOne(id);
		Set<MenuEntity> roleMenu = role.getMenus();
		Tree<MenuEntity> treeMenu = menuEntityService.getAllMenuTree();
		treeMenu.setIconClsProperty("iconCls");
		treeMenu.setTextProperty("text");
		treeMenu.setChecked(roleMenu);
		treeMenu.makeCheckable();
		model.addAttribute("role", role);
		model.addAttribute("allMenuTree", treeMenu.getRoots());
		return "classpath:/admin/role/menu/edit";
	}

}
