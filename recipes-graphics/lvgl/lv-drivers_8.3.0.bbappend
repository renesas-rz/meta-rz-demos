# lv-drivers configuration for RZ/G2UL
# On RZ/G2UL, fbdev and evdev are used, but wayland is not used.
LVGL_CONFIG_USE_FBDEV_smarc-rzg2ul = "1"
LVGL_CONFIG_USE_EVDEV_smarc-rzg2ul = "1"
LVGL_CONFIG_USE_WAYLAND_smarc-rzg2ul = "0"
USE_WAYLAND_TIMER_HANDLER_smarc-rzg2ul = "0"

PATCHTOOL = "git"

SRC_URI += "\
	file://0001-do-not-skip-flushing.patch \
	file://0002-fix-mistake-that-caused-tearing.patch \
"
