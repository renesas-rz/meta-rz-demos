SUMMARY = "Benchmark tool package group"
DESCRIPTION = "This group includes benchmark tools that can be run \
on RZ/G Verified Linux Package and Board Support Package. \
glmark2 licensed under GPLv3 is also included. \
"

inherit packagegroup

PACKAGES = "\
	${PN} \
"

RDEPENDS:${PN} = "\
	iperf3 \
	sysbench \
	stress-ng \
	coremark \
	fio \
"
RDEPENDS:${PN} += "${@oe.utils.conditional('MACHINE', 'smarc-rzg2lc', 'glmark2', '', d)}"
RDEPENDS:${PN} += "${@oe.utils.conditional('MACHINE', 'smarc-rzg2l', 'glmark2', '', d)}"
RDEPENDS:${PN} += "${@oe.utils.conditional('MACHINE', 'smarc-rzg3e', 'glmark2', '', d)}"
RDEPENDS:${PN} += "${@oe.utils.conditional('MACHINE', 'smarc-rzg3l', 'glmark2', '', d)}"
