#!/bin/sh

tmp=$(mktemp)

gst-launch-1.0 -r filesrc location=/usr/share/movies/big_buck_bunny_1080p.mp4 ! qtdemux ! queue ! h264parse ! omxh264dec ! waylandsink max-lateness=-1 qos=false sync=false name=v -p v:sink >> $tmp

# remove control characters in the result
tr -d '\0' < $tmp

rm $tmp
