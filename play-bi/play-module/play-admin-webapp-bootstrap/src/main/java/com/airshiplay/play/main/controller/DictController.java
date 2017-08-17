package com.airshiplay.play.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airshiplay.play.main.entity.DictEntity;
import com.airshiplay.play.main.service.DictEntityService;
import com.querydsl.core.types.Predicate;

@Controller
@RequestMapping("/center/dict")
public class DictController {

	@Autowired
	DictEntityService dictEntityService;
	
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String getUsrList(){
		return "/bootstrap/admin/dict/list";
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public Page<DictEntity> doPage(Predicate predicate, Pageable pageable) {
		return dictEntityService.findAll(predicate, pageable);
	}
}
