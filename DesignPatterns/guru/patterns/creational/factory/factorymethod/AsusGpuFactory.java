package com.dogigiri.designpatterns.guru.patterns.creational.factory.factorymethod;

public class AsusGpuFactory implements GpuCreator {
    @Override
    public Gpu createGpuInstance() {
        return new AsusGpu();
    }
}
