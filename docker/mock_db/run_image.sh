# Shell zum Starten der Mock-Datenbank zur Entwicklung des
# Nadelstich REST-Service.
# Erstellt in [Arbeitsverzeichnis]/pgdata die persistenten
# Datenbankdateien der Mock-DB.

#!/bin/bash

echo "=========================================================="
echo "Docker container for test PostgreSQL database with postgis"
echo "Uses the following Docker image:"
echo "https://hub.docker.com/r/mdillon/postgis"
echo "=========================================================="

docker run -it \
    --name pgdev_ews \
    -e POSTGRES_PASSWORD=admin1234 \
    -e PGDATA=/var/lib/postgresql/data/pgdata \
    -p 5432:5432 \
    -v $(pwd)/pgdata:/var/lib/postgresql/data/pgdata \
    --rm \
    pgdev
