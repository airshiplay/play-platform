<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		 xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>com.airshiplay</groupId>
		<artifactId>play-module</artifactId>
		<version>1.0-SNAPSHOT</version>
	</parent>
	<artifactId>play-${moduleName}-webapp-adminlte</artifactId>

	<dependencies>

        <dependency>
            <groupId>com.airshiplay</groupId>
            <artifactId>play-admin-webapp-adminlte</artifactId>
            <version>${r'${project.version}'}</version>
        </dependency>
        <dependency>
            <groupId>com.airshiplay</groupId>
            <artifactId>play-${moduleName}-webapp</artifactId>
            <version>${r'${project.version}'}</version>
        </dependency>
	</dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>com.airshiplay.play.maven</groupId>
                <artifactId>play-maven-plugin</artifactId>
                <version>${r'${project.version}'}</version>
            </plugin>
        </plugins>
    </build>

</project>

