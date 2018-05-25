# Copyright (C) 2017 Afero, Inc. All rights reserved

DESCRIPTION = "Afero binary files"
SECTION = "examples"
DEPENDS = "libevent json-c zlib openssl curl af-ipc af-util attrd otamgr"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRC_URI = "git://git@github.com/AferoCE/am335x-binaries;protocol=ssh"
SRCREV = "a1c16f92cca26c4649bb566ec8ca8353a4ed17a3"

INHIBIT_DEFAULT_DEFS = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

S = "${WORKDIR}/git"

do_install() {
    install -d ${D}${bindir}
    install -d ${D}${sysconfdir}
    install -d ${D}${libdir}
    install -d ${D}${includedir}
    install -Dm 755 ${S}/${BUILD_TARGET}${bindir}/afsecd ${D}${bindir}
    install -Dm 755 ${S}/${BUILD_TARGET}${bindir}/hubby ${D}${bindir}
    install -Dm 755 ${S}/${BUILD_TARGET}${bindir}/provision ${D}${bindir}
    install -Dm 644 ${S}/${BUILD_TARGET}${sysconfdir}/hub.profile ${D}${sysconfdir}
    install -Dm 644 ${S}/${BUILD_TARGET}${sysconfdir}/app_ota_record.json ${D}${sysconfdir}
    install -Dm 644 ${S}/${BUILD_TARGET}${libdir}/libaf_edge.so.0.0.0 ${D}${libdir}
    ln -s libaf_edge.so.0.0.0 ${D}${libdir}/libaf_edge.so
    ln -s libaf_edge.so.0.0.0 ${D}${libdir}/libaf_edge.so.0
    install -Dm 644 ${S}/${BUILD_TARGET}${includedir}/aflib.h ${D}${includedir}
    install -Dm 644 ${S}/${BUILD_TARGET}${libdir}/libafwp.so.0.0.0 ${D}${libdir}
    ln -s libafwp.so.0.0.0 ${D}${libdir}/libafwp.so
    ln -s libafwp.so.0.0.0 ${D}${libdir}/libafwp.so.0
    install -Dm 644 ${S}/${BUILD_TARGET}${includedir}/afwp.h ${D}${includedir}
}

FILES_${PN} += " ${libdir}/libaf_edge.so ${libdir}/libaf_edge.so.0"
FILES_${PN} += " ${libdir}/libafwp.so ${libdir}/libafwp.so.0"
