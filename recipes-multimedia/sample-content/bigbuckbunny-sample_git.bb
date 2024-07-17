SUMMARY = "Big Buck Bunny movie - 1080P"
LICENSE = "CC-BY-3.0"
HOMEPAGE = "https://peach.blender.org/"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/CC-BY-3.0;md5=dfa02b5755629022e267f10b9c0a2ab7"

SRC_URI = "git://github.com/renesas-rz/media.git;protocol=https;branch=main"
SRCREV = "d2bd12a41b496816ddb3dc040107690b38226bf1"

S = "${WORKDIR}/git"

inherit allarch

do_install() {
    install -D -m 0644 ${S}/Big_Buck_Bunny/big_buck_bunny_1080p_30fps_30s.mp4 ${D}${datadir}/movies/big_buck_bunny_1080p_30fps_30s.mp4
}

FILES_${PN} += "${datadir}/movies"
