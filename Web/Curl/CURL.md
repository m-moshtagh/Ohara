# Curl

by Daniel Steinberg

## Simple Commands

### Get headers only

```bash
curl -I "http://www.google.com/"
```

### Save result in a file

```bash
curl -o FILE_NAME "http://www.google.com/"
```

> `-s` switch makes curl to talk with less detail.

### Check status of a link

With this command curl follows each redirect to reach the last endpoint.

```bash
curl -ILs "http://www.yahoo.com/"
```

### Check weather

```bash
curl wttr.in/tehran
```

### qrcode

```bash
curl qrenco.de/CUSTOM_THING
```

### parrot

```bash
curl parrot.live
```
