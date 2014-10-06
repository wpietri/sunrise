sunrise
=======

Some automation for Philips Hue lights.

Currently a simple Scala daemon that simulates a day/night cycle to aid
in fighting winter blues. Rather than letting dawn come ever later and
sunset ever earlier, its brings up and down the lights at a fixed time,
vaguely approximating natural light.

To run it, do

    sbt assembly
    sudo docker build -t sunrise .
    sudo docker run sunrise

If you'd rather run it directly, you can also do

    sbt assembly
    java -jar [buildpath]/sunrise.jar
    

Note that the current code is insanely specific to my own setup, and
is mainly offered as an example. If others are interested in using it,
we can make it more configurable and flexible.

tools
=====

There's a tools directory with various things other developers might
find handy, things I ended up having to make as I went along.


build environment
=================

To build this on Ubuntu 14.04 you'll have to do something like

    sudo aptitude install openjdk-6-jre-headless openjdk-6-jre libjansi-java
    wget http://www.scala-lang.org/files/archive/scala-2.10.4.deb
    wget https://dl.bintray.com/sbt/debian/sbt-0.13.6.deb
    sudo dpkg -i scala-2.10.4.deb  sbt-0.13.6.deb
    
With that installed, the "sbt assembly" command should do the rest.