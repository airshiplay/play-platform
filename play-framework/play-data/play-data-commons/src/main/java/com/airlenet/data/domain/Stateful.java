package com.airlenet.data.domain;

public interface Stateful<T> {

	T getState();

	void setState(T state);

}
