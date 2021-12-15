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

#Log4j 2 CVE-2021-44228
ENV LOG4J_FORMAT_MSG_NO_LOOKUPS=true

CMD java -XX:MaxRAMPercentage=80.0 -jar heatdrill.jar 