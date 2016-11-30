package com.airshiplay.play.main;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.airshiplay.play.main.entity.LogEntity.LogLevel;
import com.airshiplay.play.main.entity.LogEntity.OperateType;
import com.airshiplay.play.main.service.LogEntityService;
import com.airshiplay.play.util.BrowserUtil;
import com.airshiplay.play.util.IpUtil;

@Service
public class LogService {
	@Autowired
	LogEntityService logEntityService;

	/**
	 * 日志记录
	 * 
	 * @param operateType
	 * @param level
	 * @param content
	 */
	public void addLog(OperateType operateType, LogLevel level, String content) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String browser = BrowserUtil.checkBrowse(request);
		logEntityService.addLog(browser, OperateType.OTHER, LogLevel.FATAL, IpUtil.getIpAddr(request), content);
	}
}
