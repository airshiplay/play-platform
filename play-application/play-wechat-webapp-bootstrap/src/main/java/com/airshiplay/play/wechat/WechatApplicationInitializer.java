package com.airshiplay.play.wechat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.airshiplay.play.integration.ApplicationInitializer;
import com.airshiplay.play.main.entity.MenuEntity;
import com.airshiplay.play.main.init.InitDataTools;

@Component
public class WechatApplicationInitializer extends ApplicationInitializer {
	@Autowired
	private InitDataTools tools;

	@Override
	public void onRootContextRefreshed() {

		if (!tools.existMenuCode("wechat_management")) {
			int sortNo = 2;
			MenuEntity management = tools.createMenuByParent("公众号管理", "wechat_management", "fa fa-list", null, null, sortNo++, null);
			tools.createMenuByParent("关注用户", "wechat_attentionuser_list", "fa fa-list", "page/wechat/attentionUser/attentionUserList", null, sortNo++, management);
			tools.createMenuByParent("会员管理", "wechat_wechatmember_list", "fa fa-list", "page/wechat/wechatMember/wechatMemberList", null, sortNo++, management);
			tools.createMenuByParent("会员卡", "wechat_membercard_list", "fa fa-list", "page/wechat/memberCard/memberCardList", null, sortNo++, management);

			tools.createMenuByParent("公众号配置", "wechat_wechatconfig_list", "fa fa-list", "page/wechat/wechatConfig/wechatConfigList", null, sortNo++, management);
			tools.createMenuByParent("卡券配置", "wechat_cardcoupon_list", "fa fa-list", "page/wechat/cardCoupon/cardCouponList", null, sortNo++, management);
//			tools.createMenuByParent("会员卡券", "wechat_cardcouponmember_list", "fa fa-list", "page/wechat/cardCouponMember/cardCouponMemberList", null, sortNo++, management);
		}

	}
}
