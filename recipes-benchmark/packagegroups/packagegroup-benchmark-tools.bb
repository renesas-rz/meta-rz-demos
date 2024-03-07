SUMMARY = "Benchmark tool package group"
DESCRIPTION = "This group includes benchmark tools that can be run \
on RZ/G Verified Linux Package."

inherit packagegroup

PACKAGES = "\
	${PN} \
"

RDEPENDS_${PN} = "\
	iperf3 \
	sysbench \
	stress-ng \
	coremark \
"
