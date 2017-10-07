package com.airlenet.security.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.glassfish.hk2.api.PerLookup;


/**
 * Creates {@link Subject subjects} to be used as injected values.
 * @author airlenet
 * @version 2017-10-04
 */
public class SubjectFactory extends TypeFactory<Subject> {

    public SubjectFactory() {
        super(Subject.class);
    }

    @PerLookup
    @Override
    public Subject provide() {
        return SecurityUtils.getSubject();
    }
}
