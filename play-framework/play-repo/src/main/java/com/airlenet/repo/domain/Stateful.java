package com.airlenet.repo.domain;

public interface Stateful<T> {

	T getState();

	void setState(T state);

}
