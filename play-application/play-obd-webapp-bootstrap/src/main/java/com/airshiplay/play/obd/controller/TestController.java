package com.airshiplay.play.obd.controller;

import com.airshiplay.play.obd.ecar.EcarApiTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * Created by lig on 17/1/23.
 */
@Controller
@RequestMapping("/obd/test")
public class TestController {

    @Autowired
    EcarApiTest ecarApiTest;
    @RequestMapping
    public void test() throws IOException, InterruptedException {
        ecarApiTest.main();
    }
}
