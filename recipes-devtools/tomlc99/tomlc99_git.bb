DESCRIPTION = "tomlc99 is a TOML compliant C library in c99"
SECTION = "libs"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=95bbe2f9180443b5dcef3fb959804a65"

SRC_URI = "git://github.com/cktan/tomlc99;protocol=https;branch=master \
	file://0001-Set-soname-to-libtoml-library.patch \
"
SRCREV = "5221b3d3d66c25a1dc6f0372b4f824f1202fe398"

PV = "1.0+git${SRCPV}"

S = "${WORKDIR}/git"

do_install () {
	# Create destination directories and copy files
	install -D -m 0755 ${S}/libtoml.so.1.0 ${D}/${libdir}/libtoml.so.1.0
	cd ${D}/${libdir}/
	ln -sf libtoml.so.1.0 libtoml.so.1
	ln -sf libtoml.so.1 libtoml.so
	install -D -m 644 ${S}/toml.h ${D}/${includedir}/toml.h
}
