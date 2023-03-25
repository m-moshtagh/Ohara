# Builder pattern

Builder is an internal class which aids us in creating an object with chain calls that return referenced object. This is
useful when we have optional attributes. 

```java
public class Contract {
    private String contractName;
    private Long contractNumber;
    private BigDecimal quantity;

    public Contract(String contractName, Long contractNumber, BigDecimal quantity) {
        this.contractName = contractName;
        this.contractNumber = contractNumber;
        this.quantity = quantity;
    }

    public String getContractName() {
        return contractName;
    }

    public Long getContractNumber() {
        return contractNumber;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public static class ContractBuilder {
        private String contractName;
        private Long contractNumber;
        private BigDecimal quantity;

        public ContractBuilder contractName(String contractName) {
            this.contractName = contractName;
            return this;
        }

        public ContractBuilder contractNumber(Long contractNumber) {
            this.contractNumber = contractNumber;
            return this;
        }

        public ContractBuilder quantity(BigDecimal quantity) {
            this.quantity = quantity;
            return this;
        }

        public Contract build() {
            return new Contract(contractName, contractNumber, quantity);
        }
    }
}
```

```java
@RestController
@RequestMapping("/api/v1/patterns")
public class PatternController {
    @GetMapping(path = "/builder/contract")
    public Contract getContract() {
        return new Contract.ContractBuilder()
                .contractName("abu")
                .contractNumber(1000L)
                .quantity(BigDecimal.valueOf(400000012)).build();
    }
}
```
