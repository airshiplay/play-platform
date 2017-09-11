package com.airlenet.security.shiro.authz.aop;

import java.lang.annotation.Annotation;

import com.airlenet.security.shiro.authc.annotation.RequiresDataPermissions;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.aop.AuthorizingAnnotationHandler;
import org.apache.shiro.subject.Subject;

public class PermissionDataAnnotationHandler extends
		AuthorizingAnnotationHandler {

	public PermissionDataAnnotationHandler() {
		super(RequiresDataPermissions.class);
	}

	protected String[] getAnnotationValue(Annotation a) {
		RequiresDataPermissions rpAnnotation = (RequiresDataPermissions) a;
		return rpAnnotation.value();
	}

	@Override
	public void assertAuthorized(Annotation a) throws AuthorizationException {
		// TODO Auto-generated method stub
		// RequiresAction rpAnnotation = (RequiresAction)a;
		// do nothing
		if (!(a instanceof RequiresDataPermissions))
			return;

		RequiresDataPermissions rpAnnotation = (RequiresDataPermissions) a;
		String[] perms = getAnnotationValue(a);
		Subject subject = getSubject();
		if (perms.length == 1) {
			subject.checkPermission(perms[0]);
			return;
		}
		if (Logical.AND.equals(rpAnnotation.logical())) {
			getSubject().checkPermissions(perms);
			return;
		}
		if (Logical.OR.equals(rpAnnotation.logical())) {
			// Avoid processing exceptions unnecessarily - "delay" throwing the
			// exception by calling hasRole first
			boolean hasAtLeastOnePermission = false;
			for (String permission : perms)
				if (getSubject().isPermitted(permission))
					hasAtLeastOnePermission = true;
			// Cause the exception if none of the role match, note that the
			// exception message will be a bit misleading
			if (!hasAtLeastOnePermission)
				getSubject().checkPermission(perms[0]);

		}
	}

}