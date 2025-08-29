SUMMARY = "RZ demo-launcher"
SECTION = "application"

LICENSE = "MIT & OFL-1.1"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=ac55c209a96858b87de6e4b277a4a014 \
                    file://src/asset/font/LICENSE.txt;md5=1f319ca1887afc3591b1dccdd8530c58"

SRC_URI = " \
	git://github.com/renesas-rz/rzg_hmi_sdk.git;protocol=https;branch=main \
	file://images/* \
	file://demo-launcher.service \
	file://start_demo.sh \
"
SRCREV = "6fef486fef377082330803b3b63fa47057dd4497"

PV = "1.0+git${SRCPV}"

S = "${WORKDIR}/git/sample_app/lvgl/lvgl_demo_launcher"

inherit systemd

DEPENDS += " lvgl lv-drivers tomlc99 wayland pkgconfig-native json-glib "
TARGET_CFLAGS += "-DLV_CONF_INCLUDE_SIMPLE -I${STAGING_DIR_HOST}/usr/include/lvgl -I${STAGING_DIR_HOST}/usr/include/lvgl/lv_drivers"

EXTRA_OEMAKE = "'MACHINE =${MACHINE}'"

do_install () {
    install -d ${D}${bindir}
    install -d ${D}${datadir}
    install -d ${D}${datadir}/demo-launcher
    install -d ${D}${datadir}/demo-launcher/images

    # Install app files
    install -D -m 0644 ${WORKDIR}/demo-launcher.service ${D}${systemd_system_unitdir}/demo-launcher.service
    install -m 0755 ${S}/demo-launcher ${D}${bindir}/demo-launcher
    install -m 0755 ${WORKDIR}/start_demo.sh ${D}${datadir}/demo-launcher/start_demo.sh

    # Install configuration file
    install -m 0644 ${S}/demo-launcher.json ${D}${datadir}/demo-launcher/demo-launcher.json

    # Install image files
    install -m 0644 ${WORKDIR}/images/* ${D}${datadir}/demo-launcher/images
}

FILES_${PN} += " \
    ${bindir}/demo-launcher \
    ${datadir}/demo-launcher/* \
    ${systemd_system_unitdir}/demo-launcher.service \
"

SYSTEMD_SERVICE_${PN} = "demo-launcher.service"
SYSTEMD_AUTO_ENABLE_${PN} = "enable"
