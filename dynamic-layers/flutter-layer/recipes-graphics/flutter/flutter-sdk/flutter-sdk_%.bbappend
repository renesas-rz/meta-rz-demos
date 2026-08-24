# For the nativesdk variant of flutter-sdk (i.e. nativesdk-flutter-sdk),
# inject the arm64 cross gen_snapshot into Flutter's artifact cache so
# that the `flutter` tool can produce arm64 libapp.so on an x86_64 host.
#
# Source: ${DEPLOY_DIR_IMAGE}/flutter-engine/linux-arm64-release/engine_sdk.zip
# (deployed by our flutter-engine_%.bbappend)

# unzip-native: tool we use during do_install
DEPENDS:append:class-nativesdk = " unzip-native"

# Task-level dep: bypass nativesdk name-mangling and pin to the
# specific task on the target-arch flutter-engine recipe.
do_install[depends] += "flutter-engine:do_deploy"

FLUTTER_SDK_DIR ?= "${datadir}/flutter/sdk"

do_install:append:class-nativesdk() {
    ENGINE_ZIP="${DEPLOY_DIR_IMAGE}/flutter-engine/linux-arm64-release/engine_sdk.zip"
    DEST="${D}${FLUTTER_SDK_DIR}/bin/cache/artifacts/engine/linux-arm64-release"

    if [ ! -f "${ENGINE_ZIP}" ]; then
        bbfatal "engine_sdk.zip not found at ${ENGINE_ZIP}; flutter-engine must build first"
    fi

    install -d "${DEST}"

    # Extract just the host->arm64 cross gen_snapshot to a temp dir
    TMP=$(mktemp -d)
    unzip -q -j "${ENGINE_ZIP}" "sdk/clang_x64/gen_snapshot" -d "${TMP}"
    install -m 0755 "${TMP}/gen_snapshot" "${DEST}/gen_snapshot"
    rm -rf "${TMP}"

    # Mirror the engine cache stamp so flutter tool doesn't try to refresh
    if [ -f "${D}${FLUTTER_SDK_DIR}/bin/cache/engine-linux-x64.stamp" ]; then
        install -m 0644 \
            "${D}${FLUTTER_SDK_DIR}/bin/cache/engine-linux-x64.stamp" \
            "${D}${FLUTTER_SDK_DIR}/bin/cache/engine-linux-arm64.stamp"
    fi
}

# The injected gen_snapshot is x86_64 (cross-compiler); silence QA warnings
# that would otherwise complain about a host binary in a nativesdk package.
INSANE_SKIP:${PN}:append:class-nativesdk = " arch already-stripped staticdev file-rdeps"
