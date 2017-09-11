package com.airlenet.repo.realm;

public interface DatabaseResolver {

	String resolveDatabaseName();

	void setCurrentDatabase(String database);

	void setDefaultDatabase();

	void reset();

}
