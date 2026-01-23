SUMMARY = "RZ/G Linux HMI SDK demo package group"
DESCRIPTION = "This group includes RZ Linux HMI SDK demos and \
related modules. \
"

inherit packagegroup

PACKAGES = "\
	${PN} \
"

RDEPENDS:${PN} = "\
	benchmark-demo \
	benchmark-demo-init \
	demo-launcher \
	lvgl-home-panel-demo \
"
RDEPENDS:${PN} += "${@oe.utils.conditional('MACHINE', 'smarc-rzg2l', 'bigbuckbunny-sample', '', d)}"
RDEPENDS:${PN} += "${@oe.utils.conditional('MACHINE', 'smarc-rzg3e', 'bigbuckbunny-sample home-panel-demo', '', d)}"
