# Copyright (C) 2017 Afero, Inc. All rights reserved

DESCRIPTION = "Afero Connectivity"
SECTION = "examples"
DEPENDS = "attrd af-ipc af-util libevent libpcap openssl afero-binaries"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

inherit autotools
SRC_URI = "git://git@github.com/AferoCE/af-conn;protocol=ssh"
SRCREV = "19194f4b0b175924e0d709831b0a7e95b7f9797f"

S = "${WORKDIR}/git/pkg"

EXTRA_OECONF = "WAN_RIL=ELS61"

TARGET_CFLAGS += "${@base_conditional('BUILD_TARGET','release','-DBUILD_TARGET_RELEASE','-DBUILD_TARGET_DEBUG', d)}"

PARALLEL_MAKE = ""
SRC_URI += " file://carriers"
SRC_URI += " file://firewall.user"
SRC_URI += " file://fwcfg"
SRC_URI += " file://switch_route_to.sh"
SRC_URI += " file://wancontrol"
SRC_URI += " file://wannetwork"
SRC_URI += " file://wifi_watcher"
SRC_URI += " file://afero_whitelist.prod"
SRC_URI += " file://create_afero_whitelist.sh"
SRC_URI += " file://afero_net_cap"
SRC_URI += " file://afero_get_netif_names"
SRC_URI += " file://afero_netif_names"
SRC_URI += " file://wifi_event.sh"

# install the files into the destination directory so it can be packaged
# correctly
do_install_append() {
    echo "do_install() af-conn directories and configuration files ...."

    install -d ${D}${bindir}
    install -d ${D}${sysconfdir}
    install -d ${D}${sysconfdir}/config/
    install -d ${D}${sysconfdir}/wan/
    install -d ${D}${base_libdir}
    install -Dm 755 ${WORKDIR}/fwcfg  ${D}${bindir}
    install -Dm 755 ${WORKDIR}/switch_route_to.sh  ${D}${bindir}
    install -Dm 755 ${WORKDIR}/wancontrol  ${D}${bindir}
    install -Dm 755 ${WORKDIR}/wannetwork  ${D}${bindir}
    install -Dm 644 ${WORKDIR}/afero_whitelist.prod ${D}${sysconfdir}/config/afero_whitelist.txt
    install -Dm 755 ${WORKDIR}/create_afero_whitelist.sh ${D}${sysconfdir}/config/
    install -Dm 755 ${WORKDIR}/firewall.user ${D}${sysconfdir}/config/
    install -Dm 755 ${WORKDIR}/carriers ${D}${sysconfdir}/wan/
    install -Dm 755 ${WORKDIR}/wifi_watcher ${D}${bindir}
    install -Dm 755 ${WORKDIR}/afero_net_cap ${D}${bindir}
    install -Dm 644 ${WORKDIR}/afero_get_netif_names ${D}${base_libdir}
    install -Dm 644 ${WORKDIR}/afero_netif_names ${D}${base_libdir}
    install -Dm 755 ${WORKDIR}/wifi_event.sh ${D}${bindir}
}

FILES_${PN} += " ${base_libdir}/afero_get_netif_names ${base_libdir}/afero_netif_names"
