# Deploy the engine_sdk.zip (release mode) to a stable location so the
# nativesdk-flutter-sdk bbappend can consume the host cross gen_snapshot
# from inside it.
#
# Upstream meta-flutter zips up sdk/clang_x64/{gen_snapshot,icudtl.dat,...}
# into engine_sdk.zip and packages it into flutter-engine-sdk-dev.  We
# copy just the release-mode zip to ${DEPLOYDIR} for downstream use.

inherit deploy

do_deploy() {
    install -d ${DEPLOYDIR}/flutter-engine/linux-arm64-release
    install -m 0644 \
        ${D}${datadir}/flutter/${FLUTTER_SDK_VERSION}/release/engine_sdk.zip \
        ${DEPLOYDIR}/flutter-engine/linux-arm64-release/engine_sdk.zip
}

# do_deploy must run after do_install (which produces the zip in ${D})
addtask deploy after do_install before do_build
