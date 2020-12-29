#!/usr/bin/env bash

container_name=openmind-db
port=5435
password=root
database=openmind_db

docker kill $container_name
docker rm $container_name
docker run --name $container_name -e POSTGRES_PASSWORD=$password -e POSTGRES_DB=$database -p $port:5432 -d postgres:13.1-alpine