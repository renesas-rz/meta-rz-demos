SUMMARY = "RZ demo-launcher"
SECTION = "application"

LICENSE = "MIT & OFL-1.1"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=a6f7398c4a545ebdc0f09e23a6127abd \
                    file://src/asset/font/LICENSE.txt;md5=1f319ca1887afc3591b1dccdd8530c58"

SRC_URI = " \
	git://github.com/renesas-rz/rzg_hmi_sdk.git;protocol=https;branch=main \
	file://images/ \
	file://demo-launcher.service \
	file://start_demo.sh \
	file://flutter_material3_demo.sh \
	file://flutter_animation_demo.sh \
	file://flutter_deeplink_store_demo.sh \
	file://icon_demo-launcher.png \
"
SRCREV = "06c653cd004f61d6558b2e03a7ca8dff0d72e143"

PV = "2.0+git${SRCPV}"

S = "${WORKDIR}/git/sample_app/lvgl/lvgl_demo_launcher"

inherit systemd

DEPENDS += " lvgl tomlc99 wayland pkgconfig-native json-glib "
TARGET_CFLAGS += "-DLV_CONF_INCLUDE_SIMPLE -I${STAGING_DIR_HOST}/usr/include/lvgl"

EXTRA_OEMAKE = "'MACHINE =${MACHINE}'"

PATCHTOOL = "git"

do_install () {
    install -d ${D}${bindir}
    install -d ${D}${datadir}
    install -d ${D}${datadir}/demo-launcher
    install -d ${D}${datadir}/demo-launcher/images

    # Install app files
    install -D -m 0644 ${WORKDIR}/demo-launcher.service ${D}${systemd_system_unitdir}/demo-launcher.service
    install -m 0755 ${S}/demo-launcher ${D}${bindir}/demo-launcher
    install -m 0755 ${WORKDIR}/start_demo.sh ${D}${datadir}/demo-launcher/start_demo.sh
    install -m 0755 ${WORKDIR}/flutter_material3_demo.sh ${D}${datadir}/demo-launcher/flutter_material3_demo.sh
    install -m 0755 ${WORKDIR}/flutter_animation_demo.sh ${D}${datadir}/demo-launcher/flutter_animation_demo.sh
    install -m 0755 ${WORKDIR}/flutter_deeplink_store_demo.sh ${D}${datadir}/demo-launcher/flutter_deeplink_store_demo.sh
    install -m 0644 ${WORKDIR}/icon_demo-launcher.png ${D}${datadir}/demo-launcher/icon_demo-launcher.png

    # Install configuration file
    install -m 0644 ${S}/demo-launcher.json ${D}${datadir}/demo-launcher/demo-launcher.json

    # Install image files
    install -m 0644 ${WORKDIR}/images/* ${D}${datadir}/demo-launcher/images
}

FILES:${PN} += " \
    ${bindir}/demo-launcher \
    ${datadir}/demo-launcher/* \
    ${systemd_system_unitdir}/demo-launcher.service \
"

SYSTEMD_SERVICE:${PN} = "demo-launcher.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"
