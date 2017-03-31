package com.airshiplay.play.obd.website;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/obdsite/account")
@Controller
public class AccountController {

	@RequestMapping("/my")
	public String getMy() {
		return "obd/my";
	}

	@RequestMapping("/profile")
	public String getProfile() {
		return "obd/profile";
	}
}
