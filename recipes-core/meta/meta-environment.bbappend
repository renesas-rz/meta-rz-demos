create_sdk_files:append() {
	echo 'export PATH=${SDKPATHNATIVE}${prefix_nativesdk}/share/flutter/sdk/bin:$PATH' >> $script
}
