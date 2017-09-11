package com.airlenet.repo.jpa;

import com.airlenet.repo.domain.Tenant;

public interface MultiTenantAware<T extends Tenant> {

	T getCurrentTenant();
}
