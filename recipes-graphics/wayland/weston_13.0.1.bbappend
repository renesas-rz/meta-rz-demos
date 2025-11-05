FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append:smarc-rzg3e = " \ 
        file://0002-Fix-issue-window-always-appears-at-top-left-of-the-s.patch \
"

