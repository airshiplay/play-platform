package com.airlenet.data.realm;

public interface DatabaseResolver {

	String resolveDatabaseName();

	void setCurrentDatabase(String database);

	void setDefaultDatabase();

	void reset();

}
