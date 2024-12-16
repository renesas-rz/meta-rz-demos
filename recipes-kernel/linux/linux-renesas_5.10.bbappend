FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "${@oe.utils.conditional('MACHINE', 'smarc-rzg2ul', 'file://disable-console.cfg', '', d)}"
