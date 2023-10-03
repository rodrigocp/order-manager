FROM    maven:3.5.2-jdk-8-alpine as MAVEN_TOOL_CHAIN
RUN     apk add --no-cache tree
ENV     APP=/build-tmp/
WORKDIR $APP
COPY    pom.xml .
COPY    application ./application/
COPY    orders ./orders/
COPY    orders-impl ./orders-impl
RUN     mvn clean install -DskipTests
CMD     ls -la

FROM    openjdk:8-jdk-alpine
COPY    --from=MAVEN_TOOL_CHAIN /build-tmp/application/target/application-1.0.0.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
