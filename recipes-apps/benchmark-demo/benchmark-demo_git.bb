SUMMARY = "RZ Linux benchmark demonstration software"
SECTION = "application"

LICENSE = "MIT & OFL-1.1"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=a6f7398c4a545ebdc0f09e23a6127abd \
		    file://src/gui/material/font/LICENSE.txt;md5=1f319ca1887afc3591b1dccdd8530c58"

SRC_URI = " \
	git://github.com/renesas-rz/rz_benchmark_demo.git;protocol=https;branch=main \
"
SRCREV = "89f7ce29e8f4f58ce725a7d0df4722e34890d496"

PV = "2.0+git${SRCPV}"

S = "${WORKDIR}/git"

DEPENDS += " lvgl tomlc99 wayland pkgconfig-native "
TARGET_CFLAGS += "-DLV_CONF_INCLUDE_SIMPLE -I${STAGING_DIR_HOST}/usr/include/lvgl"

EXTRA_OEMAKE = "'MACHINE =${MACHINE}'"

do_install () {
	install -D -m 0755 ${S}/rz_benchmark_demo ${D}/${bindir}/rz_benchmark_demo

	# Install configuration file
	if [ "${MACHINE}" = "smarc-rzg2lc" ]; then
		install -D -m 0644 ${S}/config/rz_bench_config_g2lc.toml ${D}${datadir}/benchmark_demo/rz_bench_config.toml
	elif [ "${MACHINE}" = "smarc-rzg2ul" ]; then
		install -D -m 0644 ${S}/config/rz_bench_config_g2ul.toml ${D}${datadir}/benchmark_demo/rz_bench_config.toml
	else
		install -D -m 0644 ${S}/config/rz_bench_config.toml ${D}${datadir}/benchmark_demo/rz_bench_config.toml
	fi
}

FILES:${PN} += "/usr/share/benchmark_demo/rz_bench_config.toml"
