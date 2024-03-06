SUMMARY = "Startup script and systemd service file for RZ Linux benchmark demonstration software"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
	file://rz_benchmark_demo.service \
	file://start_demo.sh \
	file://video-dec.sh \
	file://video-enc.sh \
	file://weston_demo.ini \
	file://icon_benchmark_demo.png \
"

S = "${WORKDIR}"

do_install() {
	# Install benchmark demo systemd service
	install -D -m 0644 ${WORKDIR}/rz_benchmark_demo.service ${D}${systemd_system_unitdir}/rz_benchmark_demo.service

	# Install benchmark demo start script
	install -d ${D}/home/root
	install -m 0755 ${WORKDIR}/start_demo.sh ${D}/home/root/start_demo.sh
	install -m 0755 ${WORKDIR}/video-dec.sh ${D}/home/root/video-dec.sh
	install -m 0755 ${WORKDIR}/video-enc.sh ${D}/home/root/video-enc.sh
	install -m 0755 ${WORKDIR}/weston_demo.ini ${D}/home/root/weston_demo.ini

	# Install benchmark demo icon
	install -D -m 0644 ${WORKDIR}/icon_benchmark_demo.png ${D}${datadir}/benchmark_demo/icon_benchmark_demo.png
}

inherit features_check systemd

RDEPENDS_${PN} = "benchmark-demo"

FILES_${PN} += " \
	${systemd_system_unitdir}/rz_benchmark_demo.service \
	/home/root/start_demo.sh \
	/home/root/video-dec.sh \
	/home/root/video-enc.sh \
	/home/root/weston_demo.ini \
	${datadir}/benchmark_demo/icon_benchmark_demo.png \
"

SYSTEMD_SERVICE_${PN} = "rz_benchmark_demo.service"
