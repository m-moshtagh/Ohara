# Managing Links

## Hard links

We use hard links to create backups when there's not enough fileSystem space.
a hard links Will point to the same Inode of the target file so, they share the same data.
Hard links can be in different directories but they should exist in the same filesystem.

## Soft link

Soft link is simply a pointer to a file that may reside on another filesystem like a shortcut.

They don't share same inode because they don't point to the same data instead they point to a file.
