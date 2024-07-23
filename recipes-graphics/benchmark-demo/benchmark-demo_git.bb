SUMMARY = "RZ Linux benchmark demonstration software"
SECTION = "application"

LICENSE = "MIT & OFL-1.1"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=ac55c209a96858b87de6e4b277a4a014 \
		    file://src/gui/material/font/LICENSE.txt;md5=1f319ca1887afc3591b1dccdd8530c58"

SRC_URI = " \
	git://github.com/renesas-rz/rz_benchmark_demo.git;protocol=https;branch=main \
"
SRCREV = "a413e88fbcbc30fbd23a4494bd556c529c782347"

PV = "1.0+git${SRCPV}"

S = "${WORKDIR}/git"

DEPENDS += " lvgl lv-drivers tomlc99 wayland "
TARGET_CFLAGS += "-DLV_CONF_INCLUDE_SIMPLE -I${STAGING_DIR_HOST}/usr/include/lvgl -I${STAGING_DIR_HOST}/usr/include/lvgl/lv_drivers"

do_install () {
	install -D -m 0755 ${S}/rz_benchmark_demo ${D}/${bindir}/rz_benchmark_demo

	# Install configuration file
	install -D -m 0644 ${S}/config/rz_bench_config.toml ${D}${datadir}/benchmark_demo/rz_bench_config.toml
}

FILES_${PN} += "/usr/share/benchmark_demo/rz_bench_config.toml"
