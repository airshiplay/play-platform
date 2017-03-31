package com.airshiplay.play.wechat.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.airshiplay.play.main.entity.AdminUserEntity;
import com.airshiplay.play.repo.jpa.DataEntity;

/**
 * 素材
 * 
 * @author lig
 *
 */
@Entity
@Table(name = "wechat_material")
public class MaterialEntity extends DataEntity<AdminUserEntity, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static enum MaterialType {
		Image("图片"), Voice("语音"), Video("视频"), ImageText("图文消息");
		String label;

		MaterialType(String label) {
			this.label = label;
		}
	}
}
