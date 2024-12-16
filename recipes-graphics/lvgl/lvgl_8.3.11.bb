# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: MIT

HOMEPAGE = "https://lvgl.io/"
DESCRIPTION = "LVGL is an OSS graphics library to create embedded GUI"
SUMMARY = "Light and Versatile Graphics Library"
LICENSE = "MIT & Zlib & OFL-1.1 & MIT & CC-BY-4.0"
LIC_FILES_CHKSUM = "file://LICENCE.txt;md5=bf1198c89ae87f043108cea62460b03a \
		    file://src/extra/libs/png/LICENSE;md5=0a21ed93a5d78a5a771d718fe4a4ebc5 \
		    file://scripts/built_in_font/font_license/DejaVuSans/LICENSE;md5=9f867da7a73fad2715291348e80d0763 \
		    file://scripts/built_in_font/font_license/FontAwesome5/LICENSE.txt;md5=57f9201afe70f877988912a7b233de47 \
		    file://scripts/built_in_font/font_license/Montserrat/OFL.txt;md5=b5170b54aff412cfd0bed5aa23205738 \
"

SRC_URI = " \
	git://github.com/lvgl/lvgl;protocol=https;branch=release/v8.3 \
	file://0001-Add-LICENSE-for-lodepng-library.patch \
	file://0001-docs-license-add-font-license-files-6971.patch \
	file://0001-fix-font-remove-simsun-font.patch \
"
SRCREV = "74d0a816a440eea53e030c4f1af842a94f7ce3d3"

inherit cmake

EXTRA_OECMAKE = "-DLIB_INSTALL_DIR=${baselib}"
EXTRA_OECMAKE += "-DBUILD_SHARED_LIBS=ON"
S = "${WORKDIR}/git"

PATCHTOOL = "git"

ALLOW_EMPTY:${PN} = "1"

LVGL_CONFIG_LV_MEM_CUSTOM ?= "1"
LVGL_CONFIG_LV_COLOR_DEPTH ?= "32"
LVGL_CONFIG_LV_USE_FS_STDIO ?= "1"
LVGL_CONFIG_LV_FS_STDIO_LETTER ?= "L"
LVGL_CONFIG_LV_FS_STDIO_CACHE_SIZE ?= "4096"

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
	    -e "s|\(#define LV_USE_FS_STDIO .*\)0|\1${LVGL_CONFIG_LV_USE_FS_STDIO}|g" \
	    -e "s|\(#define LV_FS_STDIO_LETTER .*\)\\\0|\1${LVGL_CONFIG_LV_FS_STDIO_LETTER}|g" \
	    -e "s|\(#define LV_FS_STDIO_CACHE_SIZE *\)0|\1${LVGL_CONFIG_LV_FS_STDIO_CACHE_SIZE}|g" \
	    \
	    -e "s|\(#define LV_USE_PNG .*\)0|\11|g" \
	    -e "s|\(#define LV_USE_BMP .*\)0|\11|g" \
	    -e "s|\(#define LV_USE_SJPG .*\)0|\11|g" \
	    -e "s|\(#define LV_USE_GIF .*\)0|\11|g" \
	    \
            < "${S}/lv_conf_template.h" > "${S}/lv_conf.h"
}

FILES:${PN}-dev += "\
    ${includedir}/${PN}/ \
    ${includedir}/${PN}/lvgl/ \
    "

SOLIBS = ".so"
FILES_SOLIBSDEV = ""
