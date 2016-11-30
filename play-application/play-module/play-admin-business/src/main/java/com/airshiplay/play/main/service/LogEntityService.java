package com.airshiplay.play.main.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airshiplay.play.main.entity.LogEntity;
import com.airshiplay.play.main.entity.LogEntity.LogLevel;
import com.airshiplay.play.main.entity.LogEntity.OperateType;

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
