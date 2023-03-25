package com.dogigiri.designpatterns.guru.patterns.creational.factory.factorymethod;

public class MsiGpuFactory implements GpuCreator {
    @Override
    public Gpu createGpuInstance() {
        return new MsiGpu();
    }
}
