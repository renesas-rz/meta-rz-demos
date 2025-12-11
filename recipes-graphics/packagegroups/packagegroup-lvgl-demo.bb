SUMMARY = "LVGL demo package group"
DESCRIPTION = "This group includes utility packages that can be used \
with LVGL demo program."

PACKAGE_ARCH = "${MACHINE_ARCH}"
inherit packagegroup

PACKAGES = "\
	${PN} \
"

RDEPENDS:${PN} = "\
	lvgl \
	tomlc99 \
"
