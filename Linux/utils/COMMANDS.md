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
