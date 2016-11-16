package com.airshiplay.play.main.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airshiplay.play.main.entity.LogEntity;
import com.airshiplay.play.main.entity.LogEntity.LogLevel;
import com.airshiplay.play.main.entity.LogEntity.LogType;
import com.airshiplay.play.main.util.LogUtil;
@Service
public class LogEntityService extends EntityService<LogEntity, Long>{

	@Transactional
	public LogEntity save(Throwable ex){
		LogEntity entity = new LogEntity();
		entity.setLevel(LogLevel.ERROR);
		entity.setText(LogUtil.getThrowableString(ex));
		entity.setType(LogType.Chrome);
		return save(entity);
	}
}
