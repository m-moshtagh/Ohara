# Hessian Remote Protocol

Hessian protocol is an alternative remote object invocation and Web services protocol supplied as an open source Java framework through Caucho Technology.

## Burlap vs Hessian

Burlap and Hessian are peer specifications of the same conceptual protocol. The major difference between the two is the implementation of the serialization mechanism. Burlap serializes objects over the Web using XML; Hessian serializes objects using a proprietary (very compact) binary format.

## Remote Objects

Burlap/Hessian remote objects are just ordinary Java objects that implement some interfaces. They don’t require special proxy, home, or remote classes. One of the inherent benefits of this object-and-interface model is that it promotes the good object-oriented design practice of design by interface. Design by interface mandates that client objects depend on abstractions and never on concrete classes.

Hessian server is nothing more than a servlet that can dispatch Burlap or Hessian serialized objects via the Web. Hessian remote objects are configured in a web.xml file (as examples will demonstrate shortly).

## Steps

### Enable server components for the remote invocation

The first step in making inventory-tracking objects available remotely via Hessian is to make sure that objects of interest implement some plain Java interfaces. If the object you plan to expose remotely does not implement any interfaces, you need to extract the appropriate business interface from it, and implement that interface. This step is actually the only requirement that Hessian imposes on your server-side objects.

### Configure the server component for the remote invocation (over the Web)

In order to make the ItemAvailabilityTracker service available over the Web, the server object and the interface it implements have to be registered with the Hessian Servlet (org.caucho.Hessian.HessianServlet) in the web.xml file of the existing inventory management Web application.

### Invocation on the client

In order to invoke the objects remotely, the client application needs to have a Burlap or Hessian jar in the classpath, as well as in the interface for the service being invoked.

## Suggested Practical Uses for Burlap/Hessian

* With Spring Framework
* For Localized Object Remoting
* Federation of Portable Devices(small devices & mobile phones)

## Good Replacements

* Spring http invoker, It also looks like Hessian and http invoker should be easily exchangable.
