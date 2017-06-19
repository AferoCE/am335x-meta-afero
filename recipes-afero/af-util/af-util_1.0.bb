# Copyright (C) 2017 Afero, Inc. All rights reserved

DESCRIPTION = "Afero Utilties"
SECTION = "examples"
DEPENDS = ""
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

inherit autotools
SRC_URI = "git://git@github.com/AferoCE/af-util;protocol=ssh"
SRCREV = "d5520c22bc6d9bd3280c00ef6d00a79f8134e959"

S = "${WORKDIR}/git/pkg"

TARGET_CFLAGS += "${@base_conditional('BUILD_TARGET','release','-DBUILD_TARGET_RELEASE','-DBUILD_TARGET_DEBUG', d)}"

PARALLEL_MAKE = ""

