//package com.airshiplay.play.main.security;
//
//import org.apache.shiro.authc.credential.DefaultPasswordService;
//import org.apache.shiro.crypto.SecureRandomNumberGenerator;
//import org.apache.shiro.crypto.hash.DefaultHashService;
//import org.apache.shiro.crypto.hash.HashRequest;
//import org.apache.shiro.util.ByteSource;
//import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.google.common.base.Strings;
//import com.airshiplay.play.main.entity.UserEntity;
//
//@Service
//public class PasswordService extends DefaultPasswordService{
//
//	SecureRandomNumberGenerator generator;
////	@Autowired
////	private PasswordEncoder passwordEncoder;
////
//	public boolean changeUserPassword(UserEntity user, String oldPassword,
//			String newPassword) {	 
//		generator =new SecureRandomNumberGenerator();
//		String salt=generator.nextBytes().toHex();
//		encryptPassword(newPassword);
//		HashRequest request=	new HashRequest.Builder().setSalt(salt).setAlgorithmName(DEFAULT_HASH_ALGORITHM).setSource(newPassword).build();
//		
//		
//		createHashRequest(ByteSource.Util.bytes(newPassword));
//		String password=	((DefaultHashService)getHashService()).computeHash(request).toHex();
////		boolean matched = Strings.isNullOrEmpty(user.getPassword()) ? true
//				: passwordEncoder.matches(oldPassword, user.getPassword());
////		if (!matched) {
////			return false;
////		}
////
//		user.setSalt(salt);
//		user.setPassword(password);
////		user.setPassword(passwordEncoder.encode(newPassword));
//		return true;
//	}
////
////	public String encode(String password) {
////		return passwordEncoder.encode(password);
////	}
////
////	public boolean matches(String rawPassword, String encodedPassword) {
////		return passwordEncoder.matches(rawPassword, encodedPassword);
////	}
//}
