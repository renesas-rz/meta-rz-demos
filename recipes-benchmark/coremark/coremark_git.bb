SUMMARY = "CoreMark"
DESCRIPTION = "CoreMark is a simple/small benchmark tool. It displays a single number score for performance of CPUs."
SECTION = "benchmark"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=0a18b17ae63deaa8a595035f668aebe1"

SRC_URI = "git://github.com/eembc/coremark.git;protocol=https;branch=main"
SRCREV = "f3e8f2e0941e42961aadcc52750b1b5577c157c9"

PV = "1.01+git${SRCPV}"

S = "${WORKDIR}/git"

EXTRA_OEMAKE += "XCFLAGS='-DPERFORMANCE_RUN=1' OUTNAME=coremark link"

do_install () {
   install -D -m 755 ${S}/coremark ${D}${bindir}/coremark
}
