# Copyright (C) 2017 Afero, Inc. All rights reserved

DESCRIPTION = "Afero Edge Device Demo"
SECTION = "examples"
DEPENDS = "libevent af-util af-ipc afero-binaries af-conn"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

inherit autotools
SRC_URI = "git://git@github.com/AferoCE/edge-demo;protocol=ssh"
SRCREV = "a46882a42bcfc9dee2890ae4671b37e38632e14e"

S = "${WORKDIR}/git/pkg"

TARGET_CFLAGS += "${@base_conditional('BUILD_TARGET','release','-DBUILD_TARGET_RELEASE','-DBUILD_TARGET_DEBUG', d)}"

PARALLEL_MAKE = ""

