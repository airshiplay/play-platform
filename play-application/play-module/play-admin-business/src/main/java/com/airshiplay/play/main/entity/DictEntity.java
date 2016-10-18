package com.airshiplay.play.main.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.airshiplay.play.repo.jpa.HierarchicalEntity;

@Entity
@Table(name = "sys_dict")
public class DictEntity extends
		HierarchicalEntity<UserEntity, Long, DictEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4840837819456431560L;

	/**
	 * 编码、类型
	 */
	private String type;

	/**
	 * 标签名称
	 */
	@NotNull
	@Size(min = 1, max = 50)
	@Column(nullable = false, length = 50)
	private String label;

	private String name;
	/**
	 * 键值
	 */
	private int value;

	private String description;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
