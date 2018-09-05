package com.airlenet.data.jpa;

import com.airlenet.data.domain.Tenant;

public interface MultiTenantAware<T extends Tenant> {

	T getCurrentTenant();
}
