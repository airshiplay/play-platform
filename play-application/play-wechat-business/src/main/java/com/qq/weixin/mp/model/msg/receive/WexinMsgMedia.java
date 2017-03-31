package com.qq.weixin.mp.model.msg.receive;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import com.qq.weixin.mp.model.msg.receive.WexinMsgFactory;

/**
 * <pre>
 * <xml><ToUserName><![CDATA[gh_06e069f27b90]]></ToUserName>
 * <FromUserName><![CDATA[oTl2euLs6iswJU5kt2H4FtLZAiZk]]></FromUserName>
 * <CreateTime>1481601955</CreateTime>
 * <MsgType><![CDATA[video]]></MsgType>
 * <MediaId><![CDATA[d4VM_PyClntDaibb857VFYujWf3GPkglZI5cEGc70sP3Rum9JLkvqXMsh4tDF1dT]]></MediaId>
 * <ThumbMediaId><![CDATA[iaopkNPhGG1uPm1J9z8Ff8O9fqKxbacI_fYRz_8jB6tNTOgNEfAKMh9VNcQcktcZ]]></ThumbMediaId>
 * <MsgId>6363431943133976453</MsgId>
 * </xml>
 * </pre>
 * 
 * <pre>
 * <xml><ToUserName><![CDATA[gh_06e069f27b90]]></ToUserName>
 * <FromUserName><![CDATA[oTl2euLs6iswJU5kt2H4FtLZAiZk]]></FromUserName>
 * <CreateTime>1481602175</CreateTime>
 * <MsgType><![CDATA[link]]></MsgType>
 * <Title><![CDATA[两步实现类似格瓦拉的转场动画]]></Title>
 * <Description><![CDATA[本篇来介绍格瓦拉的转场动画效果。刚开始以为是Android5.0以上才有，后面用4.4的机子发现也有这种的效果。]]></Description>
 * <Url><![CDATA[http://mp.weixin.qq.com/s?__biz=MzA3MDMyMjkzNg==&mid=2652262085&idx=1&sn=f28850033282bd59b12049024527b9a9&chksm=84dc7152b3abf8446e38a206c7ab19da4f7d616270ba0d8fde0faf638bca95be6aa6c6cf4ce4&scene=0#rd]]></Url>
 * <MsgId>6363432888026785995</MsgId>
 * </xml>
 * </pre>
 * 
 * @author lig
 *
 */
@Root(name = "xml")
public class WexinMsgMedia extends WexinMsgFactory.WechatMsgParent {

	@Element(name = "PicUrl", data = true)
	private String picUrl;

	@Element(name = "MediaId", data = true)
	private String mediaId;

	@Element(name = "Format", data = true)
	private String format;

	@Element(name = "Recognition", data = true)
	private String recognition;

	@Element(name = "ThumbMediaId", data = true)
	private String thumbMediaId;

	@Element(name = "Title", data = true)
	private String title;

	@Element(name = "Description", data = true)
	private String description;

	@Element(name = "Url", data = true)
	private String url;

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

}
