package com.airshiplay.play.obd.website;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airshiplay.play.obd.entity.Store4SEntity;
import com.airshiplay.play.obd.service.Store4SEntityService;
import com.querydsl.core.types.Predicate;

@Controller
@RequestMapping("/store4s")
public class Store4SController {

	@Autowired
	private Store4SEntityService store4SEntityService;

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public String getList() {
		return "/store4s";
	}

	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public Page<Store4SEntity> doPage(Predicate predicate, Pageable pageable) {
		return store4SEntityService.findAll(predicate, pageable);
	}

}
