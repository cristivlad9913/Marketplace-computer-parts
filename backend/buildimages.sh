#!/bin/bash
cd ./buyer-service && ./mvnw spring-boot:build-image
cd ../discover-server && ./mvnw spring-boot:build-image

