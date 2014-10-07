sunrise
=======

Using Phlips Hue LED lights, this simulates a day-night cycle. The
day starts with a gradual dawn, starting dim and warm. At noon, the
light is bright and cool. In the evening, the light warms again and
eventually dims. The goal is to promote a comfortable and regular
sleep cycle, even when the light outside isn't cooperating due to
clouds, rain, or the dark mornings and early nights of winter.

It is currently a simple Scala daemon wrapped in Docker for easy
deployment.

Note that the current code is specific to my own setup and preferences,
and is mainly offered as an example. If others are interested in using
it, we can make it more configurable and flexible.

To run it, do

    sbt assembly
    sudo docker build -t sunrise .
    sudo docker run sunrise

If you'd rather run it directly, you can also do

    sbt assembly
    java -jar [buildpath]/sunrise.jar
    


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
