package com.airshiplay.play.main.init;

import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.airshiplay.play.main.entity.AreaEntity;
import com.airshiplay.play.main.entity.AreaEntity.AreaType;
import com.airshiplay.play.main.entity.DictEntity;
import com.airshiplay.play.main.entity.MenuEntity;
import com.airshiplay.play.main.entity.SettingEntity;
import com.airshiplay.play.main.service.AreaEntityService;
import com.airshiplay.play.main.service.DictEntityService;
import com.airshiplay.play.main.service.MenuEntityService;
import com.airshiplay.play.main.service.SettingEntityService;
import com.google.common.base.Strings;

@Component
public class InitDataTools {

	@Autowired
	private MenuEntityService menuEntityService;

	@Autowired
	private SettingEntityService settingEntityService;

	@Autowired
	private AreaEntityService areaEntityService;

	@Autowired
	private DictEntityService dictEntityService;

	public void setting(String siteName, String logo, String adminLoginBanner,
			String phone1, String phone2, String email, String address) {
		SettingEntity settingEntity = settingEntityService.get();
		settingEntity.setSiteName(siteName);
		settingEntity.setLogo(logo);
		settingEntity.setAdminLoginBanner(adminLoginBanner);
		settingEntity.setPhone1(phone1);
		settingEntity.setPhone2(phone2);
		settingEntity.setEmail(email);
		settingEntity.setAddress(address);
		settingEntityService.save(settingEntity);
	}

	public boolean existMenu() {
		return menuEntityService.count() > 0;
	}

	public boolean existArea() {
		return areaEntityService.count() > 0;
	}

	public boolean existMenuCode(String code) {
		return menuEntityService.findByCode(code) != null;
	}

	public MenuEntity createMenuByParent(String label, String code,
			String iconCls, String view, String config, Integer sortNo,
			MenuEntity parent) {
		MenuEntity menu = menuEntityService.newEntity();
		menu.setText(label);
		menu.setCode(code);
		menu.setIconCls(iconCls);
		menu.setView(view);
		menu.setConfig(config);
		menu.setSortNo(sortNo);
		menu.setParent(parent);
		menuEntityService.save(menu);
		return menu;
	}

	public MenuEntity createMenuByParentCode(String label, String code,
			String iconCls, String view, String config, Integer sortNo,
			String parentCode) {
		return createMenuByParent(label, parentCode, iconCls, view, config,
				sortNo, Strings.isNullOrEmpty(parentCode) ? null
						: menuEntityService.findByCode(parentCode));
	}

	public void initDict() {
		if (dictEntityService.countByType(AreaType.class.getSimpleName()) == 0) {
			dictEntityService.save(AreaType.class, "区域等级");
		}
	}

	public void createArea() {

		AreaEntity entityCh = areaEntityService.newEntity();
		entityCh.setName("中国");
		entityCh.setParent(null);
		entityCh.setFullName("中国");
		entityCh.setSortNo(0);
		entityCh.setType(AreaType.country);
		areaEntityService.save(entityCh);
		try {

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					getClass().getClassLoader().getResourceAsStream(
							"core_area.txt")));

			String line = null;
			line = reader.readLine();
			while ((line = reader.readLine()) != null) {

				String[] ss = line.split(",");

				AreaEntity entity = areaEntityService.newEntity();
				entity.setName(ss[1]);

				entity.setFullName("中国-" + ss[5]);
				entity.setSortNo(0);
				entity.setType(ss[2].equals("1") ? AreaType.province : (ss[2]
						.equals("2") ? AreaType.city
						: (ss[2].equals("3") ? AreaType.region
								: AreaType.region)));

				AreaEntity parent = areaEntityService
						.findByFullNameAndType(
								entity.getFullName()
										.substring(
												0,
												entity.getFullName().length()
														- entity.getName()
																.length() - 1),
								entity.getType() == AreaType.city ? AreaType.province
										: (entity.getType() == AreaType.province ? AreaType.country
												: (entity.getType() == AreaType.region ? AreaType.city
														: AreaType.city)));
				entity.setParent(parent);
				areaEntityService.save(entity);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}