# Copyright (C) 2017 Afero, Inc. All rights reserved

DESCRIPTION = "Afero binary files"
SECTION = "examples"
DEPENDS = "libevent json-c zlib openssl curl af-conn af-ipc af-util attrd otamgr"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRC_URI = "git://git@github.com/AferoCE/am335x-binaries;protocol=ssh"
SRCREV = "582f8b01a151f78e9683d15126a9710cf3d7b58b"

S = "${WORKDIR}/git"

do_install() {
    install -d ${D}${bindir}
    install -d ${D}${sysconfdir}
    install -Dm 755 ${S}/${BUILD_TARGET}/usr/bin/afsecd ${D}${bindir}
    install -Dm 755 ${S}/${BUILD_TARGET}/usr/bin/hubby ${D}${bindir}
    install -Dm 755 ${S}/${BUILD_TARGET}/usr/bin/provision ${D}${bindir}
    install -Dm 644 ${S}/${BUILD_TARGET}/etc/hub.profile ${D}${sysconfdir}
}
