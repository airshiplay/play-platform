package com.airlenet.repo.domain;

public interface MultiTenant<T extends Tenant> {

	T getTenant();

	void setTenant(T tenant);

}
