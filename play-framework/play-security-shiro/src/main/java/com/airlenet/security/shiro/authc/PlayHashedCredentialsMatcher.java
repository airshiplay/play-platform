package com.airlenet.security.shiro.authc;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 */
public class PlayHashedCredentialsMatcher extends HashedCredentialsMatcher {
    public static final String WordPress="WordPressPass";
    private Cache<String, AtomicInteger> passwordRetryCache;
    private int retryCount;
    public PlayHashedCredentialsMatcher() {
    	setHashAlgorithmName(Md5Hash.ALGORITHM_NAME);
//        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    public PlayHashedCredentialsMatcher(String hashAlgorithmName) {
        setHashAlgorithmName(hashAlgorithmName);
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

    @Override
    protected Object hashProvidedCredentials(AuthenticationToken token, AuthenticationInfo info) {
        return super.hashProvidedCredentials(token, info);
    }

    @Override
    protected boolean equals(Object tokenCredentials, Object accountCredentials) {
        return super.equals(tokenCredentials, accountCredentials);
    }


    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }
}
