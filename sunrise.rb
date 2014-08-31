#!/usr/bin/env ruby

require 'json'
require 'rest_client'

sunrise_time = ARGV[0] || 30
pace = sunrise_time.to_i * 2 # seconds per minute, basically

BASE_URL = "http://192.168.1.199/api/080ed655b6f74144a29fd2f256eff3ae"

def group(number,state)
  puts RestClient.put "#{BASE_URL}/groups/#{number}/action", state.to_json
end

def light(number,state)
  puts RestClient.put "#{BASE_URL}/lights/#{number}/state", state.to_json
end

# sunrise comes up over 30 minutes to full brightness

# 0-5 bring light 1 up
light 1, {bri: 0, xy:[0.65,0.32], transitiontime: 0, on: true}
sleep 0.1
light 1, {bri: 50, xy:[0.62,0.35], transitiontime: 5*pace*10}
sleep 5*pace

# 5-10 bring light 2 up; shift light 1 color
light 2, {bri: 0, xy:[0.62,0.35], transitiontime: 0, on:true}
sleep 0.1
light 1, {bri: 50, xy:[0.58,0.39], transitiontime: 5*pace*10}
light 2, {bri: 50, xy:[0.58,0.39], transitiontime: 5*pace*10}
sleep 5*pace
#now lights are in sync, so use groups

# 10-20 shift to warm light at medium brightness
group 0, {bri: 125, ct:500, transitiontime: 10*pace*10}
sleep 10*pace

# 20-30 shift to normal light at high brightness
group 0, {bri: 255, ct:333, transitiontime: 10*pace*10}
sleep 10*pace

# shift to daytime color over the longest period allowed
group 0, {ct:153, transitiontime: 65535}




