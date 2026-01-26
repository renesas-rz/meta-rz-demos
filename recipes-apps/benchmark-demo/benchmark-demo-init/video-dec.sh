#!/bin/sh

moviefile="/usr/share/movies/big_buck_bunny_1080p_30fps_30s.mp4"

tmp1=$(mktemp)
tmp2=$(mktemp)

top -d 1 -b > $tmp1 &

gst-launch-1.0 -r filesrc location=${moviefile} ! qtdemux ! h264parse ! omxh264dec ! waylandsink max-lateness=-1 qos=false name=v -rp v:sink > $tmp2

kill -9 top
sleep 1

# output result of top (CPU usage)
grep -m 1 "COMMAND" $tmp1
grep $moviefile $tmp1

# remove control characters in the result
tr -d '\0' < $tmp2

rm $tmp1
rm $tmp2
