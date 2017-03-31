package com.qq.weixin.mp.model.card;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CardBaseInfo {

	@Getter
	@Setter
	@ToString
	public static class Sku {
		private Long quantity;
		private Long total_quantity;

		public Sku() {
		}

		public Sku(Long quantity) {
			this.quantity = quantity;
		}

	}

	private String id;
	/**
	 * 卡券的商户logo，建议像素为300*300。
	 */
	private String logo_url;
	private String brand_name;
	/**
	 * "CODE_TYPE_TEXT"，文本； "CODE_TYPE_BARCODE"，一维码 ； "CODE_TYPE_QRCODE"，二维码；
	 * "CODE_TYPE_ONLY_QRCODE",二维码无code显示； "CODE_TYPE_ONLY_BARCODE",一维码无code显示；
	 */
	private String code_type;
	private String title;
	private String color;
	private String notice;
	private String service_phone;
	private String description;
	private CardDateInfo date_info;
	private CardBaseInfo.Sku sku;
	private int get_limit;
	private boolean use_custom_code;
	private boolean can_give_friend;
	private List<String> location_id_list;
	private String custom_url_name;
	private String custom_url;
	private String custom_url_sub_title;
	private String promotion_url_name;
	private String promotion_url;
	private String promotion_url_sub_title;
	private boolean need_push_on_view;
	/**
	 * “CARD_STATUS_NOT_VERIFY”,待审核； “CARD_STATUS_VERIFY_FAIL”,审核失败；
	 * “CARD_STATUS_VERIFY_OK”，通过审核； “CARD_STATUS_DELETE”，卡券被商户删除；
	 * “CARD_STATUS_DISPATCH”，在公众平台投放过的卡券；
	 */
	private String status;
}