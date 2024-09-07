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
