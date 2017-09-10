package com.airlenet.play.repo.domain;

public interface DatabaseResolver {

	String resolveDatabaseName();

	void setCurrentDatabase(String database);

	void setDefaultDatabase();

	void reset();

}
