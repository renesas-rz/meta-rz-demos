#!/bin/sh

export LD_PRELOAD=/usr/share/flutter/3.27.1/release/lib/libflutter_engine.so

flutter-client -f -b /usr/share/flutter/flutter-samples-animations/3.27.1/release
