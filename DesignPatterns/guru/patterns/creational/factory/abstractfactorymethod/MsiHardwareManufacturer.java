package com.dogigiri.designpatterns.guru.patterns.creational.factory.abstractfactorymethod;

public class MsiHardwareManufacturer implements HardwareFactory {
    @Override
    public Gpu createGpu() {
        return new MsiGpu();
    }

    @Override
    public MotherBoard createMotherboard() {
        return new MsiMotherBoard();
    }
}
