# Copyright (C) 2017 Afero, Inc. All rights reserved

DESCRIPTION = "Afero Utilties"
SECTION = "examples"
DEPENDS = ""
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

inherit autotools
SRC_URI = "git://git@github.com/AferoCE/af-util;protocol=ssh"
SRCREV = "4d1734e6f391a1e7e6598109580b32892c266e4e"

S = "${WORKDIR}/git/pkg"

TARGET_CFLAGS += "${@base_conditional('BUILD_TARGET','release','-DBUILD_TARGET_RELEASE','-DBUILD_TARGET_DEBUG', d)}"

PARALLEL_MAKE = ""

