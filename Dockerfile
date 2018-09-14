FROM denvazh/scala:2.10.5-openjdk8 as builder

WORKDIR /tmp/build
ADD src ./src
ADD project ./project
ADD build.sbt ./
RUN ls -l /tmp/build
RUN sbt assembly

FROM denvazh/scala:2.10.5-openjdk8

COPY --from=builder /tmp/build/target/scala-2.10/sunrise.jar /usr/local/bin/sunrise.jar

ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/local/bin/sunrise.jar"]
CMD []
