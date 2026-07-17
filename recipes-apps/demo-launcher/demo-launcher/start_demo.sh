#!/bin/sh

MACHINE_NAME=`hostname`

if [ "smarc-rzg2ul" != ${MACHINE_NAME} ]; then

    if [ -z "$XDG_RUNTIME_DIR" ]
    then
        export XDG_RUNTIME_DIR=/run/user/$(id -u weston)
    fi
    export WAYLAND_DISPLAY=wayland-1

    if [ ! -d "$XDG_RUNTIME_DIR" ]
    then
    	mkdir -p $XDG_RUNTIME_DIR
    	chmod 0700 $XDG_RUNTIME_DIR
    fi

    # Wait for weston to start
    while [ ! -e "$XDG_RUNTIME_DIR/$WAYLAND_DISPLAY" ]
    do
    	usleep 10000
    done
fi

/etc/profile.d/audio.sh
/usr/bin/demo-launcher
