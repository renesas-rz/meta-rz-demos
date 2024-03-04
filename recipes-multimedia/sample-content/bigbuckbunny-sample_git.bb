SUMMARY = "Big Buck Bunny movie - 1080P"
LICENSE = "CC-BY-3.0"
HOMEPAGE = "https://peach.blender.org/"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/CC-BY-3.0;md5=dfa02b5755629022e267f10b9c0a2ab7"

SRC_URI = "git://github.com/bower-media-samples/big-buck-bunny-1080p-60fps-30s.git;protocol=https;branch=master"
SRCREV = "c4c7ec6aa5d68944d32faa28f332f999c8866cbc"

SRC_URI[md5sum] = "b6088330b9a759f14bf717197a97720c"
SRC_URI[sha256sum] = "521a78e22a4065814066d8a60d53b99472a80d6a8fb0908370df0410298b4b12"

S = "${WORKDIR}/git"

inherit allarch

do_install() {
    install -D -m 0644 ${S}/video.mp4 ${D}${datadir}/movies/big_buck_bunny_1080p.mp4
}

FILES_${PN} += "${datadir}/movies"
