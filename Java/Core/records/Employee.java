package com.dogigiri.core.records;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Random;
import java.util.UUID;

public record Employee(UUID uuid, long employeeId, String employeeName, String empCode) {

    private static final String NO_SALARY = "No Salary";

    // modifying the Constructor
    public Employee { // this is called compact constructors
        employeeName = "Mr/Mrs/miss " + employeeName;
        uuid = UUID.randomUUID();
    }

    public Employee() {
        this(UUID.randomUUID(), new Random().nextInt(1000_000), null, null);
    }

    public String getSalary() {
        return NO_SALARY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        return new EqualsBuilder().append(employeeId, employee.employeeId).append(uuid, employee.uuid)
                .append(employeeName, employee.employeeName).append(empCode, employee.empCode).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(uuid).append(employeeId)
                .append(employeeName).append(empCode).toHashCode();
    }
}
