package com.airshiplay.play.cms;

import com.airlenet.integration.core.ApplicationInitializer;
import com.airshiplay.play.main.entity.AuthorityEntity.PermissionType;
import com.airshiplay.play.main.entity.MenuEntity;
import com.airshiplay.play.main.init.InitDataTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CmsApplicationInitializer extends ApplicationInitializer {
	@Autowired
	private InitDataTools tools;

	@Override
	public void onRootContextRefreshed() {

		
		if (!tools.existMenuCode("cms_management")) {
			int sortNo = 5;
			MenuEntity appManagement = tools
					.createMenuByParent("内容管理", "cms_management",
							"fa fa-wpforms", null, null, sortNo++, null);
			MenuEntity adMenu=tools.createMenuByParent("广告列表", "cms_ad_list", "fa fa-cloud",
					"page/cms/ad/adList", null, sortNo++, appManagement);
			tools.createPemission(adMenu, PermissionType.page, "广告列表", "page:cms:ad:read");
			tools.createPemission(adMenu, PermissionType.page, "广告创建", "page:cms:ad:create");

			
			MenuEntity adPositionMenu=tools.createMenuByParent("广告位列表", "cms_adposition_list", "fa fa-cloud",
					"page/cms/adPosition/adPositionList", null, sortNo++, appManagement);
			tools.createPemission(adPositionMenu, PermissionType.page, "广告位列表", "page:cms:adposition:read");
			tools.createPemission(adPositionMenu, PermissionType.page, "广告位创建", "page:cms:adposition:create");
			tools.createPemission(adPositionMenu, PermissionType.page, "广告位编辑", "page:cms:adposition:update");
			tools.createPemission(adPositionMenu, PermissionType.page, "广告位删除", "page:cms:adposition:delete");

			MenuEntity articleCategoryMenu=tools.createMenuByParent("文章分类列表", "cms_articleCategory_list", "glyphicon glyphicon-list	",
					"page/cms/articleCategory/articleCategoryList", null, sortNo++, appManagement);
			tools.createPemission(articleCategoryMenu, PermissionType.page, "文章分类列表", "page:cms:articleCategory:read");
			tools.createPemission(articleCategoryMenu, PermissionType.page, "文章分类创建", "page:cms:articleCategory:create");
			tools.createPemission(articleCategoryMenu, PermissionType.page, "文章分类编辑", "page:cms:articleCategory:update");
			tools.createPemission(articleCategoryMenu, PermissionType.page, "文章分类删除", "page:cms:articleCategory:delete");
			
			MenuEntity articleMenu=tools.createMenuByParent("文章列表", "cms_article_list", "fa fa-file-text",
					"page/cms/article/articleList", null, sortNo++, appManagement);

			
			
			MenuEntity articleTagMenu=tools.createMenuByParent("文章标签列表", "cms_articleTag_list", "glyphicon glyphicon-list",
					"page/cms/articleTag/articleTagList", null, sortNo++, appManagement);
		}

	}
}
