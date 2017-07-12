# Copyright (C) 2017 Afero, Inc. All rights reserved

DESCRIPTION = "Afero Attribute Deamon"
SECTION = "examples"
DEPENDS = "libevent af-util af-ipc"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

inherit autotools
SRC_URI = "git://git@github.com/AferoCE/attrd;protocol=ssh"
SRCREV = "0a400fef4a6481fd1531116a8a4bdac9c8a21977"

S = "${WORKDIR}/git/pkg"

TARGET_CFLAGS += "${@base_conditional('BUILD_TARGET','release','-DBUILD_TARGET_RELEASE','-DBUILD_TARGET_DEBUG', d)}"

PARALLEL_MAKE = ""

