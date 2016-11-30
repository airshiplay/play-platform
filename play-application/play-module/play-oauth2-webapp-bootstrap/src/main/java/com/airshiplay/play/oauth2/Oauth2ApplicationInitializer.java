package com.airshiplay.play.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.airshiplay.play.integration.ApplicationInitializer;
import com.airshiplay.play.main.init.InitDataTools;

@Component
public class Oauth2ApplicationInitializer extends ApplicationInitializer {
	@Autowired
	private InitDataTools tools;

	@Override
	public void onRootContextRefreshed() {

		// if (!tools.existMenuCode("app_management")) {
		// int sortNo = 5;
		// MenuEntity appManagement = tools
		// .createMenuByParent("应用管理", "app_management",
		// "fa fa-wpforms", null, null, sortNo++, null);
		// tools.createMenuByParent("应用列表", "app_list", "fa fa-cloud",
		// "page/app/app/list", null, sortNo++, appManagement);
		//
		// }

	}
}
