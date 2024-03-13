SUMMARY = "RZ Linux benchmark demo package group"
DESCRIPTION = "This group includes RZ Linux benchmark demo and \
related modules. glmark2 licensed under GPLv3 is also included. \
To introduce this group, set WHITELIST_GPL-3.0 in build/conf/local.conf.\
"

inherit packagegroup

PACKAGES = "\
	${PN} \
"

RDEPENDS_${PN} = "\
	bigbuckbunny-sample \
	glmark2 \
	benchmark-demo \
	benchmark-demo-init \
"
