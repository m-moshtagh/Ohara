package com.dogigiri.designpatterns.guru.patterns.creational.factory.factorymethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AsusGpu implements Gpu {
    @Override
    public void assemble() {
        log.info("ASUS gpu assembled");
    }
}
