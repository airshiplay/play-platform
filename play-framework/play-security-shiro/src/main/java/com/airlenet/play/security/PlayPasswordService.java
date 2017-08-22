package com.airlenet.play.security;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.airlenet.play.security.shiro.authc.PlayHashedCredentialsMatcher;

public class PlayPasswordService {

	private SecureRandomNumberGenerator generator;
	@Autowired
	PlayHashedCredentialsMatcher credentialsMatcher;
	public PlayPasswordService() {
		generator = new SecureRandomNumberGenerator();
	}
	 
	public String encryptPassword(Object plaintextPassword,String salt)
			throws IllegalArgumentException {
		SimpleHash hash=new SimpleHash(credentialsMatcher.getHashAlgorithmName(), plaintextPassword, ByteSource.Util.bytes(salt), 1);				 
		if(credentialsMatcher.isStoredCredentialsHexEncoded()){
			return 	hash.toHex();
		}else{
			return 	hash.toBase64();
		}
	}

	public String generatorSalt(){
		return generator.nextBytes().toHex();
	}
	 
	public boolean passwordsMatch(Object submittedPlaintext,String salt, String encrypted) {		
		SimpleHash hash=	new SimpleHash(credentialsMatcher.getHashAlgorithmName(), submittedPlaintext,ByteSource.Util.bytes(salt), 1);				 
		if (credentialsMatcher.isStoredCredentialsHexEncoded()) {
			return hash.toHex().equals(encrypted);
		}			 
		return hash.toBase64().equals(encrypted);
	}

}
