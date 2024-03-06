SUMMARY = "RZ Linux benchmark demonstration software"
SECTION = "application"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=ac55c209a96858b87de6e4b277a4a014"

SRC_URI = " \
	git://github.com/renesas-rz/rz_benchmark_demo.git;protocol=https;branch=main \
"
SRCREV = "28be02eb066dec55823f10c1f4a1b5fdf0338212"

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
