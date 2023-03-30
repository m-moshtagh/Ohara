# Partitioning in linux

Before formatting the disk we can divide our disk to sets of partitions and we can allocate them to the structures in FHS.

## Benfits

For example we can allocate `/boot` a 250m partition, `/` a 200 gb partition and `/home` a 800 gb partition. Though, everything will be inside `/` directory however if we try to write anything to boot or home It will be available on the other partitions.

> We usually isolate boot, so that if the root or any part is curropted the OS can boot up so we can fix it. Also, we can encrypt all other partitions but not boot because for booting up with minimal requirements we don't have decryptions tools.

This is beneficial for example we want to install another distro and all we have to do is to replace the `/` and `/boot` parititions.

## Disk devices

In linux disk are recognzied as sda or sdb for two different devices and their partitions are specified with numbers like sda1 for first partition of first device.

> In BIOS(MBR) style we could only have four primary partitions on each disk and, for extra partitions we needed one extending partition and have logical paritions inside that one.

## Commands

* `fdisk /etc/sda1` will give us a interactive environment to interact with details and configuring parititions.
* `parted /etc/sda1 p` will show us detail on parititions. `gparted` is the graphical parted.

## LVM

Logical Volume(disk) Management. Using this we can have multiple Physical disk storages and take them as a logical volume and extend them whenever we want. Much more detail in LPIC 102.

## RAID

Raid is a disk which meant to prevent fault. All we do in set 1 of disks will be done in set 2 and if set 1 fails the second set will be good.

> This is not a backup approach.

## Swap

Swap is a physical storage meant for doing paging(memory job) if an application is overusing the memory.

We can see the memory detail using `free -h` We can inspect swap with `swapon`

Some distros may create a swapfile under `/` and use it this way.

### Zram

This way linux kernel stores some data on a part of ram with compression which seems like we have larger memory.

`zramctl` is the command to use zram.
