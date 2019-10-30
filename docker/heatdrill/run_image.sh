#!/bin/bash

export SPRING_APPLICATION_JSON="{\"db-connection\":{\"url\": \"jdbc:postgresql://10.36.43.208:5432/postgres\",\"userName\": \"postgres\",\"password\": \"postgres\"}}"

if [ "$1" == "bg" ] #bg - background
  then
    PARA="-d"
  else
    PARA=""
fi

docker run $PARA  \
    --name heatdrill \
    -e "SPRING_APPLICATION_JSON" \
    -p 8080:8080 \
    --rm \
    sogis/heatdrill
