#!/bin/sh

# Set up weston.ini for demo
if [ ! -e /etc/xdg/weston/weston.ini.bk ]
then
	mv /etc/xdg/weston/weston.ini /etc/xdg/weston/weston.ini.bk
	cp /home/root/weston_demo.ini /etc/xdg/weston/weston.ini
fi

if [ -z $XDG_RUNTIME_DIR ]
then
	export XDG_RUNTIME_DIR=/run/user/`id -u`
fi
export WAYLAND_DISPLAY=wayland-0

if [ ! -d "$XDG_RUNTIME_DIR" ]
then
	mkdir -p $XDG_RUNTIME_DIR
	chmod 0700 $XDG_RUNTIME_DIR
fi

# Wait for weston to start
while [ ! -e $XDG_RUNTIME_DIR/$WAYLAND_DISPLAY ]
do
	usleep 10000
done

/usr/bin/rz_benchmark_demo -c /usr/share/benchmark_demo/rz_bench_config.toml
