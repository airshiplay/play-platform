package com.airshiplay.play.obd.website;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
@Controller
public class AccountController {


	@RequestMapping("/profile")
	public String getProfile() {
		return "profile";
	}
}
