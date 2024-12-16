SUMMARY = "RZ/G Linux HMI SDK demo package group"
DESCRIPTION = "This group includes RZ Linux HMI SDK demos and \
related modules. glmark2 licensed under GPLv3 is also included. \
To introduce this group, set WHITELIST_GPL-3.0 in build/conf/local.conf.\
"

inherit packagegroup

PACKAGES = "\
	${PN} \
"

RDEPENDS_${PN} = "\
	benchmark-demo \
	benchmark-demo-init \
	demo-launcher \
"
RDEPENDS_${PN} += "${@oe.utils.conditional('MACHINE', 'smarc-rzg2lc', 'glmark2', '', d)}"
RDEPENDS_${PN} += "${@oe.utils.conditional('MACHINE', 'smarc-rzg2l', 'bigbuckbunny-sample glmark2 python3 python3-py home-panel-demo', '', d)}"
