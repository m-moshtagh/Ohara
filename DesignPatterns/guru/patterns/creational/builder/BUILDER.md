# Builder Design Pattern

Builder is a creational design pattern that lets you construct complex objects step by step. The pattern allows you to
produce different types and representations of an object using the same construction code.

## Problem

In order to instantiate a complex object we need a constructor with too many parameters. Even if we create subclasses
we end up calling constructors with lots of null values.

## Solution

The builder pattern suggests that you extract the object construction code out of its own class and move it to separate
objects called _**Builders**_
Pattern organises object creation into various steps. We can only call the necessary steps we need to construct our
object.

We can also extract a series of calls to the builder steps and call it director. This Director class defines order in
which to execute building steps while the builder provides the implementation of these steps.

Good description on [refactoring.guru](https://www.refactoring.guru/design-patterns/builder)

## Examples

### Without Director

```java
public class Contract {
    private long id;
    private UUID uuid;
    private ContractType contractType;
    private Instant contractStartDate;
    private Instant contractEndDate;

    public Contract() {
    }

    public Contract(long id, ContractType contractType, Instant contractStartDate, Instant contractEndDate) {
        this.id = id;
        this.uuid = UUID.randomUUID();
        this.contractType = contractType;
        this.contractStartDate = contractStartDate;
        this.contractEndDate = contractEndDate;
    }

    public long getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public Instant getContractStartDate() {
        return contractStartDate;
    }

    public Instant getContractEndDate() {
        return contractEndDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Contract contract = (Contract) o;

        return new EqualsBuilder().append(getId(), contract.getId()).append(getUuid(), contract.getUuid())
                .append(getContractType(), contract.getContractType()).append(getContractStartDate(),
                        contract.getContractStartDate()).append(getContractEndDate(),
                        contract.getContractEndDate()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getId())
                .append(getUuid()).append(getContractType()).append(getContractStartDate())
                .append(getContractEndDate()).toHashCode();
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", uuid=" + uuid +
                ", contractType=" + contractType +
                ", contractStartDate=" + contractStartDate +
                ", contractEndDate=" + contractEndDate +
                '}';
    }

    public static class ContractBuilder {
        private long id;
        private ContractType contractType;
        private Instant contractStartDate;
        private Instant contractEndDate;

        public ContractBuilder setId(long id) {
            this.id = id;
            return this;
        }

        public ContractBuilder setContractType(ContractType contractType) {
            this.contractType = contractType;
            return this;
        }

        public ContractBuilder setContractStartDate(Instant contractStartDate) {
            this.contractStartDate = contractStartDate;
            return this;
        }

        public ContractBuilder setContractEndDate(Instant contractEndDate) {
            this.contractEndDate = contractEndDate;
            return this;
        }

        public Contract build() {
            return new Contract(id, contractType, contractStartDate, contractEndDate);
        }
    }
}
```

```java
class ContractTest {
    Contract contract;

    @BeforeEach
    void setup() {
        contract = new Contract();
    }

    @Test
    void initializationTest() {
        contract = new Contract.ContractBuilder()
                .setId(1L)
                .setContractType(ContractType.FULL_TIME)
                .setContractStartDate(Instant.now(Clock.systemUTC()))
                .setContractEndDate(Instant.now(Clock.systemUTC()).plus(365, ChronoUnit.DAYS))
                .build();
        assertThat(contract).isNotNull();
    }
}
```
