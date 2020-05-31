#!/bin/sh
chmod 755 mvnw
yum -y install java-1.8.0-openjdk-devel
./mvnw clean package
docker build -t echo-host -t echo-host:1.0 .
