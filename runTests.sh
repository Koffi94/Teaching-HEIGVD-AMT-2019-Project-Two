#!/bin/bash
cd UsersAPI/users-specs/
mvn clean test
cd ../../EntitiesAPI/entities-specs/
mvn clean test
