package com.airshiplay.play.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airshiplay.play.main.entity.UserEntity;
import com.airshiplay.play.main.repo.UserEntityRepository;

@Service
public class UserEntityService extends EntityService<UserEntity, Long> {

	@Autowired
	private UserEntityRepository userEntityRepository;

	@Transactional(readOnly = true)
	public UserEntity findByUsername(String username) {
		return userEntityRepository.findByUsername(username);
	}

	@Transactional(readOnly = true)
	public UserEntity findByEmail(String email) {
		return userEntityRepository.findByEmail(email);
	}

	@Transactional(readOnly = true)
	public UserEntity findByMobile(String mobile) {
		return userEntityRepository.findByMobile(mobile);
	}

}
