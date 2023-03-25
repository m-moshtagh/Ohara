# Repository Pattern

Spring Data and Spring JDBC are based on this pattern. This was introduced in DDD by Eric Evans. We use this pattern to
hide data access operation details from consumer and to prevent leaks of this layer to business logic. So, all we need
to do is to create a Generic interface with common operations. In spring data we also create interface which extends
JPARepository.