sunrise
=======

Using Phlips Hue LED lights, this simulates a day-night cycle. The
day starts with a gradual dawn, starting dim and warm. At noon, the
light is bright and cool. In the evening, the light warms again and
eventually dims. The goal is to promote a comfortable and regular
sleep cycle, even when the light outside isn't cooperating due to
clouds, rain, or the dark mornings and early nights of winter.

It is currently a simple Scala daemon wrapped in Docker for easy
deployment. It is configured via environment variables; see below for more.


To run it, do

    sudo docker build -t sunrise .
    sudo docker run -d sunrise

If you'd rather run it directly, you can also do

    sbt assembly
    java -jar [buildpath]/sunrise.jar
    
configuration
=============

All configuration is through environment variables. There are two mandatory
ones:
* **SUNRISE_HUE_HOST** - the address of the Philips Hue bridge. E.g.,
`hue.local` or `192.168.1.123`
* **SUNRISE_HUE_KEY** - the key for the Philips Hue bridge. A long hex
string you get from the bridge API

There are a few optional ones. Sunrise's basic model is that there is night, with
all lights off, and day, with all lights on. The transition periods between the two
are dawn and dusk, which have configurable starts and durations.

* **SUNRISE_DAWN_START** - When dawn starts; the moment lights go from off to the
dimmest red. Default: `6:00`
* **SUNRISE_DAWN_LENGTH** - How many hours the dawn lasts. Default `3`
* **SUNRISE_DUSK_START** - When dusk starts; the moment lights go from full on to
a tiny bit dimmer. Default: `16:00`
* **SUNRISE_DUSK_LENGTH** - How many hours the dusk lasts. Default `6`

So by default, the lights come on very dim at 6 am and are fully on by 9. Then at
4 pm they start dimming
again and are fully off at 10 pm.

You should probably also set the time zone. 

* **SUNRISE_TIMEZONE** - The time zone the app should use. On Linux, your current one
is in `/etc/timezone`. Wikipedia has a [full list](https://en.wikipedia.org/wiki/List_of_tz_database_time_zones#List).

If I'm going to be flying to a different time zone, I sometimes shift my schedule
before I go. You can do that by giving sunrise a different time zone every day or two.



tools
=====

There's a tools directory with various things other developers might
find handy, things I ended up having to make as I went along.


build environment
=================

If you're doing a Docker build, all you need is a version of Docker at
or after 17.05, which supports multi-stage builds.


To build this on Ubuntu 14.04 you'll have to do something like

    sudo aptitude install openjdk-6-jre-headless openjdk-6-jre libjansi-java
    wget http://www.scala-lang.org/files/archive/scala-2.10.4.deb
    wget https://dl.bintray.com/sbt/debian/sbt-0.13.6.deb
    sudo dpkg -i scala-2.10.4.deb  sbt-0.13.6.deb
    
With that installed, the "sbt assembly" command should do the rest.
