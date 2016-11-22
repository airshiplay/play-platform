package com.airshiplay.play.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airshiplay.play.main.entity.AdminUserEntity;
import com.airshiplay.play.main.repo.AdminUserEntityRepository;

@Service
public class UserEntityService extends EntityService<AdminUserEntity, Long> {

	@Autowired
	private AdminUserEntityRepository userEntityRepository;

	@Transactional(readOnly = true)
	public AdminUserEntity findByUsername(String username) {
		return userEntityRepository.findByUsername(username);
	}

	@Transactional(readOnly = true)
	public AdminUserEntity findByEmail(String email) {
		return userEntityRepository.findByEmail(email);
	}

	@Transactional(readOnly = true)
	public AdminUserEntity findByMobile(String mobile) {
		return userEntityRepository.findByMobile(mobile);
	}

}
