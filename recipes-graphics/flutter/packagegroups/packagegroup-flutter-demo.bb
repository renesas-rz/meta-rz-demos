SUMMARY = "Flutter sample applications package group"
DESCRIPTION = "Flutter sample applications for RZ/G HMI SDK"

inherit packagegroup

PACKAGES = "\
    ${PN} \
"

RDEPENDS:${PN} += " \
    flutter-wayland-client \
    flutter-pi \
    flutter-samples-deeplink-store-example \
    flutter-samples-animations \
    flutter-samples-material-3-demo \
"
