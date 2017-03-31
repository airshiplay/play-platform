package com.airshiplay.play.wechat.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airshiplay.play.main.api.LogService;
import com.airshiplay.play.main.api.LogService.LogLevel;
import com.airshiplay.play.main.api.LogService.OperateType;
import com.airshiplay.play.wechat.entity.AttentionReplyEntity;
import com.airshiplay.play.wechat.entity.AttentionUserEntity;
import com.airshiplay.play.wechat.entity.AttentionUserSubscribeEntity;
import com.airshiplay.play.wechat.entity.CardCouponEntity;
import com.airshiplay.play.wechat.entity.CardCouponMemberEntity;
import com.airshiplay.play.wechat.entity.MemberCardEntity;
import com.airshiplay.play.wechat.entity.MessageType;
import com.airshiplay.play.wechat.entity.QAttentionUserEntity;
import com.airshiplay.play.wechat.entity.QCardCouponEntity;
import com.airshiplay.play.wechat.entity.QCardCouponMemberEntity;
import com.airshiplay.play.wechat.entity.QWechatMessageEntity;
import com.airshiplay.play.wechat.entity.WechatConfigEntity;
import com.airshiplay.play.wechat.entity.WechatMemberEntity;
import com.airshiplay.play.wechat.entity.WechatMessageEntity;
import com.qq.weixin.mp.model.msg.receive.WexinMsgEventCard;
import com.qq.weixin.mp.model.msg.receive.WexinMsgEventCardUserGet;
import com.qq.weixin.mp.model.msg.receive.WexinMsgEventClick;
import com.qq.weixin.mp.model.msg.receive.WexinMsgEventSubscribe;
import com.qq.weixin.mp.model.msg.receive.WexinMsgEventView;
import com.qq.weixin.mp.model.msg.receive.WexinMsgFactory;
import com.qq.weixin.mp.model.msg.receive.WexinMsgFactory.WechatMsgParent;
import com.qq.weixin.mp.model.msg.receive.WexinMsgText;

/**
 * 微信公众平台 监听消息处理
 * 
 * @author lig
 *
 */
@Service
public class WeixinMsgService extends WeixinService {
	@Autowired
	private LogService logService;

	@Autowired
	private WechatMessageEntityService wechatMessageEntityService;

	@Autowired
	private CardCouponEntityService cardCouponEntityService;
	@Autowired
	private CardCouponMemberEntityService cardCouponMemberEntityService;
	@Autowired
	private AttentionUserEntityService attentionUserEntityService;

	@Autowired
	private AttentionUserSubscribeEntityService attentionUserSubscribeEntityService;

	@Autowired
	private AttentionReplyEntityService attentionReplyEntityService;
	@Autowired
	private WechatMemberEntityService wechatMemberEntityService;
	@Autowired
	private MemberCardEntityService memberCardEntityService;

	public void updateCardEvent(WexinMsgEventCard msg) {
		String cardId = msg.getCardId();
		CardCouponEntity entity = cardCouponEntityService.findOne(QCardCouponEntity.cardCouponEntity.cardId.eq(cardId));
		if (entity != null) {
			entity.setStatus(msg.getRefuseReason());
		}
		cardCouponEntityService.save(entity);
		logService.addLog(OperateType.UPDATE, LogLevel.INFO, "卡券更新状态：" + msg.getRefuseReason());
	}

	public void userGetCardEvent(WexinMsgEventCardUserGet msg) {// 用户领取卡片
		String openid = msg.getFromUserName();
		AttentionUserEntity attentionUserEntity = attentionUserEntityService.findByWechatIdAndOpenId(msg.getToUserName(), openid);

		msg.getUserCardCode();
		WechatMemberEntity entity = new WechatMemberEntity();
		if (attentionUserEntity != null) {
			entity.setNickname(attentionUserEntity.getNickname());
			entity.setPhoto(attentionUserEntity.getHeadimgurl());

			entity.setSex(attentionUserEntity.getSex() + "");
		}

		entity.setOpenid(openid);
		wechatMemberEntityService.save(entity);

		MemberCardEntity memberCardEntity = new MemberCardEntity();
		memberCardEntity.setCardId(msg.getCardId());
		memberCardEntity.setCode(msg.getUserCardCode());
		memberCardEntity.setMemberUser(entity);
		memberCardEntityService.save(memberCardEntity);
	}

	public WexinMsgFactory.WechatMsgParent subscribeEvent(WexinMsgEventSubscribe msg) {
		if ("subscribe".equals(msg.getEvent())) {// 关注用户处理

			AttentionUserEntity entity = attentionUserEntityService.findOne(QAttentionUserEntity.attentionUserEntity.openid.eq(msg.getFromUserName()));
			if (entity == null) {
				entity = attentionUserEntityService.newEntity();
				entity.setOpenid(msg.getFromUserName());
			}
			entity.setStatus(1);
			attentionUserEntityService.save(entity);

			AttentionUserSubscribeEntity subEntity = new AttentionUserSubscribeEntity();
			subEntity.setAttentionUser(entity);
			subEntity.setStatus(1);
			subEntity.setSubscribeTime(new Date(msg.getCreateTime() * 1000));
			attentionUserSubscribeEntityService.save(subEntity);

			// 关注回复消息
			AttentionReplyEntity replyEntity = attentionReplyEntityService.findOneByWechatId(msg.getToUserName());
			if (replyEntity != null) {
				if (replyEntity.getMsgType() == MessageType.Text) {
					WexinMsgText reply = new WexinMsgText(msg.getFromUserName(), msg.getToUserName());
					reply.setContent(replyEntity.getText().getContent());
					return reply;
				}
			}

		} else if ("unsubscribe".equals(msg.getEvent())) {// 取消关注用户处理
			AttentionUserEntity entity = attentionUserEntityService.findOne(QAttentionUserEntity.attentionUserEntity.openid.eq(msg.getFromUserName()));
			if (entity == null) {
				entity = attentionUserEntityService.newEntity();
				entity.setOpenid(msg.getFromUserName());
			}
			entity.setStatus(2);
			attentionUserEntityService.save(entity);
			AttentionUserSubscribeEntity subEntity = new AttentionUserSubscribeEntity();
			subEntity.setAttentionUser(entity);
			subEntity.setStatus(2);
			subEntity.setSubscribeTime(new Date(msg.getCreateTime() * 1000));
			attentionUserSubscribeEntityService.save(subEntity);

		}
		return null;
	}

	public WexinMsgFactory.WechatMsgParent handlerTextMsg(WechatConfigEntity config, WexinMsgText textmsg) {
		String content = textmsg.getContent();
		WechatMsgParent result = defaultReply(config);
		return result;
	}

	public WechatMsgParent menuClickView(WexinMsgEventView msg) {
		return null;
	}

	public WechatMsgParent menuClick(WexinMsgEventClick msg) {
		return null;
	}

	public WechatMsgParent defaultReply(WechatConfigEntity config) {
		return null;
	}

	public WechatMsgParent handlerMessage(WechatConfigEntity config, WechatMsgParent msg, String body) {
		WechatMessageEntity entity = wechatMessageEntityService.findOne(QWechatMessageEntity.wechatMessageEntity.msgId.eq(msg.getMsgId()));
		if (entity != null) {
			return null;
		}
		AttentionUserEntity attentionUser = attentionUserEntityService.findByWechatIdAndOpenId(msg.getToUserName(), msg.getFromUserName());
		entity = wechatMessageEntityService.newEntity();
		entity.setDetail(body);
		entity.setToUsername(msg.getToUserName());
		entity.setFromUsername(msg.getFromUserName());
		entity.setCreateTime(new Date(msg.getCreateTime() * 1000));
		entity.setMsgId(msg.getMsgId());
		entity.setMsgType(msg.getMsgType());
		entity.setTimestamp(msg.getTimeStamp() == null ? null : new Date(msg.getTimeStamp() * 1000));
		entity.setConfig(config);
		entity.setUser(attentionUser);
		wechatMessageEntityService.save(entity);
		WechatMsgParent result = null;
		if (msg instanceof WexinMsgText) {// 文本消息处理
			WexinMsgText textmsg = (WexinMsgText) msg;
			result = handlerTextMsg(config, textmsg);
		} else if (msg instanceof WexinMsgEventCard) {// 卡券审核
			updateCardEvent((WexinMsgEventCard) msg);
		} else if (msg instanceof WexinMsgEventCardUserGet) {// 用户领取卡券
			userGetCardEvent((WexinMsgEventCardUserGet) msg);
		} else if (msg instanceof WexinMsgEventSubscribe) {// 关注 、取消关注处理
			result = subscribeEvent((WexinMsgEventSubscribe) msg);
		} else if (msg instanceof WexinMsgEventView) {// 点击菜单跳转链接时的事件推送
			result = menuClickView((WexinMsgEventView) msg);
		} else if (msg instanceof WexinMsgEventClick) {// 点击菜单拉取消息时的事件推送
			result = menuClick((WexinMsgEventClick) msg);
		} else {
			logService.addLog(OperateType.OTHER, LogLevel.WARN, "不支持微信消息：" + body);
			result = defaultReply(config);
		}
		if (result != null) {
			WechatMessageEntity replyEntity = wechatMessageEntityService.newEntity();
			replyEntity.setDetail(result.toXmlString());
			replyEntity.setToUsername(result.getToUserName());
			replyEntity.setFromUsername(result.getFromUserName());
			replyEntity.setCreateTime(new Date(result.getCreateTime() * 1000));
			replyEntity.setMsgId(result.getMsgId());
			replyEntity.setMsgType(result.getMsgType());
			replyEntity.setConfig(config);
			replyEntity.setUser(attentionUser);
			wechatMessageEntityService.save(replyEntity);
		}
		return result;
	}

}
