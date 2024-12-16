DESCRIPTION = "Chromium demo web application for RZG2L"
SUMMARY = "RZ Linux Chromium web application demonstration software"
SECTION = "application"
LICENSE = "MIT & OFL-1.1"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=1de290628be92a7922bba96d14537fee \
		    file://src/assets/fonts/OFL.txt;md5=30de3a874e3399fc7b9d1bec045d3166"

PR = "r0"

SRC_URI = " \
	git://github.com/renesas-rz/rzg_hmi_sdk.git;protocol=https;branch=main \
	file://chromium-app.sh 	\
"
SRCREV = "6216927aec16893613d21d2ce2f79076f0eb71d9"

PV = "1.0+git${SRCPV}"

S = "${WORKDIR}/git/sample_app/chromium/home_panel_demo"

RDEPENDS_${PN} += " bash "
DEPENDS = "nodejs-native"

do_configure() {
    cd ${S}
    npm install
}

do_compile() {
    cd ${S}
    npm run build
}

do_install() {
    install -d ${D}${datadir}/chromium_demo
    install -m 0755 ${WORKDIR}/chromium-app.sh ${D}${datadir}/chromium_demo/chromium-app.sh
    install -d ${D}${datadir}/chromium_demo/dist
    cp -r ${S}/dist/* ${D}${datadir}/chromium_demo/dist
}

FILES_${PN} = " \
    ${datadir}/chromium_demo/chromium-app.sh \
    ${datadir}/chromium_demo/* \
"
