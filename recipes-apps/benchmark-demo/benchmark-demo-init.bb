SUMMARY = "Startup script for RZ Linux benchmark demonstration software"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
	file://start_demo.sh \
	file://video-dec.sh \
	file://video-enc.sh \
	file://weston_demo.ini \
	file://icon_benchmark_demo.png \
"

S = "${WORKDIR}"

do_install() {
	# Install benchmark demo icon
	install -D -m 0644 ${WORKDIR}/icon_benchmark_demo.png ${D}${datadir}/benchmark_demo/icon_benchmark_demo.png

	# Install benchmark demo start script
	install -m 0755 ${WORKDIR}/start_demo.sh ${D}${datadir}/benchmark_demo/start_demo.sh
	install -m 0755 ${WORKDIR}/video-dec.sh ${D}${datadir}/benchmark_demo/video-dec.sh
	install -m 0755 ${WORKDIR}/video-enc.sh ${D}${datadir}/benchmark_demo/video-enc.sh
	install -m 0755 ${WORKDIR}/weston_demo.ini ${D}${datadir}/benchmark_demo/weston_demo.ini
}

inherit features_check systemd

RDEPENDS_${PN} = "benchmark-demo"

FILES_${PN} += " \
	${datadir}/benchmark_demo/start_demo.sh \
	${datadir}/benchmark_demo/video-dec.sh \
	${datadir}/benchmark_demo/video-enc.sh \
	${datadir}/benchmark_demo/weston_demo.ini \
	${datadir}/benchmark_demo/icon_benchmark_demo.png \
"

