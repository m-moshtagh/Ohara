package com.dogigiri.designpatterns.guru.patterns.creational.factory.abstractfactorymethod;

public class AsusHardwareManufacturer implements HardwareFactory {
    @Override
    public Gpu createGpu() {
        return new AsusGpu();
    }

    @Override
    public MotherBoard createMotherboard() {
        return new AsusMotherBoard();
    }
}
