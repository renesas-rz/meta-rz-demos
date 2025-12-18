FILESEXTRAPATHS:prepend := ":${THISDIR}/${PN}:"

SRC_URI:append:smarc-rzg3e = " file://audio.sh "

do_install:append:smarc-rzg3e() {
    install -d ${D}/${sysconfdir}/profile.d
    install -m 0755 ${WORKDIR}/audio.sh ${D}/${sysconfdir}/profile.d/audio.sh
}
