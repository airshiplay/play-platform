package com.airshiplay.play.security.shiro;

import java.io.Serializable;
import java.util.Set;

import com.airshiplay.play.security.CustomUserDetails;

/**
 * <p>
 * User: Zhang Kaitao
 * <p>
 * Date: 14-1-28
 * <p>
 * Version: 1.0
 */
public interface PlayShiroUserDetailsService {

	/**
	 * 根据用户名查找用户
	 * 
	 * @param username
	 * @return
	 */
	public CustomUserDetails<?, ?> findByUsername(String username);

	public Set<String> findRoles(String username,Serializable uid);
	
	public Set<String> findPermissions(String username,Serializable uid);
	
}
