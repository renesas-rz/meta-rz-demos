DESCRIPTION = "Chromium demo web application for RZG2L"
SUMMARY = "RZ Linux Chromium web application demonstration software"
SECTION = "application"
LICENSE = "MIT & OFL-1.1"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302 \
		    file://src/assets/fonts/OFL.txt;md5=30de3a874e3399fc7b9d1bec045d3166"

PR = "r0"

SRC_URI = " \
	git://github.com/renesas-rz/rzg_hmi_sdk.git;protocol=https;branch=main \
	file://chromium-app.sh 	\
"
SRCREV = "d55825b3719309d868977a7cc3f0b329750664a2"

PV = "2.0+git${SRCPV}"

S = "${WORKDIR}/git/sample_app/chromium/home_panel_demo"

RDEPENDS:${PN} += " bash "
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
    elif [ "${MACHINE}" = "smarc-rzg3e" ]; then
        npm run build:RZG3E
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

FILES:${PN} = " \
    ${datadir}/chromium_demo/chromium-app.sh \
    ${datadir}/chromium_demo/* \
"
