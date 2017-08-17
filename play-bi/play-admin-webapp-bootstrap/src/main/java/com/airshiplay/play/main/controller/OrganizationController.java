package com.airshiplay.play.main.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

import com.airshiplay.play.main.entity.OrganizationEntity;
import com.airshiplay.play.main.service.OrganizationEntityService;
import com.airshiplay.play.repo.domain.Node;
import com.airshiplay.play.repo.domain.Result;
import com.airshiplay.play.repo.domain.Tree;
import com.google.common.base.Strings;
import com.querydsl.core.types.Predicate;

@Controller
@RequestMapping("/center/org")
public class OrganizationController {
	@Autowired
	private OrganizationEntityService organizationEntityService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String get(Model model) {
		return "/bootstrap/admin/org/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add() {
		return "/bootstrap/admin/org/orgForm";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(Model model,@PathVariable Long id) {
		model.addAttribute("org", organizationEntityService.findOne(id));
		return "/bootstrap/admin/org/orgForm";
	}
	
	@RequestMapping(value = "/page", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Page<OrganizationEntity> page(Predicate predicate, Pageable pageable) {
		return organizationEntityService.findAll(predicate, pageable);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result doSave(@ModelAttribute @Valid OrganizationEntity manager, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.validateError();
		}
		organizationEntityService.save(manager);

		return Result.success();
	}

	@RequestMapping(value = "/tree", method = RequestMethod.GET)
	@ResponseBody
	public Tree<OrganizationEntity> tree(Predicate predicate) {
		Tree<OrganizationEntity> tree = organizationEntityService.findTree(predicate);
		// tree.setIconClsProperty("icon");
		tree.setTextProperty("name");
		return tree;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "id")
	@ResponseBody
	public Result delete(@RequestParam(value = "id") OrganizationEntity entity) {
		organizationEntityService.delete(entity);
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "ids")
	@ResponseBody
	public Result batchDelete(@RequestParam(value = "ids") OrganizationEntity[] entities) {
		for (OrganizationEntity entity : entities) {
			organizationEntityService.delete(entity);
		}
		return Result.success();
	}
	@RequestMapping(value = "/treeList", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Page<OrganizationEntity> treeList(Predicate predicate, Pageable pageable) {
		Tree<OrganizationEntity> tree = organizationEntityService.findTree(predicate);
		List<OrganizationEntity> result = new ArrayList<OrganizationEntity>();

		Tree.visitNodes(tree.getRoots(), new Consumer<Node<?>>() {

			@Override
			public void accept(Node<?> t) {
				OrganizationEntity data = ((OrganizationEntity) t.getData());
				int length = data.getTreePath().split(",").length;
				data.setName(Strings.repeat("&nbsp;", length * 3) + data.getName());
				result.add(data);
			}
		});
		return new PageImpl<OrganizationEntity>(result);
	}
}
