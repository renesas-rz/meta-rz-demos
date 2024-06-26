# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: MIT

HOMEPAGE = "https://lvgl.io/"
DESCRIPTION = "LVGL is an OSS graphics library to create embedded GUI"
SUMMARY = "Light and Versatile Graphics Library"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENCE.txt;md5=bf1198c89ae87f043108cea62460b03a"

SRC_URI = "git://github.com/lvgl/lvgl;protocol=https;branch=release/v8.3"
SRCREV = "9ec3b8706b098f60940cee6ac211d3cb3141aedf"

inherit cmake

EXTRA_OECMAKE = "-DLIB_INSTALL_DIR=${baselib}"
EXTRA_OECMAKE += "-DBUILD_SHARED_LIBS=ON"
S = "${WORKDIR}/git"

LVGL_CONFIG_LV_MEM_CUSTOM ?= "1"
LVGL_CONFIG_LV_COLOR_DEPTH ?= "32"

# Upstream does not support a default configuration
# but propose a default "disabled" template, which is used as reference
# More configuration can be done using external configuration variables
do_configure:prepend() {
    [ -r "${S}/lv_conf.h" ] \
        || sed -e 's|#if 0 .*Set it to "1" to enable .*|#if 1 // Enabled|g' \
	    -e "s|\(#define LV_COLOR_DEPTH \).*|\1 ${LVGL_CONFIG_LV_COLOR_DEPTH}|g" \
	    \
	    -e "s|\(#define LV_MEM_CUSTOM .*\)0|\1${LVGL_CONFIG_LV_MEM_CUSTOM}|g" \
	    \
	    -e "s|\(#define LV_TICK_CUSTOM \).*|\1 1|g" \
	    -e "s|\(#define LV_TICK_CUSTOM_INCLUDE \).*|\1 <stdint.h>|g" \
	    -e "s|\(#define LV_TICK_CUSTOM_SYS_TIME_EXPR \).*|extern uint32_t custom_tick_get(void);\n\1 (custom_tick_get())|g" \
	    \
            < "${S}/lv_conf_template.h" > "${S}/lv_conf.h"
}

FILES:${PN}-dev += "\
    ${includedir}/${PN}/ \
    ${includedir}/${PN}/lvgl/ \
    "

ALLOW_EMPTY_${PN} = "1"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
