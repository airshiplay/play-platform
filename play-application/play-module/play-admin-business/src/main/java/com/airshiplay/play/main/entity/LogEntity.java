package com.airshiplay.play.main.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.airshiplay.play.repo.jpa.DataEntity;

@Entity
@Table(name = "sys_log")
public class LogEntity extends DataEntity<UserEntity, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 799104163303606659L;

	@Column
	@NotNull
	@Enumerated(EnumType.STRING)
	private LogType type;

	@Lob
	private String text;

	@Column
	@Enumerated(EnumType.ORDINAL)
	private LogLevel level;

	@Column
	private Long ip;

	public enum LogLevel {
		TRACE, DEBUG, INFO, WARN, ERROR, FATAL
	}

	public enum LogType {
		Other, IE11, IE10, IE9, IE8, IE7, IE6, Android, IOS, Chrome, QQ, Maxthon, Green, Firefox, Opera, Safari
	}

	public LogType getType() {
		return type;
	}

	public void setType(LogType type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LogLevel getLevel() {
		return level;
	}

	public void setLevel(LogLevel level) {
		this.level = level;
	}

	public Long getIp() {
		return ip;
	}

	public void setIp(Long ip) {
		this.ip = ip;
	}
	
}
