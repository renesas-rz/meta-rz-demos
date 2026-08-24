SUMMARY = "Flutter SDK toolchain package group"
DESCRIPTION = "Target-side packages required in the SDK toolchain to \
develop custom Flutter embedders or applications against the on-device \
Flutter engine."

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PACKAGES = "\
        ${PN} \
"

RDEPENDS:${PN} = "\
        flutter-engine \
        flutter-engine-dev \
"
