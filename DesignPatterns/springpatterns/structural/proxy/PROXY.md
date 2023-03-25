# Proxy Pattern

Since Spring 4.0 every bean we make at least gets one proxy. Proxies are usually applied in spring with AOP. Sometimes
we want an intermediary object in place of real object to add behavior and control the flow of that
object. `Transactional` and `Cacheable` are great examples of this.