package com.dogigiri.designpatterns.guru.patterns.creational.factory.simplefactory;

public class GpuFactory {
    private GpuFactory() {
    }

    public static Gpu createGpuInstance(String manufacturer) {
        return switch (manufacturer) {
            case "ASUS":
                yield new AsusGpu();
            case "MSI":
                yield new MsiGpu();
            default:
                throw new IllegalArgumentException();
        };
    }
}
