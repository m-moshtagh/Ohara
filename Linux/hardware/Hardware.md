# Linux & Hardware

Operating systems are responsible for organizing and utilizing the hardwares Here we see how Linux does the job.

[Jadi's Free Ebook](https://linux1st.com/1011-determine-and-configure-hardware-settings.html)

## SYSFS

This is a pseudo file system inside kernel not physically on disc which shows all the information of kernel from modules, subsystems, hardware devices etc to user.

This is accessible inside `/sys`.

We can see our block devices inside `block` directories. Also devices connected inside `bus` directory.

## Udev

userspace dev(/dev) is a device manager for linux kernel.

> We can't interact with sysfs however it is possible with udev.

## Dbus

This is a mechanism for processes to have inter process communication.

## Proc

Shows all the processes which are on kernel memory(not physical disc)

## Commands

### lsusb, lsblk, lspci, lshw

These commands show the devices associated with command.

## Kernel Modules

Kernel has drivers of hardware devices as .ko files. We can manage these modules using `lsmod` & `modprobe`

### lsmod & modprobe

We can see all loaded modules by kernel using this command. We can remove module using `rmmod`. We need `insmod` to install modules however we need to provide the exact address of the module.

Since `insmod` is hard we can use `modprobe` instead.
