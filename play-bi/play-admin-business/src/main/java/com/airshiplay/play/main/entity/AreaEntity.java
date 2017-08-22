package com.airshiplay.play.main.entity;

import com.airlenet.play.repo.jpa.HierarchicalEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "sys_area")
public class AreaEntity extends
		HierarchicalEntity<AdminUserEntity, Long, AreaEntity> {

	private static final long serialVersionUID = 4432936174595993994L;

	@NotNull
	@Size(min = 1, max = 50)
	@Column(nullable = false, length = 100)
	private String name;

	@NotNull
	@Size(min = 1, max = 250)
	@Column(nullable = false, length = 500)
	private String fullName;

	@NotNull
	@Enumerated(EnumType.STRING)
	private AreaType type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public AreaType getType() {
		return type;
	}

	public void setType(AreaType type) {
		this.type = type;
	}

	public enum AreaType {
		country("国级"), province("省级"), city("市级"), region("区级");

		private String label;

		AreaType(String label) {
			this.setLabel(label);
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

	}
}
