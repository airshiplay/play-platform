package com.qq.weixin.mp.aes;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import com.qq.weixin.WexinHttp;
import com.qq.weixin.mp.api.WexinApiCard;
import com.qq.weixin.mp.model.card.CardBaseInfo;
import com.qq.weixin.mp.model.card.CardDateInfo;
import com.qq.weixin.mp.model.card.CardInfo;
import com.qq.weixin.mp.model.card.CardInfoMember;
import com.qq.weixin.mp.model.card.WexinCardCreateReq;
import com.qq.weixin.mp.model.card.WexinCardCreateResp;
import com.qq.weixin.mp.model.card.WexinCardListReq;
import com.qq.weixin.mp.model.card.WexinCardListResp;
import com.qq.weixin.mp.model.card.WexinCardQrReq;
import com.qq.weixin.mp.model.card.WexinCardQrReq.ActionInfo;
import com.qq.weixin.mp.model.card.WexinCardQrResp;
import com.qq.weixin.mp.model.card.WexinCardReq;
import com.qq.weixin.mp.model.card.WexinCardResp;
import com.qq.weixin.mp.model.card.WexinMediaResp;

public class WexinCardApiTest {

	public static void main(String[] args) throws IOException {
		String appid = "wx2ab7d2c9e9640c8b";
		String appSecret = "15be487a1ac6f3d6aea6c39b88cc35df";
		WexinApiCard api = WexinHttp.getApi(appid, appSecret, WexinApiCard.class);

		// //////////

		createCard(api);
		// ///////////
		// 创建会员卡
		// card_id=pTl2euP3X_0hrEnXfaAnAKvLc-z0
//		 cardQr(api);

//		cardInfo(api);
//		WexinCardListResp resp = api.batchget(new WexinCardListReq(0, 10)).execute().body();
//		System.out.println(resp);
	
	}

	public static void cardInfo(WexinApiCard api) throws IOException {
		WexinCardReq req = new WexinCardReq();
		req.setCard_id("pSLHrjmvm5gOxaVP6Z8uwBmmFpls");
		WexinCardResp resp = api.getCard(req).execute().body();
		System.out.println(resp);
	}

	public static void upload(WexinApiCard api) throws IOException {

		File file = new File("/Users/lig/Documents/workspace/play-platform/play-application/play-wechat-business/AppIcon60X60@2X.png");

		// RequestBody requestBody =
		// RequestBody.create(MediaType.parse("image/png"), file);
		RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("buffer", file.getName(), RequestBody.create(MediaType.parse("image/*"), file)).build();

		WexinMediaResp m = api.uploadImage(requestBody).execute().body();
		System.out.println(m);
		// WexinMediaResp(errcode=0, errmsg=null,
		// url=http://mmbiz.qpic.cn/mmbiz_png/rHH8hmhicB2pDoSQJ8XWiahI7DgpGroawgcqPlsNUz8Nibvb1aWYfxTiafeZxLMcUgPYa7ejASrW5eZtpTkYJSSNGw/0)

		// {"url":"http:\/\/mmbiz.qpic.cn\/mmbiz_png\/rHH8hmhicB2pDoSQJ8XWiahI7DgpGroawgcqPlsNUz8Nibvb1aWYfxTiafeZxLMcUgPYa7ejASrW5eZtpTkYJSSNGw\/0"}
		// http://mmbiz.qpic.cn/mmbiz_png/rHH8hmhicB2pDoSQJ8XWiahI7DgpGroawgcqPlsNUz8Nibvb1aWYfxTiafeZxLMcUgPYa7ejASrW5eZtpTkYJSSNGw/0
	}

	public static void cardQr(WexinApiCard api) throws IOException {
		WexinCardQrReq req = new WexinCardQrReq();
		ActionInfo action_info = new ActionInfo();
		req.setAction_info(action_info);
		req.setAction_name("QR_CARD");
		com.qq.weixin.mp.model.card.WexinCardQrReq.Card card = new com.qq.weixin.mp.model.card.WexinCardQrReq.Card();
		action_info.setCard(card);
		card.setCard_id("pTl2euP3X_0hrEnXfaAnAKvLc-z0");
		WexinCardQrResp resp = api.createCardQR(req).execute().body();
		System.out.println(resp);
		// WexinCardQrResp(errcode=0, errmsg=ok,
		// ticket=gQGt7zwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyZ2tDX2xXZXRhdVUxNnlIRDFxNHIAAgQiOEZYAwSAM_EB,
		// expire_seconds=31536000,
		// url=http://weixin.qq.com/q/02gkC_lWetauU16yHD1q4r,
		// show_qrcode_url=https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQGt7zwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyZ2tDX2xXZXRhdVUxNnlIRDFxNHIAAgQiOEZYAwSAM_EB)

	}

	public static void createCard(WexinApiCard api) throws IOException {

		WexinCardCreateReq req = new WexinCardCreateReq();
		req.setCard(new CardInfo());
		CardInfo card = req.getCard();
		card.setCard_type("MEMBER_CARD");
		card.setMember_card(new CardInfoMember());
		CardInfoMember member_card = card.getMember_card();
		member_card.setBackground_pic_url("http://mmbiz.qpic.cn/mmbiz/iaL1LJM1mF9aRKPZJkmG8xXhiaHqkKSVMMWeN3hLut7X7hicFNjakmxibMLGWpXrEXB33367o7zHN0CwngnQY7zb7g/0");
		CardBaseInfo base_info = new CardBaseInfo();
		member_card.setBase_info(base_info);
		base_info.setLogo_url("http://mmbiz.qpic.cn/mmbiz/iaL1LJM1mF9aRKPZJkmG8xXhiaHqkKSVMMWeN3hLut7X7hicFNjakmxibMLGWpXrEXB33367o7zHN0CwngnQY7zb7g/0");
		base_info.setBrand_name("海底捞");
		base_info.setCode_type("CODE_TYPE_QRCODE");
		base_info.setTitle("海底捞会员卡");
		base_info.setColor("Color010");
		base_info.setNotice("使用时向服务员出示此券");
		base_info.setService_phone("020-88888888");
		base_info.setDescription("不可与其他优惠同享");
		base_info.setDate_info(new CardDateInfo("DATE_TYPE_PERMANENT"));
		base_info.setSku(new CardBaseInfo.Sku(50000000L));
		base_info.setGet_limit(1);
		base_info.setUse_custom_code(false);
		base_info.setCan_give_friend(false);
		base_info.setCustom_url_name("立即使用");
		base_info.setCustom_url("http://weixin.qq.com");
		base_info.setCustom_url_sub_title("6个汉字tips");
		base_info.setPromotion_url_name("营销入口1");
		base_info.setPromotion_url("http://www.qq.com");
		base_info.setNeed_push_on_view(true);
		member_card.setSupply_bonus(true);
		member_card.setSupply_balance(false);
		member_card.setPrerogative("test_prerogative");
		member_card.setAuto_activate(false);
		member_card.setSupply_bonus(true);
//		member_card.setCustom_field1(new CardInfoMember.CustomField("FIELD_NAME_TYPE_ACHIEVEMEN", "童生", "http://www.qq.com"));
//		member_card.setCustom_field2(new CardInfoMember.CustomField("FIELD_NAME_TYPE_COUPON", "查看", "http://www.qq.com"));

		//
		member_card.setActivate_url("http://www.qq.com");
		member_card.setCustom_cell1(new CardInfoMember.CustomCell("个人详情", "查看会员资料", "http://www.xxx.com"));
		member_card.setCustom_cell2(new CardInfoMember.CustomCell("推荐办卡", "查看二维码", "http://www.xxx.com"));
		member_card.setCustom_cell2(new CardInfoMember.CustomCell("会员权益", "", "http://www.xxx.com"));

		CardInfoMember.BonusRule bonus_rule = new CardInfoMember.BonusRule();
		member_card.setBonus_rule(bonus_rule);
		bonus_rule.setCost_money_unit(100);
		bonus_rule.setIncrease_bonus(1);
		bonus_rule.setMax_increase_bonus(200);
		bonus_rule.setInit_increase_bonus(10);
		bonus_rule.setCost_bonus_unit(5);
		bonus_rule.setReduce_money(100);
		bonus_rule.setLeast_money_to_use_bonus(1000);
		bonus_rule.setMax_reduce_bonus(50);
		WexinCardCreateResp resp = api.createCard(req).execute().body();
		System.out.println(resp);
	}
}
