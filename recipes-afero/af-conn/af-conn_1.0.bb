# Copyright (C) 2017 Afero, Inc. All rights reserved

DESCRIPTION = "Afero Connectivity"
SECTION = "examples"
DEPENDS = "attrd af-ipc af-util libevent libpcap openssl afero-binaries"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

inherit autotools
SRC_URI = "git://git@github.com/AferoCE/af-conn;protocol=ssh"
SRCREV = "75137742d3b62aa6033482bc76c778f4ad855dde"

S = "${WORKDIR}/git/pkg"

EXTRA_OECONF = "WAN_RIL=ELS61"

TARGET_CFLAGS += "${@base_conditional('BUILD_TARGET','release','-DBUILD_TARGET_RELEASE','-DBUILD_TARGET_DEBUG', d)}"

PARALLEL_MAKE = ""
SRC_URI += " file://switch_route.sh"
SRC_URI += " file://wancontrol"
SRC_URI += " file://wannetwork"
SRC_URI += " file://carriers"
SRC_URI += " file://net_capabilities"
SRC_URI += " file://get_netif_names"
SRC_URI += " file://netif_names"
SRC_URI += " file://wifi_event.sh"

# install the files into the destination directory so it can be packaged
# correctly
do_install_append() {
    echo "do_install() af-conn directories and configuration files ...."

    install -d ${D}${sysconfdir}
    install -d ${D}${sysconfdir}/af-conn/
    install -d ${D}${libdir}
    install -d ${D}${libdir}/af-conn/
    install -Dm 755 ${WORKDIR}/switch_route.sh  ${D}${libdir}/af-conn/
    install -Dm 755 ${WORKDIR}/wancontrol  ${D}${libdir}/af-conn/
    install -Dm 755 ${WORKDIR}/wannetwork  ${D}${libdir}/af-conn/
    install -Dm 755 ${WORKDIR}/carriers ${D}${sysconfdir}/af-conn/
    install -Dm 755 ${WORKDIR}/net_capabilities ${D}${libdir}/af-conn
    install -Dm 644 ${WORKDIR}/get_netif_names ${D}${libdir}/af-conn/
    install -Dm 644 ${WORKDIR}/netif_names ${D}${sysconfdir}/af-conn/
    install -Dm 755 ${WORKDIR}/wifi_event.sh ${D}${libdir}/af-conn/
}
