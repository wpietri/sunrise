sunrise
=======

Some automation for Philips Hue lights.

Currently a simple Scala app that simulates a sunrise to aid in fighting winter blues.

To run it, do

    sbt assembly
    sudo docker build -t sunrise .
    sudo docker run sunrise

More plausibly, though, you'll want a root crontab entry like

    0 6 * * * docker run sunrise


It takes one argument, the length of the faux sunrise in minutes. If you'd
like to test out the full cycle while watching the commands go, do:

    sudo docker run -t sunrise 1


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