package com.airshiplay.play.obd.website;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**用户车库管理
 * Created by lig on 17/1/29.
 */
@Controller
@RequestMapping("/garage")
public class GarageController {
    @RequestMapping(path = {"", "/"})
    public String get(){
        return  "garage/garage";
    }
    @RequestMapping(path = {"brand"})
    public String brand(){
        return  "garage/brand";
    }
    @RequestMapping(path = {"series/{carId}"})
    public String series(){
        return  "garage/series";
    }
    @RequestMapping(path = {"type/{carId}"})
    public String type(){
        return  "garage/type";
    }

    @RequestMapping(path = {"add"})
    public String add(){
        return  "garage/add";
    }

}
