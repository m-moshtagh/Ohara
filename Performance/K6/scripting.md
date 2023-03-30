# Scripting

We can easily write scripts in `javascript`.

## Basics

We can import http and send a simpe request to target website.

```js
import http from "k6/http";

export default function () {
  http.get("http://test.k6.io");
}
```

We an also define number of VU's and sleep time

```js
import http from "k6/http";
import { sleep } from "k6";

export const options = {
  vus: 10,
  duration: "10s",
};

export default function () {
  http.get("http://test.k6.io");
  sleep(1);
}
```
