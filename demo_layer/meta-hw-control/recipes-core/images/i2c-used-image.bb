SUMMARY = "bitbake-layers recipe"
DESCRIPTION = "Recipe for setting I2C status on boot"
LICENSE = "MIT"

# My addition
require recipes-extended/images/core-image-full-cmdline.bb

python do_display_banner() {
    bb.plain("***********************************************");
    bb.plain("*                                             *");
    bb.plain("*  Some additions for core-image-full-cmdline *");
    bb.plain("*                                             *");
    bb.plain("***********************************************");
}

addtask display_banner before do_build

SERIAL_CONSOLES ?= "115200;ttyS0"
ENABLE_UART = "1"

# Enable I2C
ENABLE_I2C = "1"
#KERNEL_MODULE_AUTOLOAD_rpi += " i2c-dev i2c-bcm2708"
#KERNEL_MODULE_AUTOLOAD += " i2c-dev i2c-bcm2708"
#KERNEL_MODULE_AUTOLOAD:rpi += i2c-dev i2c-bcm2708
#KERNEL_MODULE_AUTOLOAD_rpi += " i2c-dev i2c_bcm2835"
KERNEL_MODULE_AUTOLOAD += " i2c-dev i2c_bcm2835"

# Raspian: just add the following line to /boot/config.txt:
# dtparam=i2c_arm=on

#IMAGE_INSTALL += " i2c-tools nodejs"
IMAGE_INSTALL += " i2c-tools"

# Belongs to elsewhere

EXTRA_IMAGE_FEATURES:append = " ssh-server-openssh"

# Nodejs uses disk space more than before

#IMAGE_OVERHEAD_FACTOR = "1.5"
IMAGE_OVERHEAD_FACTOR = "1.1"
