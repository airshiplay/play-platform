package com.airshiplay.play.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airshiplay.play.main.entity.AreaEntity;
import com.airshiplay.play.main.service.AreaEntityService;
import com.querydsl.core.types.Predicate;

@Controller
@RequestMapping("/center/area")
public class AreaController {

	@Autowired
	private AreaEntityService areaEntityService;
	
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String getUsrList(){
		return "/bootstrap/admin/area/list";
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public Page<AreaEntity> doPage(Predicate predicate, Pageable pageable) {
		return areaEntityService.findAll(predicate, pageable);
	}
}
