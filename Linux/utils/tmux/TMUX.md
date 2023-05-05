# TMUX

tmux is a terminal multiplexer for Unix-like operating systems. It allows multiple terminal sessions to be accessed simultaneously in a single window. It is useful for running more than one command-line program at the same time.

## Basics

We can create new session using `tmux new -s [session-name]` and attach to already existing session using `tmux att -t [session-name]`.
The main prompt key to command tmux is `CTRL + b`.

There are three types of commands in tmux:

1. commands using the prompt key
2. commands passed as parameters to tmux
3. commands used after `command + : [SPECIFIC COMMAND]` -> one of the most famous is `set synchronize panes` to write one command in all tmux available panes in single window.

## commands

* divide screen horizentaly: `command + %`
* divide screen verticaly: `command + "`
  
> we can move between these using `command + arrows`

* resize panes: `command + CTRL + arrows`
* zoom: `command + z`

## Detach sessions

We can simply detach from a session inside tmux using `command + d`. Then see available sessio using `tmux ls`. We can attach to it using `tmux att -t [session]`

## Create New windows

we can create new windows using `command + c` and rename them with `command + ,`. We can switch to other windows by `command + [number]`.

## Rename session

We can rename our session using `command + $`

## Time

`command + t`

## Scroll

We need to use `command + PGPUP | PGPDOWN`

## Copy Paste

We need to zoom and zoomout for copying texts.
