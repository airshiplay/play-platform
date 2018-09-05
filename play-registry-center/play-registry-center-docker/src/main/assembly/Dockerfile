FROM ubuntu:16.04

MAINTAINER "Lu Ji" <lu.ji3@zte.com.cn>

EXPOSE 8082
EXPOSE 3306

ARG HTTP_PROXY=${HTTP_PROXY}
ARG HTTPS_PROXY=${HTTPS_PROXY}

ENV http_proxy $HTTP_PROXY
ENV https_proxy $HTTPS_PROXY

RUN echo "mysql-server mysql-server/root_password password root" | debconf-set-selections
RUN echo "mysql-server mysql-server/root_password_again password root" | debconf-set-selections

RUN apt-get update && \
    apt-get install -y openjdk-8-jdk && \
    apt-get install -y gcc && \
    apt-get install -y libmysqlclient-dev && \
    apt-get install -y mysql-server && \
    apt-get install -y mysql-client && \
    apt-get install -y curl && \
    apt-get install -y build-essential && \
    apt-get install -y libssl-dev && \
    apt-get install -y libffi-dev

#configure the JDK
RUN sed -i 's|#networkaddress.cache.ttl=-1|networkaddress.cache.ttl=10|' /usr/lib/jvm/java-8-openjdk-amd64/jre/lib/security/java.security
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64
ENV PATH $PATH:/usr/lib/jvm/java-8-openjdk-amd64/jre/bin:/usr/lib/jvm/java-8-openjdk-amd64/bin
ENV CLASSPATH .:${JAVA_HOME}/lib:${JRE_HOME}/lib
ENV JRE_HOME ${JAVA_HOME}/jre

#add registry related resources to the docker image
RUN mkdir /home/registry
WORKDIR /home/registry
ADD registry-center-server-*-linux64.tar.gz /home/registry/

#init db
#RUN chmod 755 /home/registry/resources/bin/*.sh
#RUN /home/registry/resources/bin/initDB.sh root root 3306 127.0.0.1

#RUN chmod 755 /home/registry/bin/*.sh

#ENTRYPOINT /home/registry/resources/bin/initDB.sh root root 3306 127.0.0.1
ENTRYPOINT /home/registry/bin/run.sh
