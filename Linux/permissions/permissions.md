# Permissions and Ownerships

Files's ownership is set upon creation of the files. The user who creates it will become the owner and user's primary group will be the group ownership of the file.

## Change ownership

The ownership of the files can be changed using `chown` command. Two rules are needed for changing ownership.

1. Sudo privilage is needed to change ownership of a file.
2. The owner of a file can only change the group owner of a file using `chown` commnad.

> syntax of chown command owner and ownergroup can be declared by seperating them using `:` for example: `foo:foo` means user foo and group foo. `:foo` means only group foo.

## Permissions

Permission of files divides into three ownerships:

* user
* group
* other

and r(read), w(write), x(execute), suid(s), sgid(s) and sticky(t) is available for these groups.

We use `chmod` command to grant or revoke permissions of a file.

> we can use `setfacl` command to define a custome permission for a user.

There are two types to set permission to a file:

* Symbolic mode
* Octal mode

## umask

In order to change the default permissions of creating files and directories we need to set umask.
by default the file permission is 666 and directory is 777.
How it works is that default umask is 0022 and the default permission will be subtracted from umask value and the permission would set for file or directory.

## SUID

The suid permission works directly with executable files. What it dows is that it lets users to execute the file with the owner's permission. This is useful for run and stop scripts of server applications which need root permission to be executed. it's represented with `s` and `S` if x is not given to file.

## SGID

This tells the linux to run program file with the group permission, For directories it helps to create an envirnment for all users to share files.

## Sticky bit

This is helpful for protecting files of different users from being deleted by other users working in the same directory which don't own the file even if they belong to the group that has write access to the file.
