package com.airshiplay.play.main.entity;

import com.airlenet.repo.jpa.DataEntity;
import com.airshiplay.play.main.api.LogService.LogLevel;
import com.airshiplay.play.main.api.LogService.OperateType;

import javax.persistence.*;

@Entity
@Table(name = "sys_log")
public class LogEntity extends DataEntity<AdminUserEntity, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 799104163303606659L;

	@Column
	private String browser;

	@Lob
	private String text;

	@Column
	@Enumerated(EnumType.ORDINAL)
	private LogLevel level;

	@Column
	private String ip;

	@Column
	@Enumerated(EnumType.ORDINAL)
	private OperateType operateType;

//	public enum LogLevel {
//		TRACE, DEBUG, INFO, WARN, ERROR, FATAL
//	}
//
//	public enum OperateType {
//		LOGIN, EXIT, INSERT, DEL, UPDATE,VIEW, UPLOAD, OTHER
//	}

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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public OperateType getOperateType() {
		return operateType;
	}

	public void setOperateType(OperateType operateType) {
		this.operateType = operateType;
	}

}
