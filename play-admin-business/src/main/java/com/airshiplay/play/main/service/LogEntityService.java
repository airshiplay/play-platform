package com.airshiplay.play.main.service;

import com.airlenet.repo.jpa.EntityService;
import com.airshiplay.play.main.api.LogService.LogLevel;
import com.airshiplay.play.main.api.LogService.OperateType;
import com.airshiplay.play.main.entity.LogEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogEntityService extends EntityService<LogEntity, Long> {

	@Transactional
	public LogEntity addLog(String broswer, OperateType operateType, LogLevel level, String ip, String content) {
		LogEntity entity = new LogEntity();
		entity.setLevel(level);
		entity.setText(content);
		entity.setBrowser(broswer);
		entity.setIp(ip);
		entity.setOperateType(operateType);
		return save(entity);
	}

}
