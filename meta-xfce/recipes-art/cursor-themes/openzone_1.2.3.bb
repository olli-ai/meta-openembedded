SUMMARY = "X11 Mouse Theme"
HOMEPAGE = "http://xfce-look.org/content/show.php/OpenZone?content=111343"
SECTION = "x11/wm"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=a2f562fb8fb1e138b810d69521c4bcd7"
DEPENDS = "icon-slicer-native xcursorgen-native"

inherit allarch

SRC_URI = "http://xfce-look.org/CONTENT/content-files/111343-OpenZone-${PV}.tar.xz"
SRC_URI[md5sum] = "4dae968cbd525072664ef7a4fc7c4154"
SRC_URI[sha256sum] = "dc20f97a49e1ff1becf7853ef5f137ed30a4c27490540e755021d78d339efd92"

S = "${WORKDIR}/OpenZone"

do_install() {
    install -d ${D}${datadir}/icons
    for theme in `find -name '*.tar.xz'`; do
        tar -Jxf ${theme} -C ${D}${datadir}/icons
    done
}

python populate_packages_prepend () {
    icondir = bb.data.expand('${datadir}/icons', d)
    do_split_packages(d, icondir, '^(.*)', '%s', 'Open Zone cursors %s', allow_dirs=True)
}

PACKAGES_DYNAMIC += "^openzone-.*"
ALLOW_EMPTY_${PN} = "1"

PNBLACKLIST[openzone] ?= "Fails to build with RSS http://errors.yoctoproject.org/Errors/Details/130682/ - the recipe will be removed on 2017-09-01 unless the issue is fixed"
