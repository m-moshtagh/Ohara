# Run levels & Boot Targets

We Talked about OS boot up and the levels it starts from and where it goes. We can choose to boot up the device on certain level that we want. These contious levels of OS is called Runlevels(SystemV) or BootTargets(Systemd)

We can see target units inside systemd using:

`systemctl list-units --type=target`

## Systemd Targets

Systemd targets are more complex than traditional SysV. For Simplicity systemd defines some targets which are accessible by `systemctl isolate [state]`:

* rescue: file system is up, there is no network. only root can log in
* emergency: root file system is up (little file system)
* reboot: downs all processes, halts CPU Then boots system up again.
* halt: stops all processes and halts CPU activities.
* poweroff: like Halt but also sends an ACPI shutdown signal(no electricity power!).

## SystemV Runlevels

In SystemV red hat based distros we had:

0. Shutdown
1. Single user mode(recovery) also called `s`
2. multi user without networking
3. multi user with networking
4. to be customized by admin
5. multi user with graphic and network
6. reboot

In SystemV debian based we had:

0. shutdown
1. single user mode
2. multi user with graphic
6. reboot

> We can change these run levels using: `init [level]`
> Even on systemd machines the SystemV commnds work. `runlevel` & `tellinit`
> Inside etc there are rcs files soft linked to init.d files

## Shutdown properly

We can shutdown with `poweroff`, `halt`, `reboot`, `shutdown` commands which are link to systemd targets.

`shutdown -r [time] [message]` we can cancel using `shuwdown -c`

> we can turn wall messages off using `messg n`

## Notify users

wall -> sending messages to logged in users

/etc/issue -> Text to be displayed on tty terminal logins

/etc/issue.net -> text ot be displayed on remote logins

/etc/motd -> message of the day
