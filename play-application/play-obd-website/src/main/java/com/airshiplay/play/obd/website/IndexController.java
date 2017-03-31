package com.airshiplay.play.obd.website;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class IndexController {
    @RequestMapping(path = {"", "/", "/index"})
    public String get() {
        return "index";
    }

    @RequestMapping("/my")
    public String getMy() {
        return "/my";
    }

    @RequestMapping("/order")
    public String getOrder() {
        return "/order";
    }
}
