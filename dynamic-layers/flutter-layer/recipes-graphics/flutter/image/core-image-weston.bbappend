# Add Flutter SDK toolchain content to the Yocto SDK (populate_sdk),
# but only if the meta-flutter layer is present in this build.
# This keeps `bitbake core-image-weston -c populate_sdk` working
# even when customers do NOT add meta-flutter to their bblayers.conf.

TOOLCHAIN_HOST_TASK:append = "${@bb.utils.contains('BBFILE_COLLECTIONS', 'flutter-layer', ' nativesdk-flutter-sdk', '', d)}"
TOOLCHAIN_TARGET_TASK:append = "${@bb.utils.contains('BBFILE_COLLECTIONS', 'flutter-layer', ' packagegroup-flutter-sdk-toolchain', '', d)}"
