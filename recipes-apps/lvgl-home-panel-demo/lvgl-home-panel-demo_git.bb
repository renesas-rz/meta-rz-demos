SUMMARY = "RZ lvgl-home-panel-demo"
SECTION = "application"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=9da82885c90b317db044f6bfba5652db"

SRC_URI = " \
	git://github.com/renesas-rz/rzg_hmi_sdk.git;protocol=https;branch=main \
"
SRCREV = "80bb68314c4a24b1ea32940eb7e7bc27f8095707"

PV = "2.0+git${SRCPV}"

S = "${WORKDIR}/git/sample_app/lvgl/lvgl_home_panel_demo"

DEPENDS += " lvgl wayland pkgconfig-native "
TARGET_CFLAGS += "-DLV_CONF_INCLUDE_SIMPLE -I${STAGING_DIR_HOST}/usr/include/lvgl -I${STAGING_DIR_HOST}/usr/include/glib-2.0 -I${STAGING_DIR_HOST}/usr/lib64/glib-2.0/include"

DEPENDS += "${@oe.utils.conditional('MACHINE', 'smarc-rzg2l', 'gstreamer1.0', '', d)}"
TARGET_CFLAGS +=  "${@oe.utils.conditional('MACHINE', 'smarc-rzg2l', '-I${STAGING_DIR_HOST}/usr/include/gstreamer-1.0', '', d)}"

DEPENDS += "${@oe.utils.conditional('MACHINE', 'smarc-rzg2lc', 'gstreamer1.0', '', d)}"
TARGET_CFLAGS +=  "${@oe.utils.conditional('MACHINE', 'smarc-rzg2lc', '-I${STAGING_DIR_HOST}/usr/include/gstreamer-1.0', '', d)}"

DEPENDS += "${@oe.utils.conditional('MACHINE', 'smarc-rzg3e', 'gstreamer1.0', '', d)}"
TARGET_CFLAGS +=  "${@oe.utils.conditional('MACHINE', 'smarc-rzg3e', '-I${STAGING_DIR_HOST}/usr/include/gstreamer-1.0', '', d)}"

DEPENDS += "${@oe.utils.conditional('MACHINE', 'smarc-rzg3l', 'gstreamer1.0', '', d)}"
TARGET_CFLAGS +=  "${@oe.utils.conditional('MACHINE', 'smarc-rzg3l', '-I${STAGING_DIR_HOST}/usr/include/gstreamer-1.0', '', d)}"

PATCHTOOL = "git"

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

FILES:${PN} += " \
    ${bindir}/lvgl-home-panel-demo \
    ${datadir}/lvgl-home-panel-demo/* \
"
