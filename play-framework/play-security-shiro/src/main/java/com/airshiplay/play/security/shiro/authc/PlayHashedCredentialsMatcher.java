package com.airshiplay.play.security.shiro.authc;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public class PlayHashedCredentialsMatcher extends HashedCredentialsMatcher {

//    private Cache<String, AtomicInteger> passwordRetryCache;

    public PlayHashedCredentialsMatcher() {
    	setHashAlgorithmName(Md5Hash.ALGORITHM_NAME);
//        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
//        String username = (String)token.getPrincipal();
        //retry count + 1
//        AtomicInteger retryCount = passwordRetryCache.get(username);
//        if(retryCount == null) {
//            retryCount = new AtomicInteger(0);
//            passwordRetryCache.put(username, retryCount);
//        }
//        if(retryCount.incrementAndGet() > 5) {
//            //if retry count > 5 throw
//            throw new ExcessiveAttemptsException();
//        }

        boolean matches = super.doCredentialsMatch(token, info);
        if(matches) {
            //clear retry count
//            passwordRetryCache.remove(username);
        }
        return matches;
    }
}
