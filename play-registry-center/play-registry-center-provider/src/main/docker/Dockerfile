FROM openjdk:8

MAINTAINER "airlenet" <airshiplay@126.com>

EXPOSE 18888
EXPOSE 3306

ARG HTTP_PROXY=${HTTP_PROXY}
ARG HTTPS_PROXY=${HTTPS_PROXY}

ENV http_proxy $HTTP_PROXY
ENV https_proxy $HTTPS_PROXY

#add register-center related resources to the docker image
VOLUME /tmp
ADD maven/${project.build.finalName}.${project.packaging} app.jar

RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
