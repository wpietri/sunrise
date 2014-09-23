FROM ubuntu:14.04
Maintainer William Pietri <william-sunrise-201409@scissor.com>

#
RUN apt-get update
RUN apt-get install -y openjdk-7-jre-headless

ADD target/scala-2.10/sunrise.jar /usr/local/bin/sunrise.jar

ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/local/bin/sunrise.jar"]
CMD []
