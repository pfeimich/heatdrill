#!/bin/bash

export SPRING_APPLICATION_JSON="{\"db-connection\":{\"url\": \"jdbc:postgresql://10.36.54.208:5432/dev\",\"userName\": \"ddluser\",\"password\": \"ddluser\"}}"

docker run -it \
    --name heatdrill \
    -e "SPRING_APPLICATION_JSON" \
    -p 8088:8080 \
    --rm \
    sogis/heatdrill
