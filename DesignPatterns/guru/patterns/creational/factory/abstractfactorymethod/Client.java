package com.dogigiri.designpatterns.guru.patterns.creational.factory.abstractfactorymethod;

public class Client {
    private Gpu gpu;
    private MotherBoard motherBoard;

    public Client(HardwareFactory factory) {
        gpu = factory.createGpu();
        motherBoard = factory.createMotherboard();
    }

    public void doStuff() {
        this.gpu.assemble();
        this.motherBoard.assemble();
    }
}
