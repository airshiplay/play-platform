package com.airshiplay.play.main.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.Map;

/**
 * @author airlenet
 * @version 2017-09-12
 */
@Controller
@RequestMapping("/sysinfo")
public class InfoRestController {

	@Value("${info.system_name?:play platform}")
	private String systemName;

	@Value("${info.system_version?:1.0}")
	private String systemVersion;

	@Value("${info.company_name?:airlenet}")
	private String companyName;
	
	@Autowired
	private ServletContext servletContext;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> get() {
		Map<String, Object> appInfo = new HashMap<>();
		appInfo.put("system_name", systemName);
		appInfo.put("system_version", systemVersion);
		appInfo.put("company_name", companyName);
		
		appInfo.put("javaVersion", System.getProperty("java.version"));
		appInfo.put("javaHome", System.getProperty("java.home"));
		appInfo.put("osName", System.getProperty("os.name"));
		appInfo.put("osArch", System.getProperty("os.arch"));
		appInfo.put("serverInfo", servletContext.getServerInfo());
		appInfo.put("servletVersion", servletContext.getMajorVersion() + "." + servletContext.getMinorVersion());
		
		return appInfo;
	}

}
