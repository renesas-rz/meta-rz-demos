SUMMARY = "LVGL demo package group"
DESCRIPTION = "This group includes utility packages that can be used \
with LVGL demo program."

inherit packagegroup

PACKAGES = "\
	${PN} \
"

RDEPENDS_${PN} = "\
	lvgl \
	lv-drivers \
	tomlc99 \
"
