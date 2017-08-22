package com.airshiplay.play.main.controller;

import com.airshiplay.play.main.entity.LogEntity;
import com.airshiplay.play.main.entity.QLogEntity;
import com.airshiplay.play.main.service.LogEntityService;
import com.airshiplay.play.repo.domain.Result;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QSort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/center/log")
public class LogController {

	@Autowired
	private LogEntityService logEntityService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getUsrList() {
		return "/bootstrap/admin/log/list";
	}
	@RequestMapping(value = { "/view/{id}.view" }, method = RequestMethod.GET)
	public String view(Model model, @PathVariable Long id) {
		model.addAttribute("log", logEntityService.findOne(id));
		return "/bootstrap/admin/log/logView";
	}

	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public Page<LogEntity> doPage(Predicate predicate, Pageable pageable) {

		PageRequest pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), new QSort(QLogEntity.logEntity.createdDate.desc()).and(pageable.getSort()));

		return logEntityService.findAll(predicate, pageRequest);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "id")
	@ResponseBody
	public Result delete(@RequestParam(value = "id") LogEntity entity) {
		logEntityService.delete(entity);
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "ids")
	@ResponseBody
	public Result batchDelete(@RequestParam(value = "ids") LogEntity[] entities) {
		for (LogEntity entity : entities) {
			logEntityService.delete(entity);
		}

		return Result.success();
	}
}
