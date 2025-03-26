# Serializing & Deserializing Java Objects

In order to be able to transfer Java Objects through IO we need to transfer them as byte arrays. By Implementing
Serializable marker interface compiler will make this happen.

## usage

* RemoteCall / Multi-Tier
* Web Session Scoped Objects

## Versioning

Classes in java would have a serialVersionUID which is a digest number out of class fields. By adding and removing
fields from a class this long number will be different. We can also give this number ourselves to prevent any
incompatibility if JDK or any library use different algorithm to create the digest.
`private static final long serialVersionUID = 6568719787599064240L;`

> WE can see the class version via this JDK commandline tool `serialver ${classPath}`

## Transient

WE can also ignore fields for serializing with `transient` keyword.
