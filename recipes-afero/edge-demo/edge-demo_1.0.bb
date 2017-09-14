# Copyright (C) 2017 Afero, Inc. All rights reserved

DESCRIPTION = "Afero Edge Device Demo"
SECTION = "examples"
DEPENDS = "libevent af-util af-ipc afero-binaries"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

inherit autotools
SRC_URI = "git://git@github.com/AferoCE/edge-demo;protocol=ssh"
SRCREV = "061b6235f5273574ad0cb8213b4390505bc1e3fe"

S = "${WORKDIR}/git/pkg"

TARGET_CFLAGS += "${@base_conditional('BUILD_TARGET','release','-DBUILD_TARGET_RELEASE','-DBUILD_TARGET_DEBUG', d)}"

PARALLEL_MAKE = ""

