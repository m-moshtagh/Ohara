package com.dogigiri.designpatterns.guru.patterns.creational.factory.abstractfactorymethod;

public interface HardwareFactory {
    Gpu createGpu();

    MotherBoard createMotherboard();
}
