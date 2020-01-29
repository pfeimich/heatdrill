FROM openjdk:11-slim

USER root

WORKDIR /home/heatdrill
COPY tmp/heatdrill.jar /home/heatdrill

RUN cd /home/heatdrill && \
    chown -R 1001:0 /home/heatdrill && \
    chmod -R g+rw /home/heatdrill && \
    ls -la /home/heatdrill

USER 1001
EXPOSE 8080
CMD java -XX:MaxRAMPercentage=80.0 -jar heatdrill.jar 


#${CCC_MAX_INACTIVITY:-"-DCCC_MAX_INACTIVITY=${CCC_MAX_INACTIVITY}"} ${CCC_MAX_PAIRING:-"-DCCC_MAX_PAIRING=${CCC_MAX_PAIRING}"} ${CCC_DEBUG:+"--spring.profiles.active=ccc-debug"}