# Copyright (C) 2017 Afero, Inc. All rights reserved

DESCRIPTION = "Afero IPC"
SECTION = "examples"
DEPENDS = "libevent af-util"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

inherit autotools
SRC_URI = "git://git@github.com/AferoCE/af-ipc;protocol=ssh"
SRCREV = "478f335569f867aa0d82038cfa6031b487c122a7"

S = "${WORKDIR}/git/pkg"

TARGET_CFLAGS += "${@base_conditional('BUILD_TARGET','release','-DBUILD_TARGET_RELEASE','-DBUILD_TARGET_DEBUG', d)}"

PARALLEL_MAKE = ""

