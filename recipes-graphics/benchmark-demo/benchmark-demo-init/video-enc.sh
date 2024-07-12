#!/bin/sh

moviefile="/tmp/test-data.mp4"

tmp1=$(mktemp)
tmp2=$(mktemp)

top -d 1 -b > $tmp1 &

gst-launch-1.0 -r videotestsrc num-buffers=500 is-live=true ! video/x-raw,width=1920,height=1080,format=NV12,framerate=30/1 ! omxh264enc target_bitrate=8000000 ! video/x-h264,profile=\(string\)main,level=\(string\)4,alignment=au ! h264parse ! qtmux ! queue ! filesink location=${moviefile=} name=v -rp v:sink > $tmp2

kill -9 top
sleep 1

# output result of top (CPU usage)
grep -m 1 "COMMAND" $tmp1
grep $moviefile $tmp1

# remove control characters in the result
tr -d '\0' < $tmp2

rm $tmp1
rm $tmp2
