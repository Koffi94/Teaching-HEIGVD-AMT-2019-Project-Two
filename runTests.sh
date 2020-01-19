#!/bin/bash

mvn -f UsersAPI/users-specs/pom.xml clean test
mvn -f EntitiesAPI/entities-specs/pom.xml clean test
