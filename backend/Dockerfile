#
# USAGE: Replace all taco-cloud-0.0.1-SNAPSHOT.jar with your own jar file from /target
#
ARG JAR_FILE=planesales-0.0.1-SNAPSHOT.jar
FROM maven:3.9.6-eclipse-temurin-21 as build
ARG JAR_FILE
RUN mkdir /usr/src/project
COPY . /usr/src/project
WORKDIR /usr/src/project/

RUN mvn package -DskipTests

RUN ["sh", "-c", "jar -xf target/${JAR_FILE}"]
RUN jdeps --ignore-missing-deps -q \
    --recursive \
    --multi-release 21 \
    --print-module-deps \
    --class-path 'BOOT-INF/lib/*' \
    target/${JAR_FILE} > deps.info

RUN jlink \
    --add-modules $(cat deps.info) \
    --strip-debug \
    --compress 2 \
    --no-header-files \
    --no-man-pages \
    --output /myjre

FROM debian:bookworm-slim
# FROM alpine:latest
ARG JAR_FILE=planesales-0.0.1-SNAPSHOT.jar

ENV JAVA_HOME /user/java/jdk21
ENV PATH $JAVA_HOME/bin:$PATH

COPY --from=build /myjre $JAVA_HOME

# COPY cacerts $JAVA_HOME/lib/security
USER root
COPY apitravel.pem $JAVA_HOME/lib/security
RUN \
    cd $JAVA_HOME/lib/security \
    && keytool -keystore cacerts -storepass changeit -noprompt -trustcacerts -importcert -alias apitravel -file apitravel.pem


    
RUN mkdir /project
COPY --from=build /usr/src/project/target/$JAR_FILE /project/

WORKDIR /project

CMD ["java", "-jar", "planesales-0.0.1-SNAPSHOT.jar", "-Dhttps.protocols=TLSv1.2,TLSv1.1,TLSv1"]
