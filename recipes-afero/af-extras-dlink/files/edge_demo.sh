#!/bin/sh

# Attribute IDs
ATTR_SIREN_ENABLED=1
ATTR_SIREN_PATTERN=2
ATTR_BATTERY_LEVEL=3
ATTR_BATTERY_CHARGE=4
ATTR_SIREN_DURATION=5
ATTR_PLAT_VER_NUM=7
ATTR_HUB_VER_NUM=8

pattern_file="/tmp/edge_pattern.tmp"
duration_file="/tmp/edge_duration.tmp"
enabled_file="/tmp/edge_enabled.tmp"

set_inital_attr_value() {
    #Send all the values to the Afero Service

    /usr/bin/afedgec set ${ATTR_SIREN_ENABLED} 0 # Assume siren is off at start up
    /usr/bin/afedgec set ${ATTR_SIREN_PATTERN} ${siren_pattern}
    /usr/bin/afedgec set ${ATTR_SIREN_DURATION} ${siren_duration}
    update_battery
    /usr/bin/afedgec set ${ATTR_PLAT_VER_NUM} $(head -1 /etc/FW_package_version)
    /usr/bin/afedgec set ${ATTR_HUB_VER_NUM} $(fw_printenv hw_ver | awk -F '=' '{print $2}')
}

#Enable Siren based on siren pattern and duration from file.
enable_siren() {
    pid = `cat ${enabled_file}`
    [ "$?" -eq "0" ] && kill $pid

    if [ "x${1}x" == "x1x" ] ; then
        nohup /usr/bin/siren ${siren_pattern} ${siren_duration} &
    fi
}

set_siren_pattern() {
    siren_pattern=$1
    rm -f ${pattern_file}
    echo ${siren_pattern} > ${pattern_file}
}

set_siren_duration() {
    siren_duration=$1
    rm -f ${duration_file}
    echo ${siren_duration} > ${duration_file}
}

handle_set() {
    case $1 in
        1) enable siren $2 ;;
        2) set_siren_pattern $2 ;;
        3) set_siren_duration $2 ;;
        *) ;;
    esac
}

update_battery() {

    # btv_catch.sh return format: (charge),(level). level: 0~6, -1
    # 0~6: 0, 10, 25, 50, 75, 90, 100
    # -1: Battery not plugged in

    tmp=$(/usr/local/bin/btv_catch.sh | cut -b 3)
    case ${tmp} in
        0) battery=0 ;;
        1) battery=10 ;;
        2) battery=25 ;;
        3) battery=50 ;;
        4) battery=75 ;;
        5) battery=90 ;;
        6) battery=100 ;;
        *) battery=0 ;;
    esac
    battery_charging=$(/usr/local/bin/btv_catch.sh | cut -b 1)
    /usr/bin/afedgec set ${ATTR_BATTERY_LEVEL} ${battery}
    /usr/bin/afedgec set ${ATTR_BATTERY_CHARGE} ${battery_charging}
}


if [ -f ${pattern_file} ] ; then
    siren_pattern=`/bin/cat ${pattern_file}`
else
    set_siren_pattern 1
fi

if [ -f ${duration_file} ] ; then
    siren_duration=`/bin/cat ${duration_file}`
else
    set_siren_duration 1
fi

if [ "x${1}x" == "xx" ] && exit 1

case $1 in
     update) update_battery ;;
     set) handle_set $2 $3 ;;
     online) set_initial_attr_values ;;
     *) ;;
esac
