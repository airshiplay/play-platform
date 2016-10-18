package com.airshiplay.play.main.controller;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.airshiplay.play.main.entity.MenuEntity;
import com.airshiplay.play.main.entity.RoleEntity;
import com.airshiplay.play.main.service.MenuEntityService;
import com.airshiplay.play.main.service.RoleEntityService;
import com.airshiplay.play.repo.domain.Result;
import com.airshiplay.play.repo.domain.Tree;
import com.querydsl.core.types.Predicate;

@Controller
@RequestMapping("/center/role")
public class RoleController {

	@Autowired
	private RoleEntityService roleEntityService;

	@Autowired
	private MenuEntityService menuEntityService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList() {
		return "/views/admin/role/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAdd() {
		return "/views/admin/role/add";
	}

	@RequestMapping(value = "/menu/edit/{id}", method = RequestMethod.GET)
	public String getEditMenu(Model model, @PathVariable Long id) {
		RoleEntity role = roleEntityService.findOne(id);
		Set<MenuEntity> roleMenu = role.getMenus();
		Tree<MenuEntity> treeMenu = menuEntityService.getAllMenuTree();
		treeMenu.setChecked(roleMenu);
		treeMenu.makeCheckable();
		model.addAttribute("role", role);
		model.addAttribute("allMenuTree", treeMenu.getRoots());
		return "/views/admin/role/menu/edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String getEdit(Model model, @PathVariable Long id) {
		model.addAttribute("role", roleEntityService.findOne(id));

		return "/views/admin/role/edit";
	}

	@RequestMapping(value = "/menu/save", method = RequestMethod.POST)
	public Result doRoleMenuSave( Model model) {

		return Result.success();
	}

	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public Page<RoleEntity> doPage(Predicate predicate, Pageable pageable) {
		return roleEntityService.findAll(predicate, pageable);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result doSave(@ModelAttribute @Valid RoleEntity manager,
			BindingResult bindingResult) {
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

}
