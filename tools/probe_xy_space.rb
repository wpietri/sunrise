#!/usr/bin/env ruby

# probes the xy space to see what the story is

require 'json'
require 'rest_client'

bulb = ARGV[0] || 1
resolution = (ARGV[1] || 5).to_i
puts resolution
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

def find_resulting_xy(bulb,x,y)
    set(bulb,{xy:[x,y], transitiontime:0})
    end_time = Time.now + 12
    while  end_time > Time.now do
      new_x, new_y = get_xy(bulb)
      return new_x,new_y  if !(close_enough(x,new_x) && close_enough(y,new_y))
      sleep 0.1
    end
    return get_xy(bulb)
end

puts bulb_info(bulb)


positions = (0..resolution).map{|p| Rational(p,resolution).to_f}

$stdout.sync = true
positions.each do |x|
  positions.each do |y|
    print "#{x}\t#{y}\t->\t"
    new_x, new_y = find_resulting_xy(bulb,x,y)
    print "#{new_x}\t#{new_y}\n"
  end
end
