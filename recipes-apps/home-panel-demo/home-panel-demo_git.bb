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
SRCREV = "6fef486fef377082330803b3b63fa47057dd4497"

SRC_URI_append_smarc-rzg2l = " \
	file://home-panel-demo-add-a-video-playe.patch \
"

PV = "1.0+git${SRCPV}"

S = "${WORKDIR}/git/sample_app/chromium/home_panel_demo"

RDEPENDS_${PN} += " bash "
DEPENDS = "nodejs-native"

PATCHTOOL = "git"

do_configure() {
    cd ${S}
    npm install
}

do_compile() {
    cd ${S}
    if [ "${MACHINE}" = "smarc-rzg2lc" ]; then
        npm run build:RZG2LC
    else
        npm run build
    fi
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
