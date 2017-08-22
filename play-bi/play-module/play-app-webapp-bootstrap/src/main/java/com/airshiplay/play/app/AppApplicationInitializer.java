package com.airshiplay.play.app;

import com.airshiplay.play.integration.ApplicationInitializer;
import com.airshiplay.play.main.entity.MenuEntity;
import com.airshiplay.play.main.init.InitDataTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppApplicationInitializer extends ApplicationInitializer {
	@Autowired
	private InitDataTools tools;

	@Override
	public void onRootContextRefreshed() {

		
		if (!tools.existMenuCode("app_management")) {
			int sortNo = 5;
			MenuEntity appManagement = tools
					.createMenuByParent("应用管理", "app_management",
							"fa fa-wpforms", null, null, sortNo++, null);
			tools.createMenuByParent("应用列表", "app_list", "fa fa-cloud",
					"page/app/app/list", null, sortNo++, appManagement);

		}

	}
}
