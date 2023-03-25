# XML - Extensible Markup Language

## Concept

XML is a standard data format which is used to store and exchange data in applications. JAX-B is one of the Java XML
APIs.

## JAXB

Java Architecture for XML Binding. We use JAXB for binding XML data to a Java POJO. There are several APIs for
processing XML:

* DOM
* SAX
* StAX
* JAXB

![APIs](pics/xml1.png)
![APIs](pics/xml2.png)
![APIs](pics/xml3.png)
![APIs](pics/xml4.png)
![APIs](pics/xml5.png)

## XML & Namespace

namespace is like packages in java which keep a bunch of related tag names together. start tag can have a special
attribute name xmlns and the value will be the name of the namespace.

XML unlike HTML doesn't have fixed set of tags. You just define custom tags that are meaningful in the context of your
application. The XML Schema defines the data model for xml. Schema has different languages

* DTD
* XSD
* W3C

In order to define a schema we use w3 namespace and also use xs prefix. We can define useful things at root level like
elements, attributes, simpleType and complexType. Complex type are types that contain other nested elements.

## JAXB API

![](pics/xml6.png)
![](pics/xml7.png)
![](pics/xml8.png)
![](pics/xml9.png)
![](pics/xml10.png)
![](pics/xml11.png)

### Marshalling java objects to XML

![](pics/xml12.png)
![](pics/xml13.png)

### JAXB annotation

JAXB gives us annotations to control the binding of data to Java POJOs.

The way JAXB models the Domain from Java insight using Class fields. Fields are wrapper & primitive and, it
can access them through getters and setters like java beans. We can also, customize JAXB to access fields directly. Some
annotations are applicable both on getters/setters and fields.

Domain model from XML is done by element, attribute, simpleType(primitive) and complexType(wrapper) declaration

### Order of elements

If we don't specify the order annotations, JAXB will order the fields as it please.

![](pics/xml14.png)
![](pics/xml15.png)
![](pics/xml16.png)

### Mapping to simpleTypes

![](pics/xml17.png)
![](pics/xml18.png)
![](pics/xml19.png)
![](pics/xml20.png)
![](pics/xml21.png)
![](pics/xml22.png)
![](pics/xml23.png)
![](pics/xml24.png)

### Mapping Enums to SimpleTypes

![](pics/xml25.png)
![](pics/xml26.png)

### Mapping collections and Arrays

![](pics/xml27.png)
![](pics/xml28.png)
![](pics/xml29.png)

> This is not efficient, best practice is the method below

![](pics/xml30.png)

> This works on arrays and sets but, maps should be customized with adapters.

### Custom mapping with Adapters

![](pics/xml31.png)
![](pics/xml32.png)
![](pics/xml33.png)
![](pics/xml34.png)
![](pics/xml35.png)

### Generating XSD form Java code

![](pics/xml36.png)
![](pics/xml37.png)
![](pics/xml38.png)
![](pics/xml39.png)

### Generating named & anonymous types

![](pics/xml40.png)
![](pics/xml41.png)

> If we pass empty string anonymous root element will be created.

### Making elements and attributes required

![](pics/xml42.png)
![](pics/xml43.png)
![](pics/xml44.png)

### Specifying default values for elements adn attributes

![](pics/xml45.png)
![](pics/xml46.png)
![](pics/xml47.png)
![](pics/xml48.png)

### Making elements Nillable

![](pics/xml49.png)
![](pics/xml50.png)
![](pics/xml51.png)

### Distinguishing between absent and nillable

![](pics/xml52.png)
![](pics/xml53.png)
![](pics/xml54.png)
![](pics/xml55.png)
![](pics/xml56.png)
![](pics/xml57.png)
![](pics/xml58.png)
![](pics/xml59.png)
![](pics/xml60.png)
