# Command line commands

## uname

We can view kernel version using uname command

```bash
uname -r
uname -a => detailed info
```

## timedatectl

We can change time zone and see the related info using `timedatectl`

```bash
timedatectl set-timezone Asia/Tehran
```

## lsb-release

we can see distro info using

```bash
lsb-release -a
```

## mkfs

we can create filesystem on partitions using `mkfs -t ext4 /dev/sda4`

## tune2fs

We can get detail of a partition using tune2fs command.

We can use `-L` option to set label for a partition.

```bash
tune2fs -l /dev/sda4
```

## e2label

We can set label on a partition using this command.

## blkid

We can see partition metadata using this command.

## lsblk

Displays  current partition size and mount points.

```bash
# Shows details of block devices
lsblk -f
```

## iostat

Displays IO rates and disk statistics of partitions.

## mount && unmount

after formatting a partition we need to mount the physical partition to a mount point(logical path)

```bash
mount -t ext4 /dev/sda2 /foo
umount /dev/sda2 /foo
```

We then need to persist the mount in /etc/fstab

## df

we can see the mount points and other info using df command.

```bash
df -h -T  -i
```

## du

We can see disk usage by using this command.

```bash
du -a -s /etc/dev/
```

## fsck

We can use fsck to check and repair linux filesystems. In order to use this command we need to first `umount` the partition. we need to check the exit code using `echo $?`.

## groupadd

`groupadd --gid 1001 groupname`

## getent

`getent group groupname`
`getent passwd username`
`getent shadow username`

## useradd

`useradd --comment "comment" --home-dir /home/dir --make-home --uid 1000 --gid primaryGroupname --groups group, group name`

## usermod

add a user to a group
`usermod -aG groupName username`

## chown

we can change file owner and group using this command.

```bash
chown user file.txt
chown user:group file.txt
chown :group file.txt
```

## chgrp

we can change group ownership of a file

```bash
chgrp group file.txt
```

## chmod

```bash
chmod g-w file.txt
chmod ug=rwx file.txt
chmod ugo+r file.txt
chmod a=rw file.txt
chmod --reference sourceFile.txt targetFile.txt
chmod 664 file.txt
```

## id

we can view current user detail of UUID, GID and groups it belongs to.

```bash
id -gn # check current group's name.
```

## newgrp

we can change current user group

```bash
newgrp group
```

## which

shows full path name of a shell command passed as argument.

## whereis

locates, program binaries, source code and manual pages.

## locate

This command searches a local db `mlocate.db` under `/var/lib/mlocate/` and shows all related files, libraries for a program.

## find

this command searches for files under the specified file hierarchy.

## alias

We can define abbreviation for commands. without options lists all aliases defined in system.

```bash
alias lss='ls -ltrh'
```

> To create a permanent alias we need to define it inside .bashrc

## ln

we can create links to files using ln command.

```bash
ln /root/target /root/link
ln -s /root/target /root/simbol
```

## history

shows history of the commands the user has called.
We have a $histfile which is the path of the history file. we can clear the commands and then reload it and things like this...

```bash
!! # re execute the last command
!n # re execute the command in n line
```
