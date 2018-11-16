# Copyright (C) 2018 Afero, Inc. All rights reserved

DESCRIPTION = "Afero Dlink Extras - configuration and script files"
SECTION = "examples"
DEPENDS = ""
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

inherit systemd

TARGET_CFLAGS += "${@base_conditional('BUILD_TARGET','release','-DBUILD_TARGET_RELEASE','-DBUILD_TARGET_DEBUG', d)}"

SRC_URI += " file://logpush"
SRC_URI += " file://logpush.conf"

S = "${WORKDIR}"

do_install() {
    echo "do_install() af-extras-dlink script and configuration files ...."

    install -d ${D}${bindir}
    install -d ${D}${sysconfdir}

    install -Dm 755 ${WORKDIR}/logpush ${D}${bindir}
    install -Dm 644 ${WORKDIR}/logpush.conf ${D}${sysconfdir}
}

FILES_${PN} += " ${bindir}/logpush"
FILES_${PN} += " ${sysconfdir}/logpush.conf"
