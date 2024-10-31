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

## Commands for manipulating files

`cat` => we can use cat to print, files.
`paste` => we can merge lines of files
`od` => It's used to display the contents of a file in various formats, including octal, hexadecimal, decimal, and ASCII. It's particularly useful for debugging binary files.

```bash
od -c filename
od -x filename
od -b filename
```

`split` => split a file into pieces
`sort` => sort lines of text files
`nl` => number line of files
`wc` => count the contents of a file
`cut` => remove sections from each line of files
`head` => get 10 firt lines of a file
`tail` => get 10 last lines of a file
`more` => view file
`less` => view a file better:D
`uniq` => report or ommit repeated lines
`sed` => modify a text file

```bash
sed 's/foo/bar/g/' foo.txt // replace foo with bar in foo text file globally.
```

## man

an interface for comamnd manuals.
`man -k` => `apropos`
`man -f` => `whatis`

we can find different sections by numbers

* 1 => User commands
* 2 => System calls
* 3 => Library calls
* 4 => Device calls
* 5 => File formats
* 6 => Games
* 7 => Miscellaneous
* 8 => Adminstrative commands
* 9 => kernel routines

## ps

we can view all processes using `ps -ef` or `ps aux` command.
print process tree using `ps -ejh` or `ps axjf` and we can also use `pstree`
If we need to use process running as root we can use `ps -U root -u root u`

## jobs

Displays the status of jobs in background. We can see details using `- l` option.
We can pause a running command using `CTRL + z` and then using bg command and number that jobs command give us send it to run in background.
We can also use `fg` command to send a backgorund job to the foreground.

## kill

We can send a signal to a process using kill command. list of signals is available in `kill -l`

## killall

Kill a process by name.

## nice & renice

We can run a program with modified scheduling priority. renice can change the niceness level of a command later using processId, process started by a certain user or groups.

```bash
nice --9 ping 9.9.9.9 // sets niceness level of command to -9
```

## compressing  tools

We can compress files with different tools, xz and bzip2 are recommended. except zip command all of them replace the original file.
The first column is compressing command, second one is for decompressing(we can use -d with the original command), third one is for displaying the original content it.

gzip :: gunzip :: zcat
bzip2 :: bunzip2 :: bzcat
xz :: unxz :: xzcat
zip :: unzip :: zless

## archiving files

We can archive and backup multiple files using `tar, cpio, dd` tools.
a tar file is created using `tar -cvf foo.tar files...`
We can also create tarballs by adding `--xz --gzip --bzip2` or their abbreviation `-J -z -j`
`cpio` is another tool for archiving our files.

## dd

We can use dd for backing up files or disks, We can also create files with a fixed space.
options:
`if` for input device and `of` for output device.
`count` sets number of input blocks to copy.
`status` sets info to desplay to STDERR can be none, noxfer, progress

```bash
dd if=/dev/sdb of=/dev/sdc status=progress
dd if=/dev/zero of=/dev/sdc status=progress # fills sdc device with zeros.
```

## Streams

We can redirect STDOUT or STDERR following special pipes
1> or > redirects STDOUT
2> redirects STDERR
&> redirects all output

> we can send STDERR to /dev/null which is a black hole.

2>&1 | less redirects STDERR to STDOUT

We can combine STDOUTS of two commands:
`cal 2017; cal 2023 | less`

## xargs

this takes what is coming from STDOUT and puts it as the STDINPUT of a command.

```bash
ls -l EMPTY?.txt | xargs -p rm
```

## $() & ``

We can also execute commands with the output of other commands with help of these.
