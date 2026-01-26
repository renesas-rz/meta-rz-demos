SUMMARY = "Startup script for RZ Linux benchmark demonstration software"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
	file://video-dec.sh \
	file://video-enc.sh \
"

S = "${WORKDIR}"

do_install() {
	# Install benchmark demo start script
	install -D -m 0755 ${WORKDIR}/video-dec.sh ${D}${datadir}/benchmark_demo/video-dec.sh
	install -m 0755 ${WORKDIR}/video-enc.sh ${D}${datadir}/benchmark_demo/video-enc.sh
}

inherit features_check systemd

RDEPENDS:${PN} = "benchmark-demo"

FILES:${PN} += " \
	${datadir}/benchmark_demo/video-dec.sh \
	${datadir}/benchmark_demo/video-enc.sh \
"
