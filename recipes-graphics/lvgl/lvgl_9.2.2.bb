# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: MIT

HOMEPAGE = "https://lvgl.io/"
DESCRIPTION = "LVGL is an OSS graphics library to create embedded GUI"
SUMMARY = "Light and Versatile Graphics Library"
LICENSE = "MIT & Zlib & OFL-1.1 & CC-BY-4.0"
LIC_FILES_CHKSUM = "file://LICENCE.txt;md5=bf1198c89ae87f043108cea62460b03a \
                    file://src/libs/lodepng/LICENSE.txt;md5=0a21ed93a5d78a5a771d718fe4a4ebc5 \
                    file://scripts/built_in_font/font_license/DejaVuSans/LICENSE;md5=9f867da7a73fad2715291348e80d0763 \
                    file://scripts/built_in_font/font_license/FontAwesome5/LICENSE.txt;md5=57f9201afe70f877988912a7b233de47 \
                    file://scripts/built_in_font/font_license/Montserrat/OFL.txt;md5=b5170b54aff412cfd0bed5aa23205738 \
"
SRC_URI = "\
	git://github.com/lvgl/lvgl;protocol=https;branch=release/v9.2 \
	file://0001-thorvg-fix-build-with-gcc-15.patch \
	file://0001-docs-license-add-LICENSE-file-for-lodepng-library.patch \
	file://0001-docs-license-add-font-license-files-6971.patch \
	file://0001-docs-license-add-font-license-files-for-LVGL-v9.2.2.patch \
	file://0002-fix-font-remove-three-fonts-due-to-license-issues.patch \
	file://0001-fix-driver-fix-wayland-driver.patch \
"
SRCREV = "7f07a129e8d77f4984fff8e623fd5be18ff42e74"

inherit cmake

EXTRA_OECMAKE = "-DLIB_INSTALL_DIR=${baselib} -DBUILD_SHARED_LIBS=ON"
S = "${WORKDIR}/git"

PATCHTOOL = "git"

require lv-conf.inc

do_install:append() {
    install -m 0644 "${S}/lv_conf.h" "${D}${includedir}/${BPN}/lv_conf.h"
}
