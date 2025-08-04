SUMMARY = "RZ lvgl-home-panel-demo"
SECTION = "application"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=ac55c209a96858b87de6e4b277a4a014"

SRC_URI = " \
	git://github.com/renesas-rz/rzg_hmi_sdk.git;protocol=https;branch=main \
"
SRCREV = "6fef486fef377082330803b3b63fa47057dd4497"

PV = "1.0+git${SRCPV}"

S = "${WORKDIR}/git/sample_app/lvgl/lvgl_home_panel_demo"

DEPENDS += " lvgl lv-drivers wayland pkgconfig-native "
TARGET_CFLAGS += "-DLV_CONF_INCLUDE_SIMPLE -I${STAGING_DIR_HOST}/usr/include/lvgl -I${STAGING_DIR_HOST}/usr/include/lvgl/lv_drivers -I${STAGING_DIR_HOST}/usr/include/glib-2.0 -I${STAGING_DIR_HOST}/usr/lib64/glib-2.0/include"

DEPENDS += "${@oe.utils.conditional('MACHINE', 'smarc-rzg2l', 'gstreamer1.0', '', d)}"
TARGET_CFLAGS +=  "${@oe.utils.conditional('MACHINE', 'smarc-rzg2l', '-I${STAGING_DIR_HOST}/usr/include/gstreamer-1.0', '', d)}"

DEPENDS += "${@oe.utils.conditional('MACHINE', 'smarc-rzg2lc', 'gstreamer1.0', '', d)}"
TARGET_CFLAGS +=  "${@oe.utils.conditional('MACHINE', 'smarc-rzg2lc', '-I${STAGING_DIR_HOST}/usr/include/gstreamer-1.0', '', d)}"

do_compile () {
    cd ${S}
    make MACHINE=${MACHINE}
}

do_install () {
    install -d ${D}${bindir}
    install -d ${D}${datadir}
    install -d ${D}${datadir}/lvgl-home-panel-demo
    install -d ${D}${datadir}/lvgl-home-panel-demo/images
    install -d ${D}${datadir}/lvgl-home-panel-demo/music

    # Install app files
    install -m 0755 ${S}/lvgl-home-panel-demo ${D}${bindir}/lvgl-home-panel-demo

    # Install image files
    install -m 0644 ${S}/images/* ${D}${datadir}/lvgl-home-panel-demo/images

    # Install music files
    install -m 0644 ${S}/music/* ${D}${datadir}/lvgl-home-panel-demo/music
}

FILES_${PN} += " \
    ${bindir}/lvgl-home-panel-demo \
    ${datadir}/lvgl-home-panel-demo/* \
"
