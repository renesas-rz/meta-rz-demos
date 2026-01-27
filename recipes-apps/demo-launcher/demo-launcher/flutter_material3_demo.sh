#!/bin/sh

export LD_PRELOAD=/usr/share/flutter/3.27.1/release/lib/libflutter_engine.so

flutter-client -f -b /usr/share/flutter/flutter-samples-material-3-demo/3.27.1/release
