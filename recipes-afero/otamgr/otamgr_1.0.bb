# Copyright (C) 2017 Afero, Inc. All rights reserved

DESCRIPTION = "Afero OTA Manager Sample Code"
SECTION = "examples"
DEPENDS = "libevent af-util af-ipc attrd"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

inherit autotools
SRC_URI = "git://git@github.com/AferoCE/otamgr;protocol=ssh"
SRCREV = "85fb930bef7eb5a185d088cc29b2b91344ffdc9b"

S = "${WORKDIR}/git/pkg"

TARGET_CFLAGS += "${@base_conditional('BUILD_TARGET','release','-DBUILD_TARGET_RELEASE','-DBUILD_TARGET_DEBUG', d)}"

PARALLEL_MAKE = ""

