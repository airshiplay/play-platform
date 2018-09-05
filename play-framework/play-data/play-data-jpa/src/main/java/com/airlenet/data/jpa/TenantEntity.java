package com.airlenet.data.jpa;

import com.airlenet.data.domain.MultiTenant;
import com.airlenet.data.domain.Tenant;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class TenantEntity<I extends Serializable, O extends Tenant> extends BaseEntity<I> implements MultiTenant<O> {

	private static final long serialVersionUID = 142922686974183102L;

	@ManyToOne(fetch = FetchType.LAZY)
	private O tenant;

	@Override
	public O getTenant() {
		return tenant;
	}

	@Override
	public void setTenant(O tenant) {
		this.tenant = tenant;
	}

}
