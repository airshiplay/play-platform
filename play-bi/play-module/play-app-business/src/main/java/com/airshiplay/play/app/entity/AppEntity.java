package com.airshiplay.play.app.entity;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.airshiplay.play.main.entity.AdminUserEntity;
import com.airshiplay.play.repo.jpa.DataEntity;

@Entity
@Table(name = "app_app")
public class AppEntity extends DataEntity<AdminUserEntity, Long> {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Platform platform;
	@NotNull
	@Enumerated(EnumType.STRING)
	private AppSource source;

	@Column(length = 4096)
	private String description;

	@Column(length = 20)
	private String version;
	
	@NotNull
	@Column(length = 100)
	private String name;

	@NotNull
	@Column(length = 20)
	private String trackId;

	@NotNull
	@Column()
	private Long fileSizeBytes;

	@Column(length = 200)
	private String iconPath;

	@Column(length = 45)
	private String identifier;

	@ElementCollection  
	@CollectionTable(name="app_photo", joinColumns=@JoinColumn(name="app_id"))  
	@Column(name="photo", length=200)
	private List<String> photos;
	
	public enum Platform {
		ios, android, windows, wechat
	}

	public enum AppSource {
		enterprise, store
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	public AppSource getSource() {
		return source;
	}

	public void setSource(AppSource source) {
		this.source = source;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTrackId() {
		return trackId;
	}

	public void setTrackId(String trackId) {
		this.trackId = trackId;
	}

	public Long getFileSizeBytes() {
		return fileSizeBytes;
	}

	public void setFileSizeBytes(Long fileSizeBytes) {
		this.fileSizeBytes = fileSizeBytes;
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public List<String> getPhotos() {
		return photos;
	}

	public void setPhotos(List<String> photos) {
		this.photos = photos;
	}

}
