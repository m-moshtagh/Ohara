# Rest Webservices

Rest is not a protocol instead is an architectural approach to enforce on the communication of Client and Server. Here I write the notes on situations REST is prefered.

## HTTP

Rest is Tightly coupled with HTTP however, its not tide with it. We can use REST to send SMTP, FTP request or even WRAP it around SOAP.

## Stateless

REST is Stateless so it all information in headers.

## REST constraints

In order to have a restful API we need to provide:

* statelessness
* cachability
* layer-system(proxy supports)
* Uniform interface(HATEOS)

## Serialization

Lots of formats are supported for rest messages like json, XML, javascript etc.
