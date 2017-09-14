<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		 xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>com.airshiplay</groupId>
		<artifactId>play-module</artifactId>
		<version>1.0-SNAPSHOT</version>
	</parent>
	<artifactId>play-${moduleName}-business</artifactId>

	<dependencies>
		<dependency>
			<groupId>com.airshiplay</groupId>
			<artifactId>play-admin-business</artifactId>
			<version>${r'${project.version}'}</version>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>com.mysema.maven</groupId>
				<artifactId>apt-maven-plugin</artifactId>
				<executions>
					<execution>
						<goals>
							<goal>process</goal>
						</goals>
						<configuration>
							<outputDirectory>target/generated-sources/java</outputDirectory>
							<processor>com.querydsl.apt.jpa.JPAAnnotationProcessor</processor>
						</configuration>
					</execution>
				</executions>
				<dependencies>
					<dependency>
						<groupId>com.querydsl</groupId>
						<artifactId>querydsl-apt</artifactId>
						<version>${r'${querydsl.version}'}</version>
					</dependency>
				</dependencies>
			</plugin>
            <plugin>
                <groupId>com.airshiplay.play.maven</groupId>
                <artifactId>play-maven-plugin</artifactId>
                <version>${r'${project.version}'}</version>
            </plugin>
		</plugins>
	</build>
</project>

