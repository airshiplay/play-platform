package com.airshiplay.play.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QSort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airshiplay.play.main.entity.LogEntity;
import com.airshiplay.play.main.entity.QLogEntity;
import com.airshiplay.play.main.service.LogEntityService;
import com.airlenet.repo.domain.Result;
import com.querydsl.core.types.Predicate;

@Controller
@RequestMapping("/center/log")
public class LogController {

	@Autowired
	private LogEntityService logEntityService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getUsrList() {
		return "classpath:/admin/log/list";
	}
	@RequestMapping(value = { "/view/{id}.view" }, method = RequestMethod.GET)
	public String view(Model model, @PathVariable Long id) {
		model.addAttribute("log", logEntityService.findOne(id));
		return "classpath:/admin/log/logView";
	}

	
}
