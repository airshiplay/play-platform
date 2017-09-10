package com.airlenet.play.repo.domain;

public interface Stateful<T> {

	T getState();

	void setState(T state);

}
