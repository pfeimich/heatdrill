#!/bin/bash

echo "=========================================================="
echo "Docker container for test PostgreSQL database with postgis"
echo "Uses the following Docker image:"
echo "https://hub.docker.com/r/mdillon/postgis"
echo "=========================================================="

docker build \
    --no-cache \
    --force-rm \
    -t pgdev \
    .
