# SOAP Overview

Web services came to life to let different application with SOA communicate with each other on network with sets of protocols.

## SOAP

SOAP web services are tide to XML format. however, The protocol is not strict and it can ride on any protocol.

> SOAP HTTP support is tight to using post method. GET is also supported but because complexity most implementations ignore get method.

## Reason

SOAP is frequently used on the communication of private network services because of security and strictness it provides.

## Scalibility

SOAP is not scalable because if the service is upgraded to another schema the client has to upgrade itself.

## SOAP vs REST

* SOAP has better support for security and proxy services
* SOAP has fail recovery point in case of failure.
* Whenever we need ACID we should go for SOAP.
* SOAP provides Standardization and security but, REST provides flexibility and efficiency.
* When ever we need other protocols other than HTTP, Private APIs or statefullness we go for SOAP.

## Disadvantage

* Not Scalable
* Strict
* overhead
* XML parsing is slower than JSON.
