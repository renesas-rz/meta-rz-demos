SUMMARY = "Chromium demo package group"
DESCRIPTION = "This group includes utility packages that can be used \
with Chromium demo program."

inherit packagegroup

PACKAGES = "\
	${PN} \
"

RDEPENDS_${PN} = "\
	chromium-ozone-wayland \
	ntp  \
	ttf-sazanami-gothic \
	ttf-sazanami-mincho \
	adwaita-icon-theme-cursors \
"
