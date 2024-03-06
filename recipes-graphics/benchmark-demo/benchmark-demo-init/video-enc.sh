#!/bin/sh

tmp=$(mktemp)

gst-launch-1.0 -r videotestsrc num-buffers=500 is-live=true ! video/x-raw,width=1280,height=720,format=NV12,framerate=30/1 ! omxh264enc target_bitrate=4000000 ! video/x-h264,profile=\(string\)main,level=\(string\)1 ! queue ! filesink location=/tmp/test-data.mp4 name=v -p v:sink >> $tmp

# remove control characters in the result
tr -d '\0' < $tmp

rm $tmp
