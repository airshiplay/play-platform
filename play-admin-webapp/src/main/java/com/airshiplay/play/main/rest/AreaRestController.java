package com.airshiplay.play.main.rest;

import com.airshiplay.play.main.entity.AreaEntity;
import com.airshiplay.play.main.service.AreaEntityService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author airlenet
 * @version 2017-09-12
 */
public class AreaRestController {
    @Autowired
    private AreaEntityService areaEntityService;

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    public Page<AreaEntity> doPage(Predicate predicate, Pageable pageable) {
        return areaEntityService.findAll(predicate, pageable);
    }
}
