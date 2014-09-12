#!/usr/bin/env ruby

# takes the output of the xy probe and makes a pretty picture


scale = 1000

puts "<?xml version='1.0' ?>"
puts "<svg width='#{scale}' height='#{scale}' version='1.1'>"

ARGF.each_line do |line|
  digits = line.chomp.split(/\t/).grep /^[.0-9]+$/
  if digits.size==4
    x1,y1,x2,y2 = digits.map{|n| (n.to_f * 1000).to_i}
    puts "  <line x1='#{x1}' y1='#{scale - y1}' x2='#{x2}' y2='#{scale - y2}' stroke='black' stroke-width='1'/>"
  end
end

puts "</svg>"
