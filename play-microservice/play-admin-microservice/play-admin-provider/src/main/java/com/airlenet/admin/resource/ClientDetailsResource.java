package com.airlenet.admin.resource;

import com.airlenet.admin.entity.OauthClientDetailsEntity;
import com.airlenet.admin.service.OauthService;
import com.airlenet.data.domain.RestResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/client")
public class ClientDetailsResource {
    @Autowired
    private OauthService iClientDetailsService;

    @GetMapping("/ui")
    @ApiOperation(value = "添加客户端detailUi", notes = "添加客户端detailUi")
    public String addClientUi() {
        return "clientUi";
    }

    @PostMapping("/add")
    @ResponseBody
    @ApiOperation(value = "添加客户端detail", notes = "添加客户端detail")
    public RestResult addClient(OauthClientDetailsEntity client) {
        OauthClientDetailsEntity clientDetailsEntity = iClientDetailsService.saveOauthClientDetails(client);
        return RestResult.success();
    }

}