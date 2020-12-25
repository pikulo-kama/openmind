## Start Postgres database in docker


- Build image from dockerfile
```sh
     docker build -t openmind-psql .
```
- Start container from created image
```shell script
    docker run --name openmind_db -p 5432:5432 openmind-psql
```
