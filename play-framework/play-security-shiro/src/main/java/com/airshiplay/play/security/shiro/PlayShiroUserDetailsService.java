package com.airshiplay.play.security.shiro;

import java.io.Serializable;
import java.util.Set;

import com.airshiplay.play.security.CustomUserDetails;

/**
 * 
 * @author lig
 *
 */
public interface PlayShiroUserDetailsService {

	/**
	 * 根据用户名查找用户
	 * 
	 * @param username
	 * @return
	 */
	public CustomUserDetails<?, ?> findByUsername(String username);

	public CustomUserDetails<?, ?> findAdminUserByUsername(String username);

	public CustomUserDetails<?, ?> findMemberUserByUsername(String username);

	
	public Set<String> findRoles(String username, Serializable uid);

	public Set<String> findAdminRoles(String username, Serializable uid);

	public Set<String> findMemberRoles(String username, Serializable uid);

	
	
	public Set<String> findPermissions(String username, Serializable uid);

	public Set<String> findAdminPermissions(String username, Serializable uid);

	public Set<String> findMemberPermissions(String username, Serializable uid);

}
