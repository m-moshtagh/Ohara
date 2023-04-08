# SOAP Web services

## Web Service

Web services are standard medium to propogate communication between client and servers on web. Web services will be ready over network and clients can invoke them to achieve certain functionalities.

Applications are developed with different technologies and communicating directly is difficult however all languages have understanding of web services.

## SOAP

Clients communicate with servers using Simple Object Access Protocol for sending XML data over http. Soap is a protocl or a definition on how webservices talk to each other.

> Soap supports both stateful and stateless communication.

## Message Structure

A SOAP message is encoded as an XML document, consisting of an Envelop element which contains and optional element and a mandatory body element. Theres a fault element inside body which is for reporting errors.

In header element we pass application related information.

Body contains information that the recipiant is waiting for.

## WSDL

Web service description language is a XML based definition language which is used for defining the functionality of a SOAP based webservice.

This is useful for testing because it defines the data, methods and orders etc of data This is actually the schema of the web service.
