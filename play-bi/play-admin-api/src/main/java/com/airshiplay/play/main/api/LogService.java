package com.airshiplay.play.main.api;

public interface LogService {
	public void addLog(OperateType operateType, LogLevel level, String content);

	public void addLog(OperateType operateType, LogLevel level, Throwable thr);

	public void addLog(OperateType operateType, LogLevel level, String content, Throwable thr);

	public enum LogLevel {
		TRACE, DEBUG, INFO, WARN, ERROR, FATAL
	}

	public enum OperateType {
		LOGIN, EXIT, INSERT, DEL, UPDATE, VIEW, UPLOAD, OTHER
	}
}
