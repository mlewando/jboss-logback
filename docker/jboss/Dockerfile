FROM anapsix/alpine-java:jdk8

ARG WILDFLY_VERSION=10.0.0.Final

ENV JBOSS_HOME /opt/jboss/wildfly
ENV LAUNCH_JBOSS_IN_BACKGROUND true

RUN apk update && apk upgrade
RUN apk add curl tar bash

RUN curl -o wildfly.tar.gz https://download.jboss.org/wildfly/$WILDFLY_VERSION/wildfly-$WILDFLY_VERSION.tar.gz \
    && tar xf wildfly.tar.gz \
    && mkdir -p /opt/jboss \
    && mv wildfly-$WILDFLY_VERSION $JBOSS_HOME \
    && rm wildfly.tar.gz

RUN apk del curl tar

ENV MODULES_BASE=$JBOSS_HOME/modules
ENV MODULES_SYSTEM=$MODULES_BASE/system/layers/base
ENV MYSQL_MODULE=$MODULES_SYSTEM/com/mysql/driver/main

ADD mgmt-users.properties $JBOSS_HOME/standalone/configuration/mgmt-users.properties
ADD standalone.xml $JBOSS_HOME/standalone/configuration/standalone.xml

ADD mysql.module.xml $MYSQL_MODULE/module.xml
ADD http://central.maven.org/maven2/mysql/mysql-connector-java/5.1.22/mysql-connector-java-5.1.22.jar $MYSQL_MODULE/mysql-connector-java-5.1.22.jar

WORKDIR $JBOSS_HOME

EXPOSE 8080
EXPOSE 9990
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]