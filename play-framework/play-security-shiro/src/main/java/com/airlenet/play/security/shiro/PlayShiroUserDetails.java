package com.airlenet.play.security.shiro;

import java.io.Serializable;

public interface PlayShiroUserDetails extends Serializable {

	
	boolean getLocked();

	String getCredentialsSalt();

	String getUsername();

	String getPassword();

}
