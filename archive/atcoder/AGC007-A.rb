# coding: utf-8


h, w = gets.chomp.split.map(&:to_i)

count = 0
aa = []
h.times do
  a = gets.chomp.each_char.to_a
  count += a.count("#")
end

if count == h + w - 1
  puts("Possible")
else
  puts("Impossible")
end