FROM ubuntu:14.04
Maintainer William Pietri <william-sunrise-201408@scissor.com>

RUN apt-get update 
RUN apt-get install -y ruby ruby-dev
RUN apt-get install -y build-essential
RUN gem install json rest-client
ADD sunrise.rb /usr/local/bin/sunrise

ENTRYPOINT ["/usr/local/bin/sunrise"]
CMD []
