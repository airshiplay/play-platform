package com.airshiplay.play.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.airshiplay.play.integration.ApplicationInitializer;
import com.airshiplay.play.main.entity.MenuEntity;
import com.airshiplay.play.main.entity.OrganizationEntity;
import com.airshiplay.play.main.entity.OrganizationEntity.OrgType;
import com.airshiplay.play.main.entity.RoleEntity;
import com.airshiplay.play.main.entity.UserEntity;
import com.airshiplay.play.main.init.InitDataTools;
import com.airshiplay.play.main.security.PasswordService;
import com.airshiplay.play.main.service.OrganizationEntityService;
import com.airshiplay.play.main.service.RoleEntityService;
import com.airshiplay.play.main.service.UserEntityService;
import com.google.common.collect.Sets;

@Component
public class PlayApplicationInitializer extends ApplicationInitializer {

	@Autowired
	private UserEntityService userEntityService;

	@Autowired
	private PasswordService passwordService;

	@Autowired
	private OrganizationEntityService organizationEntityService;

	@Autowired
	private RoleEntityService roleEntityService;

	@Autowired
	private InitDataTools tools;

	@Override
	public void onRootContextRefreshed() {
		if (roleEntityService.count() == 0) {
			RoleEntity role = roleEntityService.newEntity();
			role.setName("管理员");
			role.setCode("admin");
			role.setLocked(true);
			roleEntityService.save(role);

			OrganizationEntity org = organizationEntityService.newEntity();
			org.setName("广州当凌信息科技有限公司");
			org.setCode("whenling_company");
			org.setType(OrgType.company);
			organizationEntityService.save(org);

			UserEntity admin = userEntityService.newEntity();
			passwordService.changeUserPassword(admin, null, "123456");
			admin.setUsername("admin");
			admin.setMobile("13655177723");
			admin.setEmail("airshiplay@163.com");
			admin.setName("airshiplay");
			admin.setOrg(org);
			admin.setRoles(Sets.newHashSet(role));
			userEntityService.save(admin);

			org.setPrimaryLeader(admin);
			organizationEntityService.save(org);

			
			 

		}

		if (!tools.existMenu()) {
			int sortNo = 0;

			MenuEntity systemManagement = tools.createMenuByParent("系统管理", "center_system_management", "fa fa-desktop", null, null, sortNo++, null);
			tools.createMenuByParent("参数设置", "center_parameter_setting", "fa fa-cogs", "page/center/setting/info", null, sortNo++, systemManagement);
			tools.createMenuByParent("菜单管理", "center_menu_management", "fa fa-navicon", "page/center/menu/list", null, sortNo++, systemManagement);
			tools.createMenuByParent("区域管理", "center_area_management", "fa fa-map", "page/center/area/list", null, sortNo++, systemManagement);
			tools.createMenuByParent("字典管理", "center_dict_management", "fa fa-book", "page/center/dict/list", null, sortNo++, systemManagement);
			tools.createMenuByParent("系统日志", "center_system_log", "fa fa-building", "page/center/log/list", null, sortNo++, systemManagement);

			MenuEntity userManagement = tools.createMenuByParent("用户管理", "center_user_management", "fa fa-users", null, null, sortNo++, null);
			tools.createMenuByParent("用户列表", "center_member_list", "fa fa-user", "page/center/user/list", null, sortNo++, userManagement);
			tools.createMenuByParent("组织机构列表 ", "center_org_list", "fa fa-object-group", "page/center/org/list", null, sortNo++, userManagement);
			tools.createMenuByParent("角色权限", "center_role_list", "fa fa-gavel", "page/center/role/list", null, sortNo++, userManagement);
			
			MenuEntity pluginManagement = tools.createMenuByParent("插件管理", "plugin_management", "fa fa-plug", null, null, sortNo++, null);
			tools.createMenuByParent("oauth认证", "plugin_oauth", "fa fa-cogs", "page/plugin/oauth/list", null, sortNo++, pluginManagement);
			tools.createMenuByParent("支付方式", "plugin_payment", "fa fa-credit-card", "page/plugin/payment/list", null, sortNo++, pluginManagement);

			MenuEntity accountManagement = tools.createMenuByParent("账户管理", "center_account_management", "fa fa-user", null, null, sortNo++, null);
			tools.createMenuByParent("个人信息", "center_account_info", "fa fa-user", "page/center/account/info", null, sortNo++, accountManagement);
			tools.createMenuByParent("修改密码", "center_account_password", "fa fa-key", "page/center/account/password", null, sortNo++, accountManagement);

		}
		if(!tools.existArea()){
			tools.createArea();
		}
		tools.initDict();

	}
}
