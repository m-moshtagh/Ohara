package com.dogigiri.designpatterns.guru.patterns.creational.factory.factorymethod;

public interface GpuCreator {
    default void run() {
        Gpu gpu = createGpuInstance();
        gpu.assemble();
    }

    Gpu createGpuInstance();
}
