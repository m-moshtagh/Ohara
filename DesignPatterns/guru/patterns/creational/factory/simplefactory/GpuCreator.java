package com.dogigiri.designpatterns.guru.patterns.creational.factory.simplefactory;

public class GpuCreator {
    public static void main(String[] args) {
        var gpu = GpuFactory.createGpuInstance("MSI");
        gpu.assemble();
    }
}
