package com.dogigiri.core.security.developsecurejavacode.jce.guardedobject;

import com.dogigiri.core.security.developsecurejavacode.jce.sealedobject.Employee;
import org.slf4j.LoggerFactory;

import java.security.GuardedObject;
import java.util.PropertyPermission;

public class GuardedObjectDemo {
    public static void main(String[] args) {
        Employee employee = new Employee(2L, "David", "5456");
        var guard = new PropertyPermission("java.home", "read");
        GuardedObject guardedObject = new GuardedObject(employee, guard);
        var fetched = (Employee) guardedObject.getObject();
        LoggerFactory.getLogger(GuardedObjectDemo.class).info("{}", fetched);
    }
}
