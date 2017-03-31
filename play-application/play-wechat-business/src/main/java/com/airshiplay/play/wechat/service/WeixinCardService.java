package com.airshiplay.play.wechat.service;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airshiplay.play.filestorage.FileStorageService;
import com.airshiplay.play.main.api.LogService;
import com.airshiplay.play.main.api.LogService.LogLevel;
import com.airshiplay.play.main.api.LogService.OperateType;
import com.airshiplay.play.wechat.entity.CardCouponEntity;
import com.airshiplay.play.wechat.entity.CardCouponMemberEntity;
import com.airshiplay.play.wechat.entity.CardCouponType;
import com.airshiplay.play.wechat.entity.MemberCardEntity;
import com.airshiplay.play.wechat.entity.QCardCouponEntity;
import com.airshiplay.play.wechat.entity.QCardCouponMemberEntity;
import com.airshiplay.play.wechat.entity.WechatConfigEntity;
import com.qq.weixin.WechatServiceException;
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
import com.qq.weixin.mp.model.card.WexinCardReq;
import com.qq.weixin.mp.model.card.WexinCardResp;
import com.qq.weixin.mp.model.card.WexinMediaResp;

/**
 * 公众平台，卡券管理
 * 
 * @author lig
 *
 */
@Service
public class WeixinCardService extends WeixinService {
	@Autowired
	LogService logService;
	@Autowired
	FileStorageService fileStorageService;

	@Autowired
	WechatConfigEntityService wechatConfigEntityService;

	@Autowired
	CardCouponMemberEntityService cardCouponMemberEntityService;

	@Autowired
	CardCouponEntityService cardCouponEntityService;

	
	public void createCard(Long configId, Long cardCouponId) {
		CardCouponEntity entity = cardCouponEntityService.findOne(cardCouponId);
		if(entity.getCardType()==CardCouponType.MEMBER_CARD){
			createMemberCard(configId,cardCouponId);
		}
	}
	/**
	 * 
	 * @param configId
	 * @param CardCouponMemberId
	 */
	public void createMemberCard(Long configId, Long cardCouponId) {
		WechatConfigEntity config = wechatConfigEntityService.findOne(configId);
		WexinApiCard api = WexinHttp.getApi(config.getAppId(), config.getAppSecret(), WexinApiCard.class);

		try {
			CardCouponMemberEntity entity = cardCouponMemberEntityService.findOne(QCardCouponMemberEntity.cardCouponMemberEntity.cardCoupon.id.eq(cardCouponId));

			if (StringUtils.isEmpty(entity.getWxbackgroudUrl())) {
				File file = new File(fileStorageService.convertToPath(entity.getBackgroudUrl()));
				WexinMediaResp mediaResp = api
						.uploadImage(new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("buffer", file.getName(), RequestBody.create(MediaType.parse("image/*"), file)).build())
						.execute().body();
				entity.setWxbackgroudUrl(mediaResp.getUrl());
			}
			if (StringUtils.isEmpty(entity.getWxlogoUrl())) {
				File file = new File(fileStorageService.convertToPath(entity.getLogoUrl()));
				WexinMediaResp mediaResp = api
						.uploadImage(new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("buffer", file.getName(), RequestBody.create(MediaType.parse("image/*"), file)).build())
						.execute().body();
				entity.setWxlogoUrl(mediaResp.getUrl());
			}
			WexinCardCreateReq req = new WexinCardCreateReq();
			req.setCard(new CardInfo());
			CardInfo card = req.getCard();
			card.setCard_type("MEMBER_CARD");
			card.setMember_card(new CardInfoMember());
			CardInfoMember member_card = card.getMember_card();
			member_card.setBackground_pic_url(entity.getWxbackgroudUrl());//
			CardBaseInfo base_info = new CardBaseInfo();
			member_card.setBase_info(base_info);
			base_info.setLogo_url(entity.getWxlogoUrl());// http://mmbiz.qpic.cn
			base_info.setBrand_name(entity.getBrandName());
			base_info.setCode_type("CODE_TYPE_QRCODE");
			base_info.setTitle(entity.getTitle());
			base_info.setColor(entity.getColor());
			base_info.setNotice(entity.getNotice());
			base_info.setService_phone(entity.getServicePhone());
			base_info.setDescription(entity.getDescription());
			base_info.setDate_info(new CardDateInfo("DATE_TYPE_PERMANENT"));
			base_info.setSku(new CardBaseInfo.Sku(entity.getSkuQuantity()));
			base_info.setGet_limit(1);
			base_info.setUse_custom_code(false);
			base_info.setCan_give_friend(true);
			// base_info.setLocation_id_list(Lists.newArrayList("123",
			// "12321"));
			base_info.setCustom_url_name("立即使用");
			base_info.setCustom_url("http://weixin.qq.com");
			base_info.setCustom_url_sub_title("6个汉字tips");
			base_info.setPromotion_url_name("营销入口1");
			base_info.setPromotion_url("http://www.qq.com");
			base_info.setNeed_push_on_view(true);
			member_card.setSupply_bonus(true);
			member_card.setSupply_balance(false);
			member_card.setPrerogative(entity.getPrerogative());
			member_card.setAuto_activate(false);
			member_card.setSupply_bonus(true);
			member_card.setCustom_field1(new CardInfoMember.CustomField("FIELD_NAME_TYPE_LEVEL", "http://www.qq.com"));
			// member_card.setCustom_field2(new
			// CardMember.CustomField("FIELD_NAME_TYPE_COUPON",
			// "http://www.qq.com"));

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
			if (resp.getErrcode() != 0) {
				cardCouponMemberEntityService.save(entity);
				logService.addLog(OperateType.UPDATE, LogLevel.ERROR, "创建会员卡券失败：" + resp.getErrcode() + resp.getErrmsg());
				throw new WechatServiceException(resp.getErrcode(), resp.getErrmsg());
			}

			entity.setCardId(resp.getCard_id());
			cardCouponMemberEntityService.save(entity);
			CardCouponEntity cardCouponEntity = entity.getCardCoupon();
			cardCouponEntity.setCardId(resp.getCard_id());
			cardCouponEntityService.save(cardCouponEntity);

			logService.addLog(OperateType.UPDATE, LogLevel.INFO, "创建会员卡券成功 card_id：" + resp.getCard_id());

		} catch (IOException e) {
			throw new WechatServiceException("创建会员卡券失败:" + e.getMessage(), e);
		}

	}

	@Transactional
	public void getCard(Long configId, Long cardCouponId) {
		WechatConfigEntity config = wechatConfigEntityService.findOne(configId);
		WexinApiCard api = WexinHttp.getApi(config.getAppId(), config.getAppSecret(), WexinApiCard.class);
		CardCouponEntity entity = cardCouponEntityService.findOne(cardCouponId);
		if (StringUtils.isEmpty(entity.getCardId())) {
			throw new WechatServiceException(0, "卡券没有上传微信");
		}
		try {
			WexinCardReq req = new WexinCardReq();
			req.setCard_id(entity.getCardId());
			WexinCardResp resp = api.getCard(req).execute().body();
			if (resp.getErrcode() != 0) {
				logService.addLog(OperateType.UPDATE, LogLevel.ERROR, "获取卡券信息失败：" + resp.getErrcode() + resp.getErrmsg());
				throw new WechatServiceException(resp.getErrcode(), resp.getErrmsg());
			}
			CardInfo card = resp.getCard();

			if (card.getCard_type().equals(CardCouponType.MEMBER_CARD.name())) {
				CardInfoMember memberCard = resp.getCard().getMember_card();
				CardCouponMemberEntity memberCardEntity = entity.getMemberCard();
				if(memberCardEntity==null){
					memberCardEntity = cardCouponMemberEntityService.newEntity();
				}
				memberCardEntity.setTitle(memberCard.getBase_info().getTitle());
				memberCardEntity.setCardCoupon(entity);
				cardCouponMemberEntityService.save(memberCardEntity);

				entity.setStatus(memberCard.getBase_info().getStatus());
				entity.setTitle(memberCard.getBase_info().getTitle());
				entity.setQuantity(memberCard.getBase_info().getSku().getQuantity());
			}

			entity.setCardType(CardCouponType.valueOf(card.getCard_type()));
			
			cardCouponEntityService.save(entity);

			logService.addLog(OperateType.UPDATE, LogLevel.INFO, "获取卡券信息成功 card_id：" + entity.getCardId());
		} catch (IOException e) {
			throw new WechatServiceException("获取卡券信息失败：", e);
		}
	}

	public void getCardList(Long configId) {
		WechatConfigEntity config = wechatConfigEntityService.findOne(configId);
		WexinApiCard api = WexinHttp.getApi(config.getAppId(), config.getAppSecret(), WexinApiCard.class);
		int offset = 0;
		int count = 10;
		int total = 0;
		try {
			do {
				WexinCardListReq req = new WexinCardListReq(offset, count);
				WexinCardListResp resp = api.batchget(req).execute().body();
				if (resp.getErrcode() != 0) {
					logService.addLog(OperateType.UPDATE, LogLevel.ERROR, "获取卡券列表：" + resp.getErrcode() + resp.getErrmsg());
					throw new WechatServiceException(resp.getErrcode(), resp.getErrmsg());
				}
				for (String cardId : resp.getCard_id_list()) {
					if (null == cardCouponEntityService.findOne(QCardCouponEntity.cardCouponEntity.cardId.eq(cardId))) {
						CardCouponEntity entity = new CardCouponEntity();
						entity.setCardId(cardId);
						cardCouponEntityService.save(entity);
					}
				}
			} while (offset * count < total);
		} catch (IOException e) {
			throw new WechatServiceException("同步会员卡列表失败：", e);
		}
	}
}
