# Copyright (C) 2017 Afero, Inc. All rights reserved

DESCRIPTION = "Afero OTA Manager Sample Code"
SECTION = "examples"
DEPENDS = "libevent af-util af-ipc attrd"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

inherit autotools
SRC_URI = "git://git@github.com/AferoCE/otamgr;protocol=ssh"
SRCREV = "4a358942e23f03ba009324c6d5e8125697680ad7"

S = "${WORKDIR}/git/pkg"

TARGET_CFLAGS += "${@base_conditional('BUILD_TARGET','release','-DBUILD_TARGET_RELEASE','-DBUILD_TARGET_DEBUG', d)}"

PARALLEL_MAKE = ""

