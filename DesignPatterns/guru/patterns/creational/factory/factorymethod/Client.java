package com.dogigiri.designpatterns.guru.patterns.creational.factory.factorymethod;

public class Client {
    public void orderGpu(String brand) {
        switch (brand) {
            case "MSI" -> new MsiGpuFactory().run();
            case "ASUS" -> new AsusGpuFactory().run();
            default -> throw new IllegalArgumentException();
        }
    }
}
