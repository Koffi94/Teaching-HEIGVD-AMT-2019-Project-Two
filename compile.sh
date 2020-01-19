#!/bin/bash

mvn -f EntitiesAPI/spring-server/pom.xml clean package
mvn -f UsersAPI/spring-server/pom.xml clean package
