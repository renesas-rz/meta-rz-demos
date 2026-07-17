#!/bin/sh

export LD_PRELOAD=/usr/share/flutter/3.38.3/release/lib/libflutter_engine.so

flutter-client -f -b /usr/share/flutter/flutter-samples-deeplink-store-example/3.38.3/release
