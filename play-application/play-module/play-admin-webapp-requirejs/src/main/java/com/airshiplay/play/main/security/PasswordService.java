package com.airshiplay.play.main.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.airshiplay.play.main.entity.UserEntity;

@Service
public class PasswordService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	public boolean changeUserPassword(UserEntity user, String oldPassword,
			String newPassword) {
		boolean matched = Strings.isNullOrEmpty(user.getPassword()) ? true
				: passwordEncoder.matches(oldPassword, user.getPassword());
		if (!matched) {
			return false;
		}

		user.setPassword(passwordEncoder.encode(newPassword));
		return true;
	}

	public String encode(String password) {
		return passwordEncoder.encode(password);
	}

	public boolean matches(String rawPassword, String encodedPassword) {
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}
}
