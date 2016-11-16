package com.airshiplay.play.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airshiplay.play.main.entity.LogEntity;
import com.airshiplay.play.main.service.LogEntityService;
import com.querydsl.core.types.Predicate;

@Controller
@RequestMapping("/center/log")
public class LogController {

	@Autowired
	private LogEntityService logEntityService;
	
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String getUsrList(){
		return "/views/freemarker/admin/log/list";
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public Page<LogEntity> doPage(Predicate predicate, Pageable pageable) {
		return logEntityService.findAll(predicate, pageable);
	}
}
