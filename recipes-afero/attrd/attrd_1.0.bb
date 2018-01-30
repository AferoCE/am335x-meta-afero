# Copyright (C) 2017 Afero, Inc. All rights reserved

DESCRIPTION = "Afero Attribute Deamon"
SECTION = "examples"
DEPENDS = "libevent af-util af-ipc"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

inherit autotools
SRC_URI = "git://git@github.com/AferoCE/attrd;protocol=ssh;branch=m6"
SRCREV = "b03d4214897ad179e764cc79c586f0a60250f903"

S = "${WORKDIR}/git/pkg"

TARGET_CFLAGS += "${@base_conditional('BUILD_TARGET','release','-DBUILD_TARGET_RELEASE','-DBUILD_TARGET_DEBUG', d)}"

PARALLEL_MAKE = ""

