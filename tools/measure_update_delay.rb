#!/usr/bin/env ruby

# Measures the update delay for the given bulb

require 'json'
require 'rest_client'

bulb = ARGV[0] || 1
BRIDGE = ENV['HUE_BRIDGE'] || 'philips-hue-bridge'
USERNAME = ENV['HUE_USERNAME'] || '080ed655b6f74144a29fd2f256eff3ae'
BASE_URL = "http://#{BRIDGE}/api/#{USERNAME}"


def bulb_info(bulb)
  response = JSON.parse(RestClient.get "#{BASE_URL}/lights/#{bulb}")
  response.delete 'state'
  response.delete 'pointsymbol'
  response
end

def set(bulb,state)
  RestClient.put "#{BASE_URL}/lights/#{bulb}/state", state.to_json
end

def get_xy(bulb)
  response = RestClient.get "#{BASE_URL}/lights/#{bulb}"
  #puts response
  JSON.parse(response)['state']['xy']
end

def close_enough(f1, f2)
  (f1-f2).abs<0.0001
end

def light_update_delay(bulb,x,y)
    set(bulb,{xy:[x,y], transitiontime:0})
    start_time = Time.now.to_f
    while start_time + 30 > Time.now.to_f do
      new_x, new_y = get_xy(bulb)
      return Time.now.to_f - start_time if !(close_enough(x,new_x) && close_enough(y,new_y))
      sleep 0.1
    end
end

puts bulb_info(bulb)

255.times do
  puts light_update_delay(bulb,0,0)
  sleep rand(10) # in case there's correlation, as with a regular poll cycle
end
