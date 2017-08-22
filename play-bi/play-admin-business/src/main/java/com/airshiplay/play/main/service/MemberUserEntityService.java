package com.airshiplay.play.main.service;

import com.airshiplay.play.main.entity.MemberUserEntity;
import com.airshiplay.play.main.repo.MemberUserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class MemberUserEntityService extends EntityService<MemberUserEntity, Long> {

	@Autowired
	private MemberUserEntityRepository memberUserEntityRepository;

	@Transactional(readOnly = true)
	public MemberUserEntity findByUsername(String username) {
		return memberUserEntityRepository.findByUsername(username);
	}

	@Transactional(readOnly = true)
	public MemberUserEntity findByEmail(String email) {
		return memberUserEntityRepository.findByEmail(email);
	}

	@Transactional(readOnly = true)
	public MemberUserEntity findByMobile(String mobile) {
		return memberUserEntityRepository.findByMobile(mobile);
	}
}
