# Boot Process

Firmware is the starting point of boot process on any hardware.

1. Firmware does a PowerOnSelfTest(POST)
2. Motherboard loads the bootLoader
3. Bootloader loads the linux kernerl based on configs
4. Kernel loads and prepares the system(root file system) and runs the initialization program
5. Init program starts services and other programs

## Types of firmwares

### BIOS

* older
* limited to load one sector of disk(MBR)

### UEFI

* newer than BIOS
* Specifies a special disk partition for bootloader called EFI system partition(ESP)
* EFI is mounted on `/boot/efi` and it's files have `.efi` extension.

## Bootloader

BootLoader can locate and read kernel and load it in memory.

GRUB & LILO and GRUB2 are bootloaders.

> BootLoader can load anything and is not supposed to only load kernel. Firmware is same but we tell firmware to load bootloader for ease of use.

## InitRamFS

At first for kernel to start working needs a little understanding on disks and files and minimum detail on drivers. This is solved by `InitRamFs`. So, BootLoader loads this alongisde kernel and we can pass some parameters to it to config kernel for booting up.

We have different init systems:

* SysVInit(For Unix V)
* Systemd -> Mostly used
* Upstart

> We can view our init by `readlink -f init` or `ps -p 1`.(The first process is always init We can see it using `pstree`)

### Logs

> The whole process is logged and we can see kernel logs during bootup, by pressing `ESC` or `Alt + F1`(If we have splash screen that blocks logs). Kernel has no access to drivers so these logs are inside Kernel Ring Buffer and we can view these logs using `dmesg`.

We can view log files inside `/var/log/dmesg/` or `/var/log/boot.log`

`journalctl -k` also gives information inside kernel(if `systemd` is present). `journalctl -b` shows information for boot.

## Systemd

Systemd works with bunch of units which have precedence and can be find:

* `/etc/systemd/system/`
* `/run/systemd/syste,`
* `/usr/lib/systemd/system`

We can control systemd units using `systemctl` and view logs using `journalctl`.

* `systemctl list-units` -> lists all units
* `systemctl cat [UNIT]` -> viewing details of units
* `systemctl status [UNIT]` -> view status of a unit
* `systemctl is-active [UNIT]`
* `systemctl is-failed [UNIT]`
* `systemctl enable [UNIT]`
* `systemctl disable [UNIT]`
* `systemctl restart [UNIT]`
* `systemctl reload [UNIT]` -> reloads configuration of a unit
* `systemctl daemon-reload [UNIT]` -> reloads units if the configuration of a unit inside systemd is changed

> There different types of units but mostly we work with services and targets.
> We can stop or start services and also we can enable them to get started during boot up.

To decrease the amount of journalctl logs we can:

* `journalctl -n 10` -> 10 lines of log
* `journalctl -S -1d` -> 1 day log
* `journalctl -xe` -> last few logs
* `journalctl -u [UNIT]` -> for specific unit
* `journalctl _PID=[PROCESS_ID]`

## SysV

The control files are located inside `/etc/init.d/`. Files located here were bash scripts that we could interact.
