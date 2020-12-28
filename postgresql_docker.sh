#!/usr/bin/env bash

docker rm openmind-db
docker run --name openmind-db -e POSTGRES_PASSWORD=root -e POSTGRES_DB=openmind_db -p 5435:5432 -d postgres:13.1-alpine